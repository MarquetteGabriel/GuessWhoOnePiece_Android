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
    internal partial class DataControl
    {
        internal static string ExtractCrew(HtmlNode text, string characterName)
        {
            if (characterName.Equals("Vergo") || characterName.Equals("Senor Pink"))
                return "L'Équipage de Don Quichotte Doflamingo";
            if (characterName.Equals("Sanjuan Wolf"))
                return "L'Équipage de Barbe Noire";
            if (characterName.Equals("Surume"))
                return Resources.Strings.AlliedMugiwaraCrew;
            if (characterName.Equals("Magellan"))
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
            var affiliations = text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]/*[self::a or self::small]")
                                            ?? text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]");
            var affiliationCharacter = new List<string>();

            for (int i = 0; i < affiliations.Count; i++)
            {
                var affiliation = affiliations[i].InnerText;
                if (i + 1 < affiliations.Count && affiliations[i + 1].InnerText.Contains("anciennement", StringComparison.OrdinalIgnoreCase))
                {
                    affiliation += " " + affiliations[i + 1].InnerText;
                    i++;
                }
                affiliationCharacter.Add(affiliation);
            }

            if (affiliationCharacter.Count == 1)
            {
                var affiliation = affiliationCharacter.First();
                if (affiliation.Equals("CP9 (anciennement)"))
                    return "Cipher Pol";
                else if (affiliation.Equals("L'Équipage de Don Quichotte Doflamingo (anciennement)"))
                    return Resources.Strings.Citizen;
            }

            if (affiliationCharacter.Any(affiliation => affiliation.Equals("SWORD")))
                return Resources.Strings.NavyCrew;

            for (int i = 0; i < affiliationCharacter.Count; i++)
            {
                if (i + 1 < affiliationCharacter.Count)
                {
                    if (affiliationCharacter[i].Equals("L'Équipage du Chapeau de Paille") && affiliationCharacter[i + 1].Equals("Nain"))
                        return Resources.Strings.AlliedMugiwaraCrew;

                    if (affiliationCharacter[i].Equals("L'Équipage des Pirates du Soleil") && affiliationCharacter[i + 1].Equals("Révolutionnaires"))
                        return Resources.Strings.RevolutionaryCrew;
                }

                if (affiliationCharacter[i].Contains("Impel Down") && !affiliationCharacter[i].Contains("anciennement"))
                {
                    return Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliationCharacter[i], "");
                }
                else if (affiliationCharacter[i].Equals("(Anciennement)"))
                {
                    return Resources.Strings.PirateType;
                }
            }

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
