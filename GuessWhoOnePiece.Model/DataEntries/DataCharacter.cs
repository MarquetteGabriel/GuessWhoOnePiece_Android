// <copyright file="DataCharacter.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.DataEntries.Picture;
using HtmlAgilityPack;
using System;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Model.DataEntries
{
    public partial class ControlRoom
    {
        /// <summary>Number of levels.</summary>
        internal const int NumberOfLevels = 2;

        private static readonly SemaphoreSlim ImageDownloadSemaphore = new SemaphoreSlim(1);

        private static readonly HtmlWeb Web = new() { OverrideEncoding = Encoding.UTF8 };

        private const string OccupationValue = "occupation";
        private const string AffiliationValue = "affiliation";
        private const string Dead = "Décédé";

        private const string ClassCharacterData = "pi-item pi-group pi-border-color";
        private const string ClassFruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
        private const string ClassType = "pi-item pi-data pi-item-spacing pi-border-color";

        // Filters.
        private const string FilterCharacterData = $"//*[contains(@class, '{ClassCharacterData}')]";
        private const string FilterDevilFruit = $"//*[contains(@class, '{ClassFruit}')]";
        private const string FilterPicture = $"//*[contains(@class, 'image')]//a";
        private const string FilterBounty = $"//*[contains(@class, '{ClassType}')]";
        private const string FilterAlived = @"Statut\s:(Vivant|Décédé)";
        private const string FilterChapter = "Chapitre (\\d+)";

        // Exceptions Message.
        private const string ExceptionMessage = "No character data";
        private const string ExceptionMessageChapter = "Invalid Chapter";
        private const string ExceptionMessageAge = "No age for the chracter";

        // MemoryLeak avoided on resx.
        private const string Citizen = "Citizen";
        private const string FruitDuDemon = "Fruit du Démon";
        private const string CelestialDragons = "Celestial Dragons";
        private const string Navy = "Navy";

        private const string Space = " ";
        private const string DataSource = "data-source";

        /// <summary>Percentage of acceptance between picture link and character.</summary>
        private const double AcceptanceCritera = 0.11;

        /// <summary>Percentage of advencement for loading characters.</summary>
        private int _countPercentage;

        /// <summary>Get data for a specific character from its fandom page.</summary>
        /// <param name="url">The url of the character.</param>
        /// <param name="characterName">The name of the character.</param>
        /// <returns>The character.</returns>
        public async Task<Character?> DataForCharacter(Uri url, string characterName)
        {
            try
            {
                var doc = await Web.LoadFromWebAsync(url.ToString());

                var characterData = CleanWebHtmlString(doc.DocumentNode.SelectSingleNode(FilterCharacterData)?.InnerText)
                     ?? throw new InvalidOperationException(ExceptionMessage);

                var pictureElements = doc.DocumentNode.SelectNodes(FilterPicture);

                var fruitElement = CleanWebHtmlString(doc.DocumentNode.SelectSingleNode(FilterDevilFruit)?.InnerText)
                    ?? throw new InvalidOperationException(ExceptionMessage);
                var bountyTypeCrewElements = doc.DocumentNode.SelectNodes(FilterBounty) ?? throw new InvalidOperationException(ExceptionMessage);
                doc = null;

                // Get the fruit devil.
                var fruit = fruitElement.Contains(FruitDuDemon, StringComparison.OrdinalIgnoreCase);
                fruitElement = null;

                // Get the picture.
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
                            pictureElements = null;
                            pictureElement = null;
                        }
                    });
                }

                // Crew part.
                HtmlNode typeElement = null!;
                HtmlNode crewElement = null!;

                foreach (var bountyTypeCrewElement in bountyTypeCrewElements)
                {
                    var dataSource = bountyTypeCrewElement.GetAttributeValue(DataSource, string.Empty);

                    if (dataSource == OccupationValue)
                        typeElement = bountyTypeCrewElement;

                    if (dataSource == AffiliationValue)
                        crewElement = bountyTypeCrewElement;

                    if (typeElement != null && crewElement != null)
                        break;
                }

                crewElement ??= bountyTypeCrewElements.FirstOrDefault(element => element.InnerHtml.Contains(OccupationValue, StringComparison.OrdinalIgnoreCase));
                typeElement ??= bountyTypeCrewElements.FirstOrDefault(element => element.InnerHtml.Contains(AffiliationValue, StringComparison.OrdinalIgnoreCase));
                bountyTypeCrewElements.Clear();

                var crew = crewElement == null ? Citizen : DataControl.ExtractCrew(crewElement, characterName);
                var type = typeElement == null ? Citizen : DataControl.ExtractPatternType(typeElement, crew);
                crewElement = null;
                typeElement = null;

                if (type.Equals(CelestialDragons))
                {
                    crew = CelestialDragons;
                    type = Navy;
                }

                // Age part.
                var age = DataControl.ExtractPatternAge(characterData, characterName);
                if (age == 0)
                    throw new InvalidOperationException(ExceptionMessageAge);

                // Chapter Part.
                var chapterString = DataControl.ExtractPattern(characterData, FilterChapter);
                var chapter = !string.IsNullOrEmpty(chapterString) ? int.Parse(chapterString) : throw new InvalidOperationException(ExceptionMessageChapter);

                // Alived part.
                var alived = !DataControl.ExtractPattern(characterData, FilterAlived).Equals(Dead);

                // Bounty part.
                var bounty = DataControl.ExtractPatternBounty(characterData, type, characterName);
                characterData = null;

                // Change Name.
                characterName = DataControl.ExceptionForCharacterName(characterName);

                // Get picture path.
                string picturePath = string.Empty;
                if (pictureDownloadTask != null)
                    picturePath = await pictureDownloadTask;

                // Add characters.
                var characters = new Character(characterName, fruit, bounty, chapter, type, alived, age, crew, picturePath, NumberOfLevels + 1);
                _countPercentage++;

                if (_countPercentage > _characterNameList.Count)
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

        /// <summary>Clean string from web and html format.</summary>
        /// <param name="webString">String to clean.</param>
        /// <returns>The string cleaned.</returns>
        private static string CleanWebHtmlString(string? webString) => webString != null ? WebUtility.HtmlDecode(webString).Replace("\n", string.Empty, StringComparison.OrdinalIgnoreCase)
            .Replace("\t", string.Empty, StringComparison.OrdinalIgnoreCase) : string.Empty;
    }
}
