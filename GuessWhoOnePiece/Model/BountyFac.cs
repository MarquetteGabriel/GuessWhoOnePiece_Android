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
            {
                return bountyCharacter.Contains(NoBounty) ? BountyType.Equal : BountyType.WrongUnknown;
            }

            if (bountyCharacter.Contains(NoBounty))
            {
                return BountyType.WrongUnknown;
            }

            var bounty = float.Parse(bountyCharacter.Replace("[^\\d.]", ""));
            var bountySearched = float.Parse(bountyCharacterSearched.Replace("[^\\d.]", ""));

            if (bountyCharacter.Contains("Md") && bountyCharacterSearched.Contains("Mi"))
            {
                bounty *= 1000;
            }
            else if (bountyCharacter.Contains("Mi") && bountyCharacterSearched.Contains("Md"))
            {
                bountySearched *= 1000;
            }
            else if (bountyCharacter.Contains("Md") && (!bountyCharacterSearched.Contains("Md") &&
                                                        !bountyCharacterSearched.Contains("Mi")))
            {
                bounty *= 1000000000;
            }
            else if (bountyCharacter.Contains("Mi") && (!bountyCharacterSearched.Contains("Md") &&
                                                        !bountyCharacterSearched.Contains("Mi")))
            {
                bounty *= 1000000;
            }
            else if (bountyCharacterSearched.Contains("Md") &&
                     (!bountyCharacter.Contains("Md") && !bountyCharacter.Contains("Mi")))
            {
                bountySearched *= 1000000000;
            }
            else if (bountyCharacterSearched.Contains("Mi") &&
                     (!bountyCharacter.Contains("Md") && !bountyCharacter.Contains("Mi")))
            {
                bountySearched *= 1000000;
            }

            var diff = bounty - bountySearched;

            if (diff < 0)
            {
                return BountyType.Upper;
            }

            return diff > 0 ? BountyType.Lower : BountyType.Equal;
        }
    }
}
