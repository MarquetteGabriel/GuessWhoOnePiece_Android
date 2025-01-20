// <copyright file="AgeData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace GuessWhoOnePiece.Model.DataEntries
{
    internal partial class DataControl
    {
        /// <summary>Extract age from text.</summary>
        /// <param name="input">Text.</param>
        /// <returns>The new age.</returns>
        internal static int ExtractPatternAge(string input, string characterName)
        {
            var matchChoice = Regexs.ExtractPatternAgeRegex().Match(input);

            if (!matchChoice.Success)
                return 0;

            string ageText = matchChoice.Groups[0].Value;
            ageText = Regexs.SquareBracketsBountyRegex().Replace(ageText, " ");
            ageText = Regex.Replace(ageText, @"(?<=\()[^)]*\d+[^)]*(?=\))", m =>
            {
                return Regex.Replace(m.Value, @"\d+", "").Trim();
            });

            foreach (var splitAge in Regex.Split(ageText, @":"))
            {
                if ((splitAge.Contains("And") && characterName == "Bas") ||
                    (splitAge.Contains("Kerville") && characterName == "And") ||
                    (splitAge.Contains(@"Anniversaire") && characterName == "Kerville") ||
                    (splitAge.Contains(@"Mozu") && characterName == "Kiwi") ||
                    (splitAge.Contains(@"Anniversaire") && characterName == "Mozu"))
                {
                    ageText = splitAge;
                    break;
                }

                if (MonthList.Any(splitAge.Contains))
                    return 0;
            }

            var listAge = Regexs.ExtractAgeRegex().Matches(ageText);
            var maxAge = 0;

            foreach (Match match in listAge)
            {
                string age = match.Value;

                age = age
                    .Replace(" ans", "", StringComparison.OrdinalIgnoreCase)
                    .Replace(" ", "", StringComparison.OrdinalIgnoreCase)
                    .Replace("an", "", StringComparison.OrdinalIgnoreCase);

                if (age.Contains("(espércedevie") ||
                    age.Contains("s'ilétaitvivt") ||
                    age.Contains("(estimation"))
                    continue;

                if (int.TryParse(age, out int ageValue))
                    maxAge = Math.Max(maxAge, ageValue);
            }

            return maxAge;
        }
    }
}
