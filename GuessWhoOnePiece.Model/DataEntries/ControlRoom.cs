// <copyright file="ControlRoom.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
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

        /// <summary>Percentage of acceptance between picture link and character.</summary>
        private const double AcceptanceCritera = 0.11;

        /// <summary>Pattern to have only alphanumerical letters.</summary>
        private const string Pattern = @"^[a-zA-Z0-9\s]*$";
        
        /// <summary>List of characters' name.</summary>
        private ConcurrentBag<string> _characterNameList = [];

        /// <summary>Percentage of advencement for loading characters.</summary>
        private int _countPercentage;

        private static readonly SemaphoreSlim ImageDownloadSemaphore = new SemaphoreSlim(1);

        /// <summary>Generate threads to get data.</summary>
        /// <returns>The complete list of characters.</returns>
        public async Task<List<Character>> GenerateThreads()
        {
            await ReceivedCharactersList();

            var charactersList = new System.Collections.Concurrent.ConcurrentBag<Character>();

            await Parallel.ForEachAsync(_characterNameList, new ParallelOptions { MaxDegreeOfParallelism = Environment.ProcessorCount * 2 }, async (characterName, _) =>
            {
                var character = await DataForCharacter(SetCharacterLink(characterName), characterName);
                if (character != null)
                    charactersList.Add(character);
            });

            charactersList = Popularity.SetPopularity(_characterNameList.ToList(), charactersList);
            ManageCsv.SaveCharactersToCsv(charactersList.ToList());

            return charactersList.ToList();
        }

        /// <summary>Get the list of character from the fandom webpage and add each character into <see cref="_characterNameList"/>.</summary>
        public async Task ReceivedCharactersList()
        {
            try
            {
                var web = new HtmlWeb
                {
                    OverrideEncoding = Encoding.UTF8
                };
                var doc = await web.LoadFromWebAsync(UrlFandomListCharacter);
                var tables = doc.DocumentNode.SelectNodes("//div[contains(@class, 'tabber wds-tabber')]//table[contains(@class, 'wikitable')]");

                var tasks = tables.Select(async table =>
                {
                    var rows = table.SelectNodes(".//tr");
                    if (rows != null)
                    {
                        foreach (var row in rows)
                        {
                            var link = row.SelectSingleNode("td[2]/a");
                            if (link != null)
                            {
                                var character = DataControl.ExtractExceptions(link.InnerHtml.Trim());
                                if (character == "Smoothie") continue;

                                lock (_characterNameList)
                                {
                                    _characterNameList.Add(character);
                                }
                            }
                        }
                    }
                });
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
        internal async Task<Character?> DataForCharacter(Uri url, string characterName)
        {
            try
            {
                var web = new HtmlWeb
                {
                    OverrideEncoding = System.Text.Encoding.UTF8
                };
                var doc = await web.LoadFromWebAsync(url.ToString());

                const string classCharacterData = "pi-item pi-group pi-border-color";

                var data = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classCharacterData}')]");
                var characterData = string.Join(" ", (data != null ? data.Select(n => n.InnerText) : throw new InvalidOperationException("No character data")));

                const string classFruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
                var fruitElement = string.Join(" ", doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classFruit}')]").Select(n => n.InnerText));

                var pictureElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, 'image')]//a");
                string picturePath = string.Empty;
                Task<string>? pictureDownloadTask = null;
                if (pictureElements != null)
                {
                    var pictureElement = GetPictureLink(pictureElements, characterName);
                    pictureElement = CleanWebHtmlString(pictureElement);

                    pictureDownloadTask = Task.Run(async () =>
                    {
                        await ImageDownloadSemaphore.WaitAsync();
                        try
                        {
                            return await PictureManager.DownloadImageAsync(pictureElement, characterName);
                        }
                        finally
                        {
                            ImageDownloadSemaphore.Release();
                        }
                    });
                }

                const string classType = "pi-item pi-data pi-item-spacing pi-border-color";
                var bountyTypeCrewElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classType}')]");
                HtmlNode typeElement = null!;
                HtmlNode crewElement = null!;

                characterData = CleanWebHtmlString(characterData);
                fruitElement = CleanWebHtmlString(fruitElement);


                foreach (var bountyTypeCrewElement in bountyTypeCrewElements)
                {
                    var dataSource = bountyTypeCrewElement.GetAttributeValue("data-source", "");

                    if(dataSource == "occupation")
                        typeElement = bountyTypeCrewElement;

                    if(dataSource == "affiliation")
                        crewElement = bountyTypeCrewElement;

                    if(typeElement != null && crewElement != null)
                        break;
                }

                crewElement ??= bountyTypeCrewElements.FirstOrDefault(element => element.InnerHtml.Contains("occupation", StringComparison.OrdinalIgnoreCase));
                typeElement ??= bountyTypeCrewElements.FirstOrDefault(element => element.InnerHtml.Contains("affiliation", StringComparison.OrdinalIgnoreCase));

                var crew = crewElement == null? Resources.Strings.Citizen : DataControl.ExtractCrew(crewElement, characterName); // Done.
                var type = typeElement == null ? Resources.Strings.Citizen : DataControl.ExtractPatternType(typeElement, crew); // Done.

                if (type.Equals(Resources.Strings.CelestialDragons))
                {
                    crew = Resources.Strings.CelestialDragons;
                    type = Resources.Strings.NavyType;
                }

                var fruit = fruitElement.Contains("Fruit du Démon", StringComparison.OrdinalIgnoreCase); // Done.

                var bounty = DataControl.ExtractPatternBounty(characterData, type, characterName); // Done.

                var chapterString = DataControl.ExtractPattern(characterData, "Chapitre (\\d+)");
                var chapter = !string.IsNullOrEmpty(chapterString) ? int.Parse(chapterString) : throw new InvalidOperationException("Invalid Chapter");

                var alived = !DataControl.ExtractPattern(characterData, @"Statut\s:(Vivant|Décédé)").Equals("Décédé"); // Done ?

                var age = DataControl.ExtractPatternAge(characterData, characterName); // Done.
                if(age == 0)
                    throw new InvalidOperationException("No age for the chracter");

                characterName = DataControl.ExceptionForCharacterName(characterName); // Done.

                if (pictureDownloadTask != null)
                    picturePath = await pictureDownloadTask;

                var characters = new Character(characterName, fruit, bounty, chapter, type, alived, age, crew, picturePath, NumberOfLevels + 1);
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
        internal static Uri SetCharacterLink(string characterName)
        {
            var urlCharacter = characterName.Replace(" ", "_", StringComparison.OrdinalIgnoreCase).Trim();
            var matcher = Regexs.CharacterLinkRegex().Match(urlCharacter);
            if (matcher.Success)
            {
                urlCharacter = matcher.Groups[1].Value;
            }

            const string urlFandom = "https://onepiece.fandom.com/fr/wiki/";
            return new Uri(urlFandom + urlCharacter);
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

            foreach (var picture in listOfPictures.Select(picture => picture.GetAttributeValue("href", "")))
            {
                var newPicture = picture.Replace("amp;", "", StringComparison.OrdinalIgnoreCase).Replace("&amp", "&", StringComparison.OrdinalIgnoreCase);
                if (newPicture.Contains("data:image/gif", StringComparison.OrdinalIgnoreCase) || newPicture.Contains("Site-logo", StringComparison.OrdinalIgnoreCase))
                    continue;
                if (newPicture.Contains("Manga_Post_Ellipse", StringComparison.OrdinalIgnoreCase) || newPicture.Contains("Manga_Pre_Ellipse", StringComparison.OrdinalIgnoreCase) ||
                    newPicture.Contains("Anime_Post_Ellipse", StringComparison.OrdinalIgnoreCase) || newPicture.Contains("Anime_Pre_Ellipse", StringComparison.OrdinalIgnoreCase))
                    return newPicture;

                if (newPicture.Contains(characterName))
                {
                    return newPicture;
                }
                else
                {
                    foreach (var character in characterName.Split(" "))
                    {
                        if (newPicture.Contains(WebUtility.UrlEncode(character), StringComparison.OrdinalIgnoreCase))
                            return newPicture;
                        else if (RemoveDiacritics(WebUtility.UrlDecode(newPicture)).Contains(RemoveDiacritics(character), StringComparison.OrdinalIgnoreCase))
                            return newPicture;
                        else
                        {
                            // Empty on purpose.
                        }
                    }

                    if (CalculateMatchPercentage(newPicture, characterName) > AcceptanceCritera)
                        return newPicture;
                }

                if (characterName.Equals("And", StringComparison.OrdinalIgnoreCase) && picture.Contains("Baskerville", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Belmer", StringComparison.OrdinalIgnoreCase) && picture.Contains("Bell", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Rock", StringComparison.OrdinalIgnoreCase) && picture.Contains("Yeti", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Ener", StringComparison.OrdinalIgnoreCase) && picture.Contains("Enel", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Jinbei", StringComparison.OrdinalIgnoreCase) && picture.Contains("Jinbe", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Bakkin", StringComparison.OrdinalIgnoreCase) && picture.Contains("Buckingham", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Mansherry", StringComparison.OrdinalIgnoreCase) && picture.Contains("Manshelly", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Shishilian", StringComparison.OrdinalIgnoreCase) && picture.Contains("Sicilion", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals("Suleiman", StringComparison.OrdinalIgnoreCase) && picture.Contains("Suleyman", StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else
                {
                    // Empty on purpose.
                }
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
        internal static double CalculateMatchPercentage(string picture, string characterName)
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
            
            for (int i = 0; i <= n; d[i, 0] = i++) { 
                // Empty on purpose.
                }

            for (int j = 0; j <= m; d[0, j] = j++) {
                // Empty on purpose.
            }

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

