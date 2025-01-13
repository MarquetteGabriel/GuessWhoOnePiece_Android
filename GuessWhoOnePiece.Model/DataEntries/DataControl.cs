// <copyright file="DataControl.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
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
          "Baroque Works", "Blue Jam", "Assassin"
        };

        /// <summary>List of cases that are Navy.</summary>
        private static readonly HashSet<string> NavyTypeList = new(StringComparer.OrdinalIgnoreCase)
        {
            "Marine", "Marines", " Marines", "Cipher Pol", "Souverain", "Conseil des 5 doyens", "CP-AIGIS0",
            "Gouvernement", "Impel", "Navy's Crew", "Juge", "Dragon Celestes"
        };

        /// <summary>List of cases that are Revolutioanry.</summary>
        private static readonly HashSet<string> RevoTypeList = new(StringComparer.OrdinalIgnoreCase)
        {
            "Armée Révolutionnaire", "Révolutionnaires", "armée révolutionnaire", "Revolutionary's Crew",
            "Armée de la Liberté"
        };

        /// <summary>List of cases that are Celestial dragons.</summary>
        private static readonly HashSet<string> dragonCelestesKeywords = new(StringComparer.OrdinalIgnoreCase)
        {
            "Dragons Celestes", "Dragons Célestes","Nobles Mondiaux"
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
        internal static string ExtractPatternBounty(string input)
        {
            var match = Regexs.ExtractPatternBountyRegex().Match(input);
            long maxBounty = 0;

            if (match.Success && !match.Groups[1].Value.Contains("Inconnue") && !match.Groups[1].Value.Contains("Aucune"))
            {
                var text = Regex.Replace(match.Groups[1].Value, @"\(.*?\)", "", RegexOptions.CultureInvariant);
                text = text.Replace(";", " ", StringComparison.OrdinalIgnoreCase)
                        .Replace(",", ".", StringComparison.OrdinalIgnoreCase)
                        .Replace("(", "", StringComparison.OrdinalIgnoreCase)
                        .Replace(")", "", StringComparison.OrdinalIgnoreCase);

                foreach (var bounty in Regexs.SquareBracketsBountyRegex().Split(text))
                {
                    if(bounty.Contains("."))
                    {
                        var bountys = bounty.Replace(".", "").Split(@" ");
                        foreach (var splitedBounty in bountys)
                        {
                            var splitdBounty = Regexs.BountyValueRegex().Match(splitedBounty).Value;
                            if (string.IsNullOrEmpty(splitdBounty))
                                continue;
                            maxBounty = Math.Max(maxBounty, long.Parse(splitdBounty));
                        }
                    }
                    else
                    {
                        var splitBounty = bounty.Replace(" ", "");
                        splitBounty = Regexs.BountyValueRegex().Match(splitBounty).Value;
                        if (string.IsNullOrEmpty(splitBounty))
                            continue;
                        maxBounty = Math.Max(maxBounty, long.Parse(splitBounty));
                    }
                }
            }

            return maxBounty == 0 ? "" : maxBounty.ToString(CultureInfo.InvariantCulture);
        }

        /// <summary>Fix bounty for specific types.</summary>
        /// <param name="bounty">Bounty of the character.</param>
        /// <param name="type">Type of the character.</param>
        /// <returns>The new bounty.</returns>
        internal static string FixBounty(string bounty, string type)
        {
            var typeCharacterValue = NavyTypeList.FirstOrDefault(navyType => type.Contains(navyType));
            if (!string.IsNullOrEmpty(typeCharacterValue))
                type = "Navy";

            if (type.Equals("Navy", StringComparison.OrdinalIgnoreCase) || type.Equals(Resources.Strings.Citizen))
            {
                return "0";
            }
            else if (string.IsNullOrEmpty(bounty) || bounty.Equals("Inconnue", StringComparison.OrdinalIgnoreCase)
                || bounty.Equals("Aucune", StringComparison.OrdinalIgnoreCase))
            {
                return "Unknown";
            }
            else
            {
                var numBounty = double.Parse(bounty, CultureInfo.InvariantCulture);
                return numBounty switch
                {
                    >= BountyFac.BillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.###} Md", numBounty / BountyFac.BillionsDollarsValue)
                        .Replace("[,.]0+ Md", " Md", StringComparison.OrdinalIgnoreCase),
                    >= BountyFac.MillionsDollarsValue => string.Format(CultureInfo.CurrentCulture, "{0:0.###} Mi", numBounty / BountyFac.MillionsDollarsValue)
                        .Replace("[,.]0+ Mi", " Mi", StringComparison.OrdinalIgnoreCase),
                    _ => bounty
                };
            }
        }

        /// <summary>Fix type for specific types.</summary>
        /// <param name="value">Type of the character.</param>
        /// <param name="crew">Crew of the character.</param>
        /// <returns>The new type.</returns>
        internal static string FixType(string value, string crew)
        {
            if (crew.Contains("Celestial Dragons", StringComparison.OrdinalIgnoreCase))
            {
                return "Navy";
            }
            else if (value.Contains("Dragon Celestes", StringComparison.OrdinalIgnoreCase))
            {
                return value;
            }
            else if (crew.Equals(Resources.Strings.Citizen, StringComparison.OrdinalIgnoreCase))
            {
                return crew;
            }
            else
            {
                // Empty on purpose.
            }

            var typeCharacterValue = PirateTypeList.FirstOrDefault(crew.Contains);
            if (!string.IsNullOrEmpty(typeCharacterValue))
                value = "Pirate";

            typeCharacterValue = NavyTypeList.FirstOrDefault(crew.Contains);
            if (!string.IsNullOrEmpty(typeCharacterValue))
                value = "Navy";

            typeCharacterValue = RevoTypeList.FirstOrDefault(crew.Contains);
            if (!string.IsNullOrEmpty(typeCharacterValue))
                value = "Revolutionary";

            return value;
        }

        /// <summary>Fix crew for specific character.</summary>
        /// <param name="rawCrew">Crew of the character.</param>
        /// <param name="type">Type of the character.</param>
        /// <returns>The new crew.</returns>
        internal static string FixCrew(string rawCrew, string type)
        {
            if(type.Equals("Dragon Celestes"))
                rawCrew = type;

            rawCrew = rawCrew.Replace("\\s+$", "", StringComparison.OrdinalIgnoreCase);
            if (rawCrew.StartsWith("CP", StringComparison.OrdinalIgnoreCase))
            {
                return "Cipher Pol";
            }
            else if (string.IsNullOrEmpty(rawCrew))
            {
                return type;
            }
            else if (type.Equals("Revolutionary", StringComparison.OrdinalIgnoreCase))
            {
                return "Revolutionary's Crew";
            }
            else if (type.Equals(Resources.Strings.Citizen))
            {
                return Resources.Strings.Citizen;
            }
            else
            {
                return rawCrew switch
                {
                    "Dragon Celestes" or "Dragons Célestes" => "Celestial Dragons",
                    "Revolutionary" => "Revolutionary's Crew",
                    "Marine" or "Marines" or " Marines" => "Navy's Crew",
                    "L'Équipage du Firetank" => "L'Équipage du Fire Tank",
                    "Subordonné de L'Équipage de Barbe Blanche" => "L'Équipage de Barbe Blanche",
                    "La Grande Flotte du Chapeau de Paille" or "Alliance de l'Équipage du Chapeau de Paille"
                        or "Flotte de Happou" or "L'Équipage d'Idéo" or "Erbaf" or "L'Équipage des Magnifiques Pirates"
                        or "L'Équipage d'Ideo" or "Famille Chinjao" => "Allié de L'Équipage du Chapeau de Paille",
                    "Équipage de Big Mom" or "Famille Charlotte" => "L'Équipage de Big Mom",
                    "Spiders Café" or "Spider's Café" => "Baroque Works",
                    "Capitaine de l'Equipage de Caribou" => "L'Équipage de Caribou",
                    "L'Équipage de Barbe Brune(dissout)" or "César Clown" or "César Clown (espionnage)"
                        or "L'Équipage du New Age" or "L'Équipage de Barbe Brune" => "L'Équipage de Don Quichotte Doflamingo",
                    "L'Équipage des Pirates Volants" => "L'Équipage des Nouveaux Hommes-Poissons",
                    "Gecko Moria" or "Hogback" or "Dracule Mihawk" => "Thriller Bark",
                    "Alliance Baggy et Alvida" or "L'Équipage du Clown" => "Cross Guild",
                    "L'Équipage du Capitaine Usopp" or "Voleurs d'Atamayama" or "Zou" or "Pays de Wa" 
                    or "Phare du Cap des Jumeaux" => Resources.Strings.Citizen,
                    "Neutre" => "Pirate",
                    _ => rawCrew
                };
            }
        }

        /// <summary>Extract crew from text.</summary>
        /// <param name="text">Text.</param>
        /// <returns>The crew of the character.</returns>
        internal static string ExtractPatternCrew(HtmlNode text)
        {
            try
            {
                var affiliationCharacter = new List<string>();
                var affiliations = text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]/*[self::a or self::small]");

                if (affiliations == null)
                {
                    affiliations = text.SelectNodes(@"./*[contains(@class, 'pi-data-value')]");
                    if(affiliations == null)
                        return Resources.Strings.Citizen;
                }
                    

                for (int i = 0; i < affiliations.Count; i++)
                {
                    var affiliation = affiliations[i].InnerText;
                    if (i+1 < affiliations.Count && affiliations[i+1].InnerText.Contains("anciennement"))
                    {
                        affiliation += " " + affiliations[i + 1].InnerText;
                        i++;
                    }
                    affiliationCharacter.Add(affiliation);
                }

                foreach (var affiliation in affiliationCharacter)
                {
                    var cleanedAffiliation = Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliation, "");
                    if (!cleanedAffiliation.Contains("anciennement") && !cleanedAffiliation.Contains("temporairement") && !cleanedAffiliation.Contains("dissous"))
                    {
                        return cleanedAffiliation;
                    }
                }

                foreach (var affiliation in affiliationCharacter)
                {
                    var cleanedAffiliation = Regexs.ExtractRedirectLinkFromBracketsRegex().Replace(affiliation, "").Replace("(anciennement)", "").Trim();

                    var typeCharacterValue = PirateTypeList.FirstOrDefault(cleanedAffiliation.Contains);
                    if (!string.IsNullOrEmpty(typeCharacterValue))
                        return cleanedAffiliation;

                    typeCharacterValue = RevoTypeList.FirstOrDefault(cleanedAffiliation.Contains);
                    if (!string.IsNullOrEmpty(typeCharacterValue))
                        return cleanedAffiliation;
                }

                return Resources.Strings.Citizen;
            }
            catch (Exception)
            {
                return Resources.Strings.Citizen;
            }
        }

        /// <summary>Extract age from text.</summary>
        /// <param name="input">Text.</param>
        /// <returns>The new age.</returns>
        internal static int ExtractPatternAge(string input)
        {
            var matchChoice = Regexs.ExtractPatternAgeRegex().Match(input);
            var maxAge = 0;
            if (matchChoice.Success)
            {
                var listAge = Regexs.ExtractAgeRegex().Matches(matchChoice.Groups[0].Value);
                foreach (var age in listAge.Where(age => !string.IsNullOrEmpty(age.ToString())).Select(age => age.ToString()
                .Replace(" ans", "", StringComparison.OrdinalIgnoreCase).Replace(" ", "", StringComparison.OrdinalIgnoreCase)))
                {
                    if (age.Contains("(espérancedevieeffectuée)"))
                        continue;
                    maxAge = Math.Max(maxAge, int.Parse(age));
                }
            }
            return maxAge;
        }

        /// <summary>Extract type from text.</summary>
        /// <param name="text">Text.</param>
        /// <returns>The new type.</returns>
        internal static string ExtractPatternType(HtmlNode text)
        {
            try
            {
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
                var typeCharacter = Resources.Strings.Citizen;
                foreach (var typeData in typeCharacters.Select(typeData => typeData.Replace("\\[.*?]\\s*$", "", StringComparison.OrdinalIgnoreCase))
                             .Where(typeData => !typeData.Contains("anciennement", StringComparison.OrdinalIgnoreCase)
                             && !typeData.Contains("temporairement", StringComparison.OrdinalIgnoreCase)))
                {
                    if (string.IsNullOrEmpty(typeData))
                    {
                        typeCharacter = Resources.Strings.Citizen;
                        continue;
                    }
                    if (dragonCelestesKeywords.Any(keyword => typeData.Contains(keyword, StringComparison.OrdinalIgnoreCase)))
                    {
                        return "Dragon Celestes";
                    }


                    var typeCharacterValue = PirateTypeList.FirstOrDefault(typeData.Contains);
                    if (!string.IsNullOrEmpty(typeCharacterValue))
                        typeCharacter = "Pirate";

                    typeCharacterValue = NavyTypeList.FirstOrDefault(navyType => typeData.Contains(navyType) && !typeData.Contains("Prisonnier"));
                    if (!string.IsNullOrEmpty(typeCharacterValue))
                        typeCharacter = "Navy";

                    typeCharacterValue = RevoTypeList.FirstOrDefault(typeData.Contains);
                    if (!string.IsNullOrEmpty(typeCharacterValue))
                        typeCharacter = "Revolutionary";
                }
                return typeCharacter;
            }
            catch
            {
                return Resources.Strings.Citizen;
            }
        }

        /// <summary>Change value for specific character.</summary>
        /// <param name="character">The name of the character.</param>
        /// <returns>The new character name.</returns>
        internal static string ExtractExceptions(string character)
        {
            return character switch
            {
                "Chadros Higelyges" => "Barbe Brune",
                "Jabra" => "Jabura",
                "Tama" => "Kurozumi Tama",
                "Kaku (Wano)" => "Kaku",
                "Enel" => "Ener",
                "Buckingham Stussy" => "Bakkin",
                _ => character
            };
        }

        /// <summary>Change value for specific character for popularity ranking.</summary>
        /// <param name="character">The name of the character.</param>
        /// <returns>The new character name.</returns>
        internal static string ExtractExceptionsPopularity(string character)
        {
            if (character.Contains("Don Quichotte", StringComparison.OrdinalIgnoreCase))
            {
                return character.Replace("Don Quichotte", "Donquixote", StringComparison.OrdinalIgnoreCase);
            }

            if (character.Contains("Alber", StringComparison.OrdinalIgnoreCase))
            {
                return "King";
            }

            if (character.Contains("Linlin", StringComparison.OrdinalIgnoreCase))
            {
                return "Big Mom";
            }

            if (character.Contains("Galdino", StringComparison.OrdinalIgnoreCase))
            {
                return "Mr 3";
            }

            if (character.Contains("Marshall D. Teach", StringComparison.OrdinalIgnoreCase))
            {
                return "Barbe Noire";
            }

            if (character.Contains("Edward Newgate", StringComparison.OrdinalIgnoreCase))
            {
                return "Barbe Blanche";
            }

            return character;
        }

        /// <summary>Change charater name to accept more possibilites.</summary>
        internal static string ExceptionForCharacterName(string characterName)
        {
            return characterName switch
            {
                "Aramaki" => "Aramaki / Ryokugyû",
                "Brannew" => "Brand new",
                "Borsalino" => "Borsalino / Kizaru",
                "Bentham" => "Bentham / Mr. 2",
                "Barbe Brune" => "Barbe Brune / Chadros Higelyges",
                "Bakkin" => "Bakkin / Buckingham Stussy",
                "Babe" => "Babe / Mr. 4",
                "Edward Newgate" => "Edward Newgate / Barbe Blanche",
                "Edward Weevil" => "Edward Weeble",
                "Charlotte Linlin" => "Charlotte Linlin / Big Mom",
                "Daz Bones" => "Daz Bones / Mr. 1",
                "Drophy" => "Drophy / Miss Merry Christmas",
                _ => characterName
            };
        }
    }
}
