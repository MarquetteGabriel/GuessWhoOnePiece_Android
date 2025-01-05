// <copyright file="BountyFac.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model
{
    /// <summary>Represents the manage of bounties before being printed.</summary>
    public class BountyFac
    {
        /// <summary>The string to return when there is no bounty.</summary>
        private const string NoBounty = "Unknown";

        /// <summary>Get the bounty and return how is the current bounty among the other one.</summary>
        /// <param name="characters">The character entered.</param>
        /// <param name="characterSearched">The character to find.</param>
        /// <returns>The position of the bounty.</returns>
        public static BountyType WhatBounty(Character characters, Character characterSearched)
        {
            var bountyCharacter = characters.Bounty;
            var bountyCharacterSearched = characterSearched.Bounty;

            if (bountyCharacterSearched.Contains(NoBounty))
                return bountyCharacter.Contains(NoBounty) ? BountyType.Equal : BountyType.WrongUnknown;

            if (bountyCharacter.Contains(NoBounty))
                return BountyType.WrongUnknown;

            var bountyCharacterValue = float.Parse(bountyCharacter.Replace("Md", "", StringComparison.OrdinalIgnoreCase)
                .Replace("Mi", "", StringComparison.OrdinalIgnoreCase).Replace(" ", "", StringComparison.OrdinalIgnoreCase));
            var bountyCharacterSearchedValue = float.Parse(bountyCharacterSearched.Replace("Md", "", StringComparison.OrdinalIgnoreCase)
                .Replace("Mi", "", StringComparison.OrdinalIgnoreCase).Replace(" ", "", StringComparison.OrdinalIgnoreCase));

            if (bountyCharacter.Contains("Mi"))
                bountyCharacterValue *= 1000000;
            else if (bountyCharacter.Contains("Md"))
                bountyCharacterValue *= 1000000000;

            if (bountyCharacterSearched.Contains("Mi"))
                bountyCharacterSearchedValue *= 1000000;
            else if (bountyCharacterSearched.Contains("Md"))
                bountyCharacterSearchedValue *= 1000000000;
            
            var diff = bountyCharacterValue - bountyCharacterSearchedValue;

            if (diff < 0)
            {
                return BountyType.Upper;
            }

            return diff > 0 ? BountyType.Lower : BountyType.Equal;
        }
    }
}
