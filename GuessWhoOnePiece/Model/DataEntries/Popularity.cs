// <copyright file="Popularity.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represent the definition of the popularity for each character.</summary>
    public class Popularity
    {
        private const string UrlLevels = "http://www.volonte-d.com/details/popularite.php";

        private static readonly List<string> ListPopularity = [];

        private static int _countLevels;

        /// <summary>Set the popularity of characters.</summary>
        /// <param name="characterNameList">List of characters.</param>
        public static void SetPopularity(List<string> characterNameList, List<Character> characterList)
        {
            var web = new HtmlWeb();
            var doc = web.Load(UrlLevels);
            var elements =
                doc.DocumentNode.SelectNodes("//*[contains(@class, 'gallery') and contains(@class, 'clearfix')]");
            if (elements.LastOrDefault() != null)
            {
                var tables = elements.Last().SelectNodes("table");
                if (tables.Count > 0)
                {
                    var table = tables.Last();
                    if (table != null)
                    {
                        var rows = table.SelectNodes("tr");
                        for (var i = 1; i < rows.Count; i++)
                        {
                            var row = rows[i];
                            var cols = row.SelectNodes("td");
                            ListPopularity.Add(cols[1].InnerText.Trim());
                        }
                    }
                }
            }

            foreach (var character in characterNameList)
            {
                string tempCharacterName = character;
                if (tempCharacterName.Contains("alias"))
                {
                    tempCharacterName = character.Replace(@"\s*\(.*?\)", "");
                }

                var position = ListPopularity.IndexOf(tempCharacterName);

                if (position == -1)
                {
                    var newCharacter = GetSimilarCharacter(tempCharacterName);

                    if (newCharacter == null)
                        position = ListPopularity.Count;
                    else 
                        position = ListPopularity.IndexOf(newCharacter);

                    if (position == -1)
                    {
                        position = ListPopularity.Count;
                    }
                }

                foreach (var characters in characterList)
                {
                    if (characters.Name.Equals(tempCharacterName))
                    {
                        for (var i = ControlRoom.NumberOfLevels; i >= 1; i--)
                        {
                            if (position <= 200 * i)
                            {
                                characters.Level = i - 1;
                            }
                        }

                        if (characters.Level == ControlRoom.NumberOfLevels + 1)
                        {
                            characters.Level = (ControlRoom.NumberOfLevels - 1);
                        }
                    }
                }

                _countLevels++;
            }
        }

        static string? GetSimilarCharacter(string character)
        {
            foreach (var popularityCharacter in ListPopularity)
            {
                character = DataControl.ExtractExceptionsPopularity(character);
                if (CalculateMatchPercentage(popularityCharacter, character) > 0.7)
                    return popularityCharacter;
            }

            return null;
        }

        static double CalculateMatchPercentage(string characterSearched, string characterName)
        {
            int levenshteinDistance = ControlRoom.LevenshteinDistance(characterSearched, characterName);
            int maxLength = Math.Max(characterSearched.Length, characterName.Length);

            return maxLength == 0 ? 1.0 : 1.0 - (double)levenshteinDistance / maxLength;
        }
    }
}
