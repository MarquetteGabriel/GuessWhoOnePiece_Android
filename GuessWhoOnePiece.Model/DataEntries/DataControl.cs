// <copyright file="DataControl.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text.RegularExpressions;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represents the management of data given by web scrap.</summary>
    internal static partial class DataControl
    {
        /// <summary>List of cases that are Pirates.</summary>
        private static readonly HashSet<string> PirateTypeList = new(StringComparer.OrdinalIgnoreCase)
        {
          "Pirate", "Pirates", "Bandit", "Bandits", "Charlotte", "Big Mom", "Flotte de Happou", "Clan", "Moria",
          "Équipage", "Edward", "César", "Equipage", "Mihawk", "Thriller", "Mads", "New Comer Land", "Spiders Café", 
          "Baroque Works", "Blue Jam", "Assassin", "Zo", "Kujas", "Cross Guild", "Ligue des Primates", "Grand Corsaire"
        };

        /// <summary>List of cases that are Navy.</summary>
        private static readonly HashSet<string> NavyTypeList = new(StringComparer.OrdinalIgnoreCase)
        {
            "Marine", "Marines", " Marines", "Cipher Pol", "Souverain", "Conseil des 5 doyens", "CP-AIGIS0",
            "Gouvernement", "Impel", "Navy's Crew", "Juge", "Dragon Celestes", "CP9", "Seraphim", "CP6", "CP7", 
            "Celestial Dragons", "Cinq Doyens"
        };

        /// <summary>List of cases that are Revolutioanry.</summary>
        private static readonly HashSet<string> RevoTypeList = new(StringComparer.OrdinalIgnoreCase)
        {
            "Armée Révolutionnaire", "Révolutionnaires", "armée révolutionnaire", "Revolutionary's Crew",
            "Armée de la Liberté","Revolutionary's Army"
        };

        /// <summary>List of cases that are Celestial dragons.</summary>
        private static readonly HashSet<string> dragonCelestesKeywords = new(StringComparer.OrdinalIgnoreCase)
        {
            "Dragons Celestes", "Dragons Célestes","Nobles Mondiaux"
        };

        /// <summary>List of months.</summary>
        private static readonly HashSet<string> MonthList = new(StringComparer.OrdinalIgnoreCase)
        {
            "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", 
            "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        };

        private static readonly Dictionary<string, string> CrewMapping = new Dictionary<string, string>
        {
            { "L'Équipage du Firetank", "L'Équipage du Fire Tank" },
            { "Subordonné de L'Équipage de Barbe Blanche", "L'Équipage de Barbe Blanche" },
            { "Capitaine de l'Equipage de Caribou", "L'Équipage de Caribou" },
            { "Punk Hazard", "L'Équipage aux Cent Bêtes" },
            { "Neutre", Resources.Strings.PirateType },
            { "L'Équipage des Pirates Volants", "L'Équipage des Nouveaux Hommes-Poissons"},
            { "Grand Corsaire", "Edward Weeble"}
        };

        private static readonly Dictionary<string, string> CharacterNameMappings = new()
        {
            { "Aramaki", "Aramaki / Ryokugyû" },
            { "Brannew", "Brand new" },
            { "Borsalino", "Borsalino / Kizaru" },
            { "Bentham", "Bentham / Mr. 2" },
            { "Barbe Brune", "Barbe Brune / Chadros Higelyges" },
            { "Bakkin", "Bakkin / Buckingham Stussy" },
            { "Babe", "Babe / Mr. 4" },
            { "Edward Newgate", "Edward Newgate / Barbe Blanche" },
            { "Edward Weevil", "Edward Weeble" },
            { "Charlotte Linlin", "Charlotte Linlin / Big Mom" },
            { "Daz Bones", "Daz Bones / Mr. 1" },
            { "Drophy", "Drophy / Miss Merry Christmas" },
            { "Galdino", "Galdino / Mr. 3" },
            { "Kiku", "Kiku / Kikunojo" },
            { "Marianne", "Marianne / Miss GoldenWeek" },
            { "Marshall D. Teach", "Marshall D. Teach / Barbe Noire" },
            { "Mikita", "Mikita / Miss Valentine" },
            { "Zala", "Zala / Miss Doublefinger" },
            { "Sakazuki", "Sakazuki / Akainu" }
        };

        private static readonly HashSet<string> ThrillerBark = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Gecko Moria", "Hogback", "Dracule Mihawk" 
        };

        private static readonly HashSet<string> CelestialDragons = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Dragon Celestes", "Dragons Célestes" 
        };

        private static readonly HashSet<string> NavyCrew = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Marine", "Marines", " Marines"
        };
        
        private static readonly HashSet<string> BigMomCrew = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Équipage de Big Mom", "Famille Charlotte"
        };
        
        private static readonly HashSet<string> BaroqueWorks = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Spiders Café", "Spider's Café" 
        };
        
        private static readonly HashSet<string> CrossGuild = new(StringComparer.OrdinalIgnoreCase)
        { 
            "Alliance Baggy et Alvida", "L'Équipage du Clown", "Alliance Baggy et Alvida/Les Pirates d'Expédition" 
        };

        private static readonly HashSet<string> DonQuichotte = new(StringComparer.OrdinalIgnoreCase)
        {
            "L'Équipage de Barbe Brune", "L'Équipage du New Age", "César Clown"
        };
        
        private static readonly HashSet<string> AlliedMugiwaraCrew = new(StringComparer.OrdinalIgnoreCase)
        { 
            "La Grande Flotte du Chapeau de Paille", "Alliance de l'Équipage du Chapeau de Paille", "Flotte de Happou",
            "L'Équipage d'Idéo", "Erbaf", "L'Équipage des Magnifiques Pirates", "L'Équipage d'Ideo", "Famille Chinjao" ,
            "Grande Flotte du Chapeau de Paille"
        };
        
        private static readonly HashSet<string> CitizenCrew = new(StringComparer.OrdinalIgnoreCase)
        { 
            "L'Équipage du Capitaine Usopp", "Voleurs d'Atamayama", "Zou", "Pays de Wa", "Phare du Cap des Jumeaux",
            "Clan des D.", "Principauté de Mokomo", "Alabasta", "Takoyaki 8", "Royaume maléfique de Black Drum",
            "Alliance des Ninjas-Pirates-Minks-Samouraïs", "Baratie", "Bar de l'Arnaque" 
        };


        /// <summary>Extract data from specific pattern.</summary>
        /// <param name="input">Text to extract.</param>
        /// <param name="pattern">Pattern which select data.</param>
        /// <returns>The text extraced.</returns>
        internal static string ExtractPattern(string input, string pattern)
        {
            var match = Regex.Match(input, pattern);
            return match.Success ? match.Groups[1].Value : "";
        }

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
            if (!match.Success || match.Groups[1].Value.Contains("Inconnue") || match.Groups[1].Value.Contains("Aucune"))
                return Resources.Strings.Unknown;

            float maxBounty = 0;
            var text = Regex.Replace(match.Groups[1].Value, @"\(.*?\)", "", RegexOptions.CultureInvariant);
            text = text.Replace(";", " ", StringComparison.OrdinalIgnoreCase)
                        .Replace(",", ".", StringComparison.OrdinalIgnoreCase)
                        .Replace("(", "", StringComparison.OrdinalIgnoreCase)
                        .Replace(")", "", StringComparison.OrdinalIgnoreCase)
                        .Replace('\u00A0', ' ');

            foreach (var bountyPart in Regexs.SquareBracketsBountyRegex().Split(text))
            {
                if (string.IsNullOrEmpty(bountyPart))
                    continue;

                if(bountyPart.Contains('.'))
                {
                    var bountyValue = bountyPart.Replace(".", "");
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
                    var splitBounty = bountyPart.Replace(@" ", "");
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

            if(affiliationCharacter.Count == 1)
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
                if(i+1 < affiliationCharacter.Count)
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

                if(MonthList.Any(splitAge.Contains))
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

                if(int.TryParse(age, out int ageValue))
                    maxAge = Math.Max(maxAge, ageValue);
            }

            return maxAge;
        }

        /// <summary>Extract type from text.</summary>
        /// <param name="text">Text.</param>
        /// <returns>The new type.</returns>
        internal static string ExtractPatternType(HtmlNode text, string crew)
        {
            if (crew.Equals(Resources.Strings.Citizen))
                return Resources.Strings.Citizen;
            if (crew.Equals(Resources.Strings.RevolutionaryCrew))
                return Resources.Strings.RevolutionaryType;
            if (PirateTypeList.Any(crew.Contains))
                return Resources.Strings.PirateType;

            var typeCharacters = new List<string>();
            var types = text.SelectNodes(".//*[contains(@class, 'pi-data-value')]")
                .Select(node => node.InnerText)
                .SelectMany(html => Regexs.SplitPatternType().Split(html))
                .ToArray();

            foreach (var type in types)
            {
                var docSmallDatas = new HtmlDocument();
                docSmallDatas.LoadHtml(type);
                var smallText = docSmallDatas.DocumentNode.SelectSingleNode("//small")?.InnerText ?? "";
                var cleanedType = Regexs.ContentBetweenBracketsRegex().Replace(type, "").Trim() + smallText;
                typeCharacters.AddRange(cleanedType.Split(new[] { ";", "," }, StringSplitOptions.RemoveEmptyEntries));
            }

            var filteredTypeCharacters = typeCharacters
                .Select(typeData => typeData.Replace("\\[.*?]\\s*$", "", StringComparison.OrdinalIgnoreCase))
                .Where(typeData => !typeData.Contains("anciennement", StringComparison.OrdinalIgnoreCase) &&
                                    !typeData.Contains("temporairement", StringComparison.OrdinalIgnoreCase));

            foreach (var typeData in filteredTypeCharacters)
            {
                if (dragonCelestesKeywords.Any(keyword => typeData.Contains(keyword, StringComparison.OrdinalIgnoreCase)))
                {
                    return Resources.Strings.CelestialDragons;
                }
            }
                
            return Resources.Strings.NavyType;
        }

        /// <summary>Change value for specific character for popularity ranking.</summary>
        /// <param name="character">The name of the character.</param>
        /// <returns>The new character name.</returns>
        internal static string ExtractExceptionsPopularity(string character)
        {
            if (character.Contains("Don Quichotte", StringComparison.OrdinalIgnoreCase))
                return character.Replace("Don Quichotte", "Donquixote", StringComparison.OrdinalIgnoreCase);
            
            if (character.Contains("Alber", StringComparison.OrdinalIgnoreCase))           
                return "King";            

            if (character.Contains("Linlin", StringComparison.OrdinalIgnoreCase))
                return "Big Mom";

            if (character.Contains("Galdino", StringComparison.OrdinalIgnoreCase))            
                return "Mr 3";

            if (character.Contains("Marshall D. Teach", StringComparison.OrdinalIgnoreCase))
                return "Barbe Noire";

            if (character.Contains("Edward Newgate", StringComparison.OrdinalIgnoreCase))
                return "Barbe Blanche";

            return character;
        }

        /// <summary>Change charater name to accept more possibilites.</summary>
        internal static string ExceptionForCharacterName(string characterName)
        {
            return CharacterNameMappings.TryGetValue(characterName, out var mappedName)
                ? mappedName
                : characterName;
        }
    }
}
