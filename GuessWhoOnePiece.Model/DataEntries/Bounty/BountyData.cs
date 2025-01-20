// <copyright file="BountyData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Globalization;
using System.Text.RegularExpressions;

namespace GuessWhoOnePiece.Model.DataEntries
{
    internal partial class DataControl
    {

        private const string InconnueValue = "Inconnue";
        private const string AucuneValue = "Aucune";
        private const string TextRegex = @"\(.*?\)";

        /// <summary>Extract bounty from text.</summary>
        /// <param name="input">Text.</param>
        /// <returns>Bounty of the character.</returns>
        internal static string ExtractPatternBounty(string input, string type, string characterName)
        {
            if (type.Equals(Resources.Strings.NavyType, StringComparison.OrdinalIgnoreCase) ||
                type.Equals(Resources.Strings.Citizen, StringComparison.OrdinalIgnoreCase))
                return "0";

            if (characterName.Equals("Sabo"))
                return "602 Mi";

            var match = Regexs.ExtractPatternBountyRegex().Match(input);
            if (!match.Success || match.Groups[1].Value.Contains(InconnueValue) || match.Groups[1].Value.Contains(AucuneValue))
                return Resources.Strings.Unknown;

            float maxBounty = 0;
            var text = Regex.Replace(match.Groups[1].Value,TextRegex, string.Empty, RegexOptions.CultureInvariant);
            text = text.Replace(";", " ", StringComparison.OrdinalIgnoreCase)
                        .Replace(",", ".", StringComparison.OrdinalIgnoreCase)
                        .Replace("(", string.Empty, StringComparison.OrdinalIgnoreCase)
                        .Replace(")", string.Empty, StringComparison.OrdinalIgnoreCase)
                        .Replace('\u00A0', ' ');

            foreach (var bountyPart in Regexs.SquareBracketsBountyRegex().Split(text))
            {
                if (string.IsNullOrEmpty(bountyPart))
                    continue;

                if (bountyPart.Contains('.'))
                {
                    var bountyValue = bountyPart.Replace(".", string.Empty);
                    foreach (var splitBounty in bountyValue.Split(@" "))
                    {
                        var splitdBounty = Regexs.BountyValueRegex().Match(splitBounty).Value;
                        if (string.IsNullOrEmpty(splitdBounty))
                            continue;
                        maxBounty = Math.Max(maxBounty, float.Parse(splitdBounty));
                    }
                }
                else
                {
                    var splitBounty = bountyPart.Replace(@" ", string.Empty);
                    splitBounty = Regexs.BountyValueRegex().Match(splitBounty).Value;
                    if (string.IsNullOrEmpty(splitBounty))
                        continue;
                    maxBounty = Math.Max(maxBounty, float.Parse(splitBounty));
                }
            }

            return maxBounty switch
            {
                >= BountyFac.BillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.######} Md", maxBounty / BountyFac.BillionsDollarsValue),
                >= BountyFac.MillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.######} Mi", maxBounty / BountyFac.MillionsDollarsValue),
                _ => maxBounty.ToString(CultureInfo.InvariantCulture)
            };
        }
    }
}
