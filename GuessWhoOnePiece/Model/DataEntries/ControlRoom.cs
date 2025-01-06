// <copyright file="ControlRoom.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System.Collections.Concurrent;
using System.Globalization;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represents the control of threads for web call.</summary>
    public class ControlRoom
    {
        /// <summary>Url of the list of characters in the One Piece Fandom.</summary>
        private const string UrlFandomListCharacter = "https://onepiece.fandom.com/fr/wiki/Liste_des_Personnages_Canon";

        /// <summary>Number of levels.</summary>
        internal const int NumberOfLevels = 2;
        
        /// <summary>Pattern to have only alphanumerical letters.</summary>
        private const string Pattern = @"^[a-zA-Z0-9\s]*$";
        
        /// <summary>List of characters' name.</summary>
        private readonly List<string> _characterNameList = [];

        /// <summary>Percentage of advencement for loading characters.</summary>
        private int _countPercentage;
        
        /// <summary>Generate threads to get data.</summary>
        /// <returns>The complete list of characters.</returns>
        public async Task<List<Character>> GenerateThreads()
        {
            await ReceivedCharactersList();

            var charactersList = new ConcurrentBag<Character>();
            
            await Parallel.ForEachAsync(_characterNameList, async (characterName, token) =>
            {
                var character = await DataForCharacter(SetCharacterLink(characterName), characterName);
                if (character != null)
                    charactersList.Add(character);
            });

            Popularity.SetPopularity(_characterNameList, charactersList.ToList());

            ManageCsv.SaveCharactersToCsv(charactersList.ToList());

            return charactersList.ToList();
        }

        /// <summary>Get the list of character from the fandom webpage and add each character into <see cref="_characterNameList"/>.</summary>
        private async Task ReceivedCharactersList()
        {
            try
            {
                var web = new HtmlWeb
                {
                    OverrideEncoding = Encoding.UTF8
                };
                var doc = await web.LoadFromWebAsync(UrlFandomListCharacter);
                var tables = doc.DocumentNode.SelectNodes("//div[contains(@class, 'tabber wds-tabber')]//table[contains(@class, 'wikitable')]");

                foreach (var table in tables)
                {
                    var rows = table.SelectNodes("//tr");
                    foreach (var row in rows)
                    {
                        var link = row.SelectSingleNode("td[2]/a");
                        if (link != null)
                        {
                            var character = DataControl.ExtractExceptions(link.InnerHtml.Trim());
                            if(!_characterNameList.Contains(character))
                                _characterNameList.Add(character);
                        }
                    }
                }
            }
            catch (Exception)
            {
                // Empty on purpose.
            }
        }

        /// <summary>Get data for a specific character from its fandom page.</summary>
        /// <param name="url">The url of the character.</param>
        /// <param name="characterName">The name of the character.</param>
        /// <returns>The character.</returns>
        private async Task<Character?> DataForCharacter(string url, string characterName)
        {
            try
            {
                var web = new HtmlWeb
                {
                    OverrideEncoding = System.Text.Encoding.UTF8
                };
                var doc = await web.LoadFromWebAsync(url);
                
                const string classCharacterData = "pi-item pi-group pi-border-color";

                var data = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classCharacterData}')]");
                var characterData = string.Join(" ", (data != null ? data.Select(n => n.InnerText) : throw new InvalidOperationException("No character data")));

                const string classFruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
                var fruitElement = string.Join(" ", doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classFruit}')]").Select(n => n.InnerText));

                var pictureElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, 'image')]//img");
                var pictureElement = GetPictureLink(pictureElements, characterName);

                const string classType = "pi-item pi-data pi-item-spacing pi-border-color";
                var bountyTypeCrewElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classType}')]");
                HtmlNode typeElement = null!, crewElement = null!;
            
                characterData = CleanWebHtmlString(characterData);
                fruitElement = CleanWebHtmlString(fruitElement);
                pictureElement = CleanWebHtmlString(pictureElement);

                foreach (var bountyTypeCrewElement in bountyTypeCrewElements)
                {
                    var dataSource = bountyTypeCrewElement.GetAttributeValue("data-source", "");
                    if(dataSource == "occupation")
                        typeElement = bountyTypeCrewElement;
                    if(dataSource == "affiliation")
                        crewElement = bountyTypeCrewElement;
                }

                var crew = crewElement == null ? "Citizen" : DataControl.ExtractPatternCrew(crewElement);
                var fruit = fruitElement.Contains("Fruit du Démon", StringComparison.OrdinalIgnoreCase);
                var type = typeElement == null ? "Citizen" : DataControl.FixType(DataControl.ExtractPatternType(typeElement), crew);
                var bounty = DataControl.FixBounty(DataControl.ExtractPatternBounty(characterData).Replace("[.,\\s]", "", StringComparison.OrdinalIgnoreCase).Trim(), type);

                var chapterString = DataControl.ExtractPattern(characterData, "Chapitre (\\d+)");
                var chapter = !string.IsNullOrEmpty(chapterString) ? int.Parse(chapterString) : throw new InvalidOperationException("Invalid Chapter");

                var alived = !DataControl.ExtractPattern(characterData, @"Statut\s:(Vivant|Décédé)").Equals("Décédé");

                var age = DataControl.ExtractPatternAge(characterData);
                if(age == 0)
                    throw new InvalidOperationException("No age for the chracter");

                crew = DataControl.FixCrew(crew, type);
                type = DataControl.FixType(type, crew);

                var characters = new Character(characterName, fruit, bounty, chapter, type, alived, age, crew, pictureElement, NumberOfLevels + 1);
                _countPercentage++;

                if(_countPercentage > _characterNameList.Count)
                {
                    _countPercentage = _characterNameList.Count;
                }
                return characters;
            }
            catch (InvalidOperationException)
            {
                _countPercentage++;
                return null;
            }
        }

        /// <summary>Set the link to find a character on the fandom.</summary>
        /// <param name="characterName">The character to find.</param>
        /// <returns>The link of the character.</returns>
        private static string SetCharacterLink(string characterName)
        {
            var urlCharacter = characterName.Replace(" ", "_", StringComparison.OrdinalIgnoreCase).Trim();
            const string pattern = @"(.+)_\(.*\)";
            var matcher = Regex.Match(urlCharacter, pattern);
            if (matcher.Success)
            {
                urlCharacter = matcher.Groups[1].Value;
            }

            const string urlFandom = "https://onepiece.fandom.com/fr/wiki/";
            return urlFandom + urlCharacter;
        }

        /// <summary>Clean string from web and html format.</summary>
        /// <param name="webString">String to clean.</param>
        /// <returns>The string cleaned.</returns>
        private static string CleanWebHtmlString(string? webString) => webString != null ? WebUtility.HtmlDecode(webString).Replace("\n", "", StringComparison.OrdinalIgnoreCase)
            .Replace("\t", "", StringComparison.OrdinalIgnoreCase) : "";
    
        /// <summary>Gets the link of the image for a character.</summary>
        /// <param name="listOfPictures">List of picture in the web page.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The link of the image for the character.</returns>
        private static string GetPictureLink(HtmlNodeCollection listOfPictures, string characterName)
        {
            string pictureElement = string.Empty;

            foreach (var picture in listOfPictures.Select(picture => picture.GetAttributeValue("src", "").Split(";")[0]))
            {
                if (picture.Contains("data:image/gif", StringComparison.OrdinalIgnoreCase) || picture.Contains("Site-logo", StringComparison.OrdinalIgnoreCase))
                    continue;
                if (picture.Contains("Manga_Post_Ellipse", StringComparison.OrdinalIgnoreCase) || picture.Contains("Manga_Pre_Ellipse", StringComparison.OrdinalIgnoreCase) ||
                    picture.Contains("Anime_Post_Ellipse", StringComparison.OrdinalIgnoreCase) || picture.Contains("Anime_Pre_Ellipse", StringComparison.OrdinalIgnoreCase))
                    return picture;

                if (picture.Contains(characterName))
                {
                    return picture;
                }
                else
                {
                    foreach (var character in characterName.Split(" "))
                    {
                        if (picture.Contains(WebUtility.UrlEncode(character), StringComparison.OrdinalIgnoreCase))
                            return picture;
                        else if (RemoveDiacritics(WebUtility.UrlDecode(picture)).Contains(RemoveDiacritics(character), StringComparison.OrdinalIgnoreCase))
                            return picture;
                    }

                    if (CalculateMatchPercentage(picture, characterName) > 0.11)
                        return picture;
                }

                if (characterName.Equals("And", StringComparison.OrdinalIgnoreCase) && picture.Contains("Baskerville", StringComparison.OrdinalIgnoreCase))
                    return picture;
                else if (characterName.Equals("Belmer", StringComparison.OrdinalIgnoreCase) && picture.Contains("Bell", StringComparison.OrdinalIgnoreCase))
                    return picture;
                else if (characterName.Equals("Rock", StringComparison.OrdinalIgnoreCase) && picture.Contains("Yeti", StringComparison.OrdinalIgnoreCase))
                    return picture;
            }

            return string.Empty;
        }

        /// <summary>Removes diacritics from a string.</summary>
        /// <param name="text">Text to analyse.</param>
        /// <returns>The new text.</returns>
        private static string RemoveDiacritics(string text)
        {
            var normalizedString = text.Normalize(NormalizationForm.FormD);
            var stringBuilder = new StringBuilder();

            foreach (var c in normalizedString)
            {
                var unicodeCategory = CharUnicodeInfo.GetUnicodeCategory(c);
                if (unicodeCategory != UnicodeCategory.NonSpacingMark)
                {
                    stringBuilder.Append(c);
                }
            }

            return stringBuilder.ToString().Normalize(NormalizationForm.FormC);
        }

        /// <summary>Calculate the percentage of match between two strings.</summary>
        /// <param name="picture">Url of the picture.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The percentage.</returns>
        static double CalculateMatchPercentage(string picture, string characterName)
        {
            int levenshteinDistance = LevenshteinDistance(picture, characterName);
            int maxLength = Math.Max(picture.Length, characterName.Length);

            return maxLength == 0 ? 1.0 : 1.0 - (double)levenshteinDistance / maxLength;
        }

        /// <summary>Compute the Levenshtein distance.</summary>
        /// <param name="s">First string to compare.</param>
        /// <param name="t">Second string to compare.</param>
        /// <returns>The Levenshtein distance.</returns>
        internal static int LevenshteinDistance(string s, string t)
        {
            int n = s.Length;
            int m = t.Length;
            int[,] d = new int[n + 1, m + 1];

            if (n == 0)
                return m;

            if (m == 0)
                return n;
            
            for (int i = 0; i <= n; d[i, 0] = i++) { }
            for (int j = 0; j <= m; d[0, j] = j++) { }

            for (int j = 1; j <= m; j++)
            {
                for (int i = 1; i <= n; i++)
                {
                    int cost = (t[j - 1] == s[i - 1]) ? 0 : 1;
                    d[i, j] = Math.Min(
                        Math.Min(d[i - 1, j] + 1, d[i, j - 1] + 1),
                        d[i - 1, j - 1] + cost);
                }
            }

            return d[n, m];
        }
    }
}

