// <copyright file="BountyData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Globalization;
using System.Text.RegularExpressions;

namespace GuessWhoOnePiece.Model.DataEntries
{
    internal static partial class DataControl
    {
        // String Values.
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

            if (characterName.Equals("Sabo", StringComparison.Ordinal))
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

                maxBounty = ComputeBounty(maxBounty, bountyPart);
            }

            return maxBounty switch
            {
                >= BountyFac.BillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.######} Md", maxBounty / BountyFac.BillionsDollarsValue),
                >= BountyFac.MillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.######} Mi", maxBounty / BountyFac.MillionsDollarsValue),
                _ => maxBounty.ToString(CultureInfo.InvariantCulture)
            };
        }

        /// <summary>Compute bounty for a character</summary>
        /// <param name="maxBounty">Actual maximum bounty for the character.</param>
        /// <param name="bountyPart">String that contains the bounty.</param>
        /// <returns>New bounty.</returns>
        private static float ComputeBounty(float maxBounty, string bountyPart)
        {
            if (bountyPart.Contains('.'))
            {
                var bountyValue = bountyPart.Replace(".", string.Empty);
                foreach (var splitBounty in bountyValue.Split(@" "))
                {
                    maxBounty = ComputeMaxBounty(maxBounty, splitBounty);
                }
            }
            else
            {
                var splitBounty = bountyPart.Replace(@" ", string.Empty);
                maxBounty = ComputeMaxBounty(maxBounty, splitBounty);
            }

            return maxBounty;
        }

        /// <summary>Compute bounty for a character</summary>
        /// <param name="maxBounty">Actual maximum bounty for the character.</param>
        /// <param name="bountyString">String that contains the bounty.</param>
        /// <returns>New bounty.</returns>
        private static float ComputeMaxBounty(float maxBounty, string bountyString)
        {
            var bountyValue = Regexs.BountyValueRegex().Match(bountyString).Value;
            if (string.IsNullOrEmpty(bountyValue))
                return maxBounty;
            return Math.Max(maxBounty, float.Parse(bountyValue));
        }
    }
}
