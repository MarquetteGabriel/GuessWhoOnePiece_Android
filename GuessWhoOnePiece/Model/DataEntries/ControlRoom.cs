// <copyright file="ControlRoom.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using System.Collections.Concurrent;
using System.Diagnostics;
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
            
            ManageCsv.SaveCharactersToCsv(charactersList.ToList());

            Popularity.SetPopularity(_characterNameList, charactersList.ToList());

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
                const string classType = "pi-item pi-data pi-item-spacing pi-border-color";
                const string classPicture = "pi-navigation pi-item-spacing pi-secondary-font";
                var pictureElement = doc.DocumentNode.SelectSingleNode($"//*[contains(@class, '{classPicture}')]//img")?.GetAttributeValue("src", "").Split(";")[0];
                var bountyTypeCrewElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classType}')]");
                HtmlNode typeElement = null!, crewElement = null!;
            
                characterData = CleanWebHtmlString(characterData);
                fruitElement = CleanWebHtmlString(fruitElement);
                pictureElement = CleanWebHtmlString(pictureElement);

                foreach (var bountyTypeCrewElement in bountyTypeCrewElements)
                {
                    var dataSource = bountyTypeCrewElement.GetAttributeValue("data-source", "");
                    switch (dataSource)
                    {
                        case "occupation":
                            typeElement = bountyTypeCrewElement;
                            break;
                        case "affiliation":
                            crewElement = bountyTypeCrewElement;
                            break;
                    }
                }

                var crew = crewElement == null ? "Citizen" : DataControl.ExtractPatternCrew(crewElement);
                var fruit = fruitElement.Contains("Fruit du Démon");
                var type = typeElement == null ? "Citizen" : DataControl.FixType(DataControl.ExtractPatternType(typeElement), crew);
                var bounty = DataControl.FixBounty(DataControl.ExtractPatternBounty(characterData).Replace("[.,\\s]", "").Trim(), type);

                var chapterString = DataControl.ExtractPattern(characterData, "Chapitre (\\d+)");
                var chapter = !string.IsNullOrEmpty(chapterString) ? int.Parse(chapterString) : throw new InvalidOperationException("Invalid Chapter");

                var alived = !DataControl.ExtractPattern(characterData, @"Statut\s:(Vivant|Décédé)").Equals("Décédé");

                var age = DataControl.ExtractPatternAge(characterData);
                if(age == 0)
                    throw new InvalidOperationException("No age for the chracter");

                crew = DataControl.FixCrew(crew, type);
                type = DataControl.FixType(type, crew);

                var characters = new Character(characterName, fruit, bounty, chapter, type, alived, age, crew, pictureElement!, NumberOfLevels + 1);
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
            var urlCharacter = characterName.Replace(" ", "_").Trim();
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
        private static string CleanWebHtmlString(string? webString) => webString != null ? WebUtility.HtmlDecode(webString).Replace("\n", "").Replace("\t", "") : "";
    }
}

