// <copyright file="CrewData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.Linq;

namespace GuessWhoOnePiece.Model.DataEntries
{
    internal static partial class DataControl
    {
        /// <summary>Return the crew for text.</summary>
        /// <param name="text">Text.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The extracted crew.</returns>
        internal static string ExtractCrew(HtmlNode text, string characterName)
        {
            if (characterName.Equals("Vergo", StringComparison.Ordinal) || characterName.Equals("Senor Pink", StringComparison.Ordinal))
                return "L'Équipage de Don Quichotte Doflamingo";
            if (characterName.Equals("Sanjuan Wolf", StringComparison.Ordinal))
                return "L'Équipage de Barbe Noire";
            if (characterName.Equals("Surume", StringComparison.Ordinal))
                return Resources.Strings.AlliedMugiwaraCrew;
            if (characterName.Equals("Magellan", StringComparison.Ordinal))
                return "Impel Down";

            string crew = GetCrewMapping(ExtractPatternCrew(text));

            if (crew.StartsWith("CP", StringComparison.OrdinalIgnoreCase))
                return "Cipher Pol";

            if (PirateTypeList.Any(crew.Contains))
                return crew;
            if (NavyTypeList.Any(crew.Contains))
                return crew;
            if (RevoTypeList.Any(crew.Contains))
                return Resources.Strings.RevolutionaryCrew;

            return Resources.Strings.Citizen;
        }

        /// <summary>Extract crew from text.</summary>
        /// <param name="text">Text.</param>
        /// <returns>The crew of the character.</returns>
        private static string ExtractPatternCrew(HtmlNode text)
        {
            var affiliationCharacter = SetAffiliationCharacter(text);

            var specificCrew = ExtractSpecificCrew(affiliationCharacter);
            if (specificCrew != null)
                return specificCrew;

            foreach (var affiliation in affiliationCharacter)
            {
                var cleanedAffiliation = Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliation, "").Trim();
                if (!cleanedAffiliation.Contains("anciennement") && !cleanedAffiliation.Contains("temporairement") && !cleanedAffiliation.Contains("dissous"))
                {
                    return cleanedAffiliation;
                }
            }

            foreach (var affiliation in affiliationCharacter)
            {
                var cleanedAffiliation = Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliation, "").Replace("(anciennement)", "").Trim();
                if (PirateTypeList.Any(cleanedAffiliation.Contains))
                    return cleanedAffiliation;
                if (RevoTypeList.Any(cleanedAffiliation.Contains))
                    return cleanedAffiliation;
            }

            return Resources.Strings.Citizen;
        }

        /// <summary>Set affialition possible for the character.</summary>
        /// <param name="text">Text.</param>
        /// <returns>List of affiliation possible.</returns>
        private static List<string> SetAffiliationCharacter(HtmlNode text)
        {
            var affiliations = text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]/*[self::a or self::small]")
                                ?? text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]");
            var affiliationCharacter = new List<string>();

            for (int index = 0; index < affiliations.Count; index++)
            {
                var affiliation = affiliations[index].InnerText;
                if (index + 1 < affiliations.Count && affiliations[index + 1].InnerText.Contains("anciennement", StringComparison.OrdinalIgnoreCase))
                {
                    affiliation += " " + affiliations[index + 1].InnerText;
                    index++;
                }
                affiliationCharacter.Add(affiliation);
            }

            return affiliationCharacter;
        }

        /// <summary>Extract affiliation for specific character.</summary>
        /// <param name="affiliationCharacter">List of affiliation possible.</param>
        /// <returns>Affiliation for the character.</returns>
        private static string? ExtractSpecificCrew(List<string> affiliationCharacter)
        {
            var singleCrew = ExtractSingleCrew(affiliationCharacter);    
            if(singleCrew != null)
                return singleCrew;


            if (affiliationCharacter.Any(affiliation => affiliation.Equals("SWORD")))
                return Resources.Strings.NavyCrew;

            for (int index = 0; index < affiliationCharacter.Count; index++)
            {
                var errorDataCrew = ExtractErrorDataCrew(affiliationCharacter, index);
                if(errorDataCrew != null)
                    return errorDataCrew;
            }

            return null;
        }

        /// <summary>Extract affiliation for character which are not implemented correctly in the webstite.</summary>
        /// <param name="affiliationCharacter">List of affiliation character.</param>
        /// <param name="index">Index of the list.</param>
        /// <returns>Affiliation for the character.</returns>
        private static string? ExtractErrorDataCrew(IList<string> affiliationCharacter, int index)
        {
            if (index + 1 < affiliationCharacter.Count)
            {
                if (affiliationCharacter[index].Equals("L'Équipage du Chapeau de Paille", StringComparison.Ordinal) 
                    && affiliationCharacter[index + 1].Equals("Nain", StringComparison.Ordinal))
                { 
                    return Resources.Strings.AlliedMugiwaraCrew; 
                }

                if (affiliationCharacter[index].Equals("L'Équipage des Pirates du Soleil", StringComparison.Ordinal)
                    && affiliationCharacter[index + 1].Equals("Révolutionnaires", StringComparison.Ordinal))
                {
                    return Resources.Strings.RevolutionaryCrew;
                }
            }

            if (affiliationCharacter[index].Contains("Impel Down", StringComparison.Ordinal)
                && !affiliationCharacter[index].Contains("anciennement", StringComparison.Ordinal))
            {
                return Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliationCharacter[index], "");
            }

            if (affiliationCharacter[index].Equals("(Anciennement)", StringComparison.Ordinal))
                return Resources.Strings.PirateType;

            return null;
        }

        /// <summary>Extract affiliation when the list contains only one element.</summary>
        /// <param name="affiliationCharacter">List of affiliation character.</param>
        /// <returns>Affiliation for the character.</returns>
        private static string? ExtractSingleCrew(IList<string> affiliationCharacter)
        {
            if (affiliationCharacter.Count != 1)
                return null;

            var affiliation = affiliationCharacter.First();

            if (affiliation.Equals("CP9 (anciennement)"))
                return "Cipher Pol";

            if (affiliation.Equals("L'Équipage de Don Quichotte Doflamingo (anciennement)"))
                return Resources.Strings.Citizen;

            return null;
        }

        /// <summary>Fix crew for some characters</summary>
        /// <param name="rawCrew">Raw crew from the extrated text.</param>
        /// <returns>The new crew.</returns>
        private static string GetCrewMapping(string rawCrew)
        {
            if (CrewMapping.TryGetValue(rawCrew, out var result))
                return result;

            if (NavyCrew.Contains(rawCrew))
                return Resources.Strings.NavyCrew;

            if (BigMomCrew.Contains(rawCrew))
                return Resources.Strings.BigMomCrew;

            if (BaroqueWorks.Contains(rawCrew))
                return Resources.Strings.BaroqueWorks;

            if (CrossGuild.Contains(rawCrew))
                return Resources.Strings.CrossGuild;

            if (AlliedMugiwaraCrew.Contains(rawCrew))
                return Resources.Strings.AlliedMugiwaraCrew;

            if (CitizenCrew.Contains(rawCrew))
                return Resources.Strings.Citizen;

            if (ThrillerBark.Contains(rawCrew))
                return Resources.Strings.ThrillerBark;

            if (DonQuichotte.Contains(rawCrew))
                return Resources.Strings.DoffyFamily;

            return rawCrew;
        }
    }
}
