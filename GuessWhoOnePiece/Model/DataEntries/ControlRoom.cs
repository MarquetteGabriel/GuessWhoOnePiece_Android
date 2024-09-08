// <copyright file="ControlRoom.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using System.Collections.Concurrent;
using System.Text.RegularExpressions;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represent the control of threads for web call.</summary>
    public class ControlRoom
    {
        /// <summary>Url of the list of characters in the One Piece Fandom.</summary>
        private const string UrlFandomListCharacter = "https://onepiece.fandom.com/fr/wiki/Liste_des_Personnages_Canon";

        /// <summary>Number of levels.</summary>
        internal const int NumberOfLevels = 2;
        
        /// <summary>List of characters' name.</summary>
        private readonly List<string> _characterNameList = [];

        private int _countPercentage;
        
        /// <summary>Generate threads to get data.</summary>
        /// <returns>The complete list of characters.</returns>
        public async Task<List<Character>> GenerateThreads()
        {
            await ReceivedCharactersList();
            
            var charactersList = new ConcurrentBag<Character>();
            foreach (var characterName in _characterNameList)
            {
                var character = await DataForCharacter(SetCharacterLink(characterName), characterName);
                if (character != null)
                    charactersList.Add(character);
                await Task.Delay(65);
            }

            Popularity.SetPopularity(_characterNameList, charactersList.ToList());

            return charactersList.ToList();
        }

        /// <summary>Get the list of character from the fandom webpage and add each character into <see cref="_characterNameList"/>.</summary>
        private async Task ReceivedCharactersList()
        {
            try
            {
                var web = new HtmlWeb();
                var doc = await web.LoadFromWebAsync(UrlFandomListCharacter);
                var tables = doc.DocumentNode.SelectNodes("//div[contains(@class, 'tabber wds-tabber')]//table[contains(@class, 'wikitable')]");

                foreach (var table in tables)
                {
                    var rows = table.SelectNodes("//td");
                    foreach (var row in rows)
                    {
                        // TODO
                        var link = row.SelectSingleNode(".//a");
                        if (link != null)
                        {
                            var text = link.InnerText.Trim();
                        }

                        /*
                        var columns = row.SelectNodes("//td");
                        var value = row.FirstChild.GetAttributeValue("a", string.Empty);
                        if (columns != null && columns.Count < 6)
                        {
                            var character = DataControl.ExtractExceptions(columns[1].InnerText);
                            _characterNameList.Add(character);
                        }*/
                    }
                }
            }
            catch (Exception)
            {
                // ignored
            }
        }

        /// <summary>Get data for a specific character from its fandom page.</summary>
        /// <param name="url">The url of the character.</param>
        /// <param name="characterName">The name of the character.</param>
        /// <returns>The character.</returns>
        private async Task<Character> DataForCharacter(string url, string characterName)
        {
            try
            {
                var web = new HtmlWeb();
                var doc = await web.LoadFromWebAsync(url);
                
                const string classCharacterData = "pi-item pi-group pi-border-color";
                var characterData = string.Join(" ", doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classCharacterData}')]").Select(n => n.InnerText));
                const string classFruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
                var fruitElement = string.Join(" ", doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classFruit}')]").Select(n => n.InnerText));
                const string classType = "pi-item pi-data pi-item-spacing pi-border-color";
                const string classPicture = "pi-navigation pi-item-spacing pi-secondary-font";
                var pictureElement = doc.DocumentNode.SelectSingleNode($"//*[contains(@class, '{classPicture}')]//img")?.GetAttributeValue("src", "");
                var bountyTypeCrewElements = doc.DocumentNode.SelectNodes($"//*[contains(@class, '{classType}')]");
                HtmlNode typeElement = null!, crewElement = null!;
            
            
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

                var crew = DataControl.ExtractPatternCrew(crewElement);
                var fruit = fruitElement.Contains("Fruit du Démon");
                var type = DataControl.FixType(DataControl.ExtractPatternType(typeElement), crew);
                var bounty = DataControl.FixBounty(DataControl.ExtractPatternBounty(characterData).Replace("[.,\\s]", "").Trim(), type);
                var chapter = int.Parse(DataControl.ExtractPattern(characterData, "Chapitre (\\d+)"));
                var alived = !DataControl.ExtractPattern(characterData, "Statut : (Vivant|Décédé)").Equals("Décédé");
                var age = DataControl.ExtractPatternAge(characterData);
                crew = DataControl.FixCrew(crew, type);
                type = DataControl.FixType(type, crew);

                var characters = new Character(characterName, fruit, bounty, chapter, type, alived,
                        age, crew, pictureElement!, 2 + 1);
                _countPercentage++;

                if(_countPercentage > _characterNameList.Count)
                {
                    _countPercentage = _characterNameList.Count;
                }
                
                ManageCsv.SaveCharacterToCsv(characters);

                return characters;
            }
            catch
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
            if (matcher.Success) {
                urlCharacter = matcher.Groups[1].Value ;
            }
            const string urlFandom = "https://onepiece.fandom.com/fr/wiki/";
            return urlFandom + urlCharacter;
        }
        
        
    }
}

