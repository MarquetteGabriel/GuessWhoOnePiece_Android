﻿// <copyright file="ControlRoom.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using System.Globalization;
using System.Text.RegularExpressions;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    public class DataControl
    {
        private static readonly List<string> PirateTypeList =
    [
      "Pirate", "Pirates", "Bandit", "Bandits", "Charlotte", "Big Mom", "Flotte de Happou", "Clan", "Moria", 
      "Équipage", "Edward", "César", "Equipage", "Mihawk", "Thriller", "Mads", "New Comer Land", "Spiders Café"
    ];
    private static readonly List<string> NavyTypeList = 
    [
        "Marine", "Marines", " Marines", "Cipher Pol", "Souverain", "Conseil des 5 doyens", "CP-AIGIS0",
        "Gouvernement", "Impel", "Navy's Crew", "Juge"
    ];
    private static readonly List<string> RevoTypeList = 
    [
        "Armée Révolutionnaire", "Révolutionnaires", "armée révolutionnaire", "Revolutionary's Crew", 
        "Armée de la Liberté"
    ];


    internal static string ExtractPattern(string input, string pattern) 
    {
        var match = Regex.Match(input, pattern);
        return match.Success ? match.Groups[1].Value : "";
    }

    internal static string ExtractPatternBounty(string input)
    {
        const string pattern = "Prime : (.*?)(Statut|Anniversaire|Âge)";
        var match = Regex.Match(input, pattern);
        long maxBounty = 0;
        if(match.Success)
        {
            if (!match.Groups[1].Value.Contains("Inconnue") && !match.Groups[1].Value.Contains("Aucune"))
            {
                var text = match.Groups[1].Value.Replace(@"(?<!\])\s", ".").Replace(";", " ").Replace("\\(", "");
                text = text.Replace(@"\[.*?\]", "").Replace(",", ".").Replace(@"\(.*?\)", "");
                var bountys = text.Split("\\s+");
                foreach (var bounty in bountys)
                {
                    maxBounty = Math.Max(maxBounty, long.Parse(bounty.Replace("\\.", "")));

                }
            }
        }

        return maxBounty == 0 ? "" : maxBounty.ToString();
    }

    internal static string FixBounty(string bounty, string type)
    {
        foreach (var navyType in NavyTypeList.Where(navyType => type.Contains(navyType)))
        {
            type = "Navy";
        }

        if(type.Equals("Navy") || type.Equals("Citizen"))
        {
            return "0";
        }
        else if(bounty.Equals("") || bounty.Equals("Inconnue") || bounty.Equals("Aucune"))
        {
            return "Unknown";
        }
        else
        {
            var numBounty = double.Parse(bounty);
            return numBounty switch
            {
                >= 1_000_000_000 => string.Format(CultureInfo.CurrentCulture, "{0:0.###} Md", numBounty / 1_000_000_000)
                    .Replace("[,.]0+ Md", " Md"),
                >= 1_000_000 => string.Format(CultureInfo.CurrentCulture, "{0:0.###} Mi", numBounty / 1_000_000)
                    .Replace("[,.]0+ Mi", " Mi"),
                _ => bounty
            };
        }
    }

    internal static string FixType(string value, string crew)
    {
        if(crew.Contains("Celestial Dragons"))
        {
            return "Navy";
        }
        else if (value.Contains("Dragon Celestes")) {
            return value;
        }

        foreach (var pirateType in PirateTypeList.Where(crew.Contains))
        {
            value = "Pirate";
        }
        foreach (var navyType in NavyTypeList.Where(crew.Contains)) {
            value = "Navy";
        }
        foreach (var revoType in RevoTypeList.Where(crew.Contains)) {
            value = "Revolutionary";
        }

        return value;
    }

    internal static string FixCrew(string rawCrew, string type)
    {
        rawCrew = rawCrew.Replace("\\s+$", "");
        if(rawCrew.StartsWith("CP"))
        {
            return "Cipher Pol";
        }
        else if(string.IsNullOrEmpty(rawCrew))
        {
            return type;
        }
        else if (type.Equals("Citizen"))
        {
            return "Citizen";
        }
        else
        {
            return rawCrew switch
            {
                "Dragon Celestes" => "Celestial Dragons",
                "Revolutionary" => "Revolutionary's Crew",
                "Marine" or "Marines" or " Marines" => "Navy's Crew",
                "Subordonné de L'Équipage de Barbe Blanche" => "L'Équipage de Barbe Blanche",
                "La Grande Flotte du Chapeau de Paille" or "Alliance de l'Équipage du Chapeau de Paille"
                    or "Flotte de Happou" or "L'Équipage d'Idéo" or "Erbaf" or "L'Équipage des Magnifiques Pirates"
                    or "L'Équipage d'Ideo" => "Allié de L'Équipage du Chapeau de Paille",
                "Équipage de Big Mom" or "Famille Charlotte" => "L'Équipage de Big Mom",
                "Spiders Café" or "Spider's Café" => "Baroque Works",
                "Capitaine de l'Equipage de Caribou" => "L'Équipage de Caribou",
                "L'Équipage de Barbe Brune(dissout)" or "César Clown" or "César Clown (espionnage)"
                    or "L'Équipage du New Age" => "L'Équipage de Don Quichotte Doflamingo",
                "L'Équipage des Pirates Volants" => "L'Équipage des Nouveaux Hommes-Poissons",
                "L'Équipage du Firetank" => "L'Équipage du Fire Tank",
                "Gecko Moria" or "Hogback" or "Dracule Mihawk" => "Thriller Bark",
                "Alliance Baggy et Alvida" or "L'Équipage du Clown" => "Cross Guild",
                "L'Équipage du Capitaine Usopp" => "Citizen",
                _ => rawCrew
            };
        }
    }

    internal static string ExtractPatternCrew(HtmlNode text)
    {
        try
        {
            var affiliationCharacter = new List<string>();
            var affiliations = text.SelectNodes(".//*[contains(@class, 'pi-data-value')]/a");

            foreach (var affiliation in affiliations)
            {
                var affiliationText = affiliation.InnerText;
                if (affiliation.NextSibling != null && (affiliation.NextSibling.InnerText.Contains("anciennement") 
                                                        || affiliation.NextSibling.InnerText.Contains("temporairement") 
                                                        || affiliation.NextSibling.InnerText.Contains("dissous")))
                {
                    var smallText = affiliation.NextSibling.InnerText;
                    affiliationText += smallText;
                }
                affiliationCharacter.Add(affiliationText);
            }
            
            foreach (var affiliation in affiliationCharacter)
            {
                var cleanedAffiliation = Regex.Replace(affiliation, "\\[.*?]\\s*$", "");
                if (!cleanedAffiliation.Contains("anciennement") && !cleanedAffiliation.Contains("temporairement") && !cleanedAffiliation.Contains("dissous"))
                {
                    return cleanedAffiliation;
                }
            }
            return "Citizen";
        }
        catch
        {
            return "Citizen";
        }
    }

    internal static int ExtractPatternAge(string input)
    {
        var matchChoice = Regex.Match(input, "Âge : (.*?) Voix | Âge : (.*?) Taille");
        var maxAge = 0;
        if(matchChoice.Success)
        {
            var textAge = matchChoice.Groups[0].Value;
            if(textAge.Contains("lors de sa mort") || textAge.Contains("lors du décès"))
            {
                var match = Regex.Match(input, @"(\d+\s)?\d+ ans \(lors d");
                while (match.Success)
                {
                    maxAge = Math.Max(maxAge, int.Parse(match.Groups[0].Value.Replace("\\D", "")));
                }
            }
            else
            {
                textAge = textAge.Replace("\\s", "").Replace("\\(.*?\\)", " ").Replace("\\[.*?\\]", " ");
                var ages = textAge.Split("[\\D+]");
                foreach (var age in ages.Where(age => !string.IsNullOrEmpty(age)))
                {
                    maxAge = Math.Max(maxAge, int.Parse(age));
                }
            }
        }
        return maxAge;
    }

    internal static string ExtractPatternType(HtmlNode text)
    {
        try
        {
            var typeCharacters = new List<string>();
            var types = text.SelectNodes(".//*[contains(@class, 'pi-data-value')]")
                .Select(node => node.InnerText)
                .SelectMany(html => Regex.Split(html, "<br>|<p>|<a>"))
                .ToArray();

            foreach (var type in types)
            {
                var docSmallDatas = new HtmlDocument();
                docSmallDatas.LoadHtml(type);
                var smallText = docSmallDatas.DocumentNode.SelectSingleNode("//small")?.InnerText ?? "";
                var cleanedType = Regex.Replace(type, "\\(.*?\\)", "").Trim() + smallText;
                typeCharacters.AddRange(cleanedType.Split(new[] { ";", "," }, StringSplitOptions.RemoveEmptyEntries));
            }

            var typeCharacter = "Citizen";
            foreach (var typeData in typeCharacters.Select(typeData => typeData.Replace("\\[.*?]\\s*$", ""))
                         .Where(typeData => !typeData.Contains("anciennement") && !typeData.Contains("temporairement")))
            {
                if (typeData.Contains("Dragons Celestes") || typeData.Contains("Dragons Célestes") 
                                                          || typeData.Contains("Nobles Mondiaux"))
                {
                    typeCharacter = "Dragon Celestes";
                    break;
                }

                foreach (var pirateType in PirateTypeList.Where(pirateType => typeData.Contains(pirateType))) {
                    typeCharacter = "Pirate";
                }
                foreach (var navyType in NavyTypeList.Where(navyType => typeData.Contains(navyType) && 
                                                                        !typeData.Contains("Prisonnier"))) {
                    typeCharacter = "Navy";
                }
                foreach (var revoType in RevoTypeList.Where(revoType => typeData.Contains(revoType))) {
                    typeCharacter = "Revolutionary";
                }

                if(string.IsNullOrEmpty(typeData))
                {
                    typeCharacter = "Citizen";
                }
            }
            return typeCharacter;
        }
        catch
        {
            return "Citizen";
        }
    }

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

    internal static string ExtractExceptionsPopularity(string character)
    {
        if (character.Contains("Don Quichotte"))
        {
            return character.Replace("Don Quichotte", "Donquixote");
        }

        if(character.Contains("Alber"))
        {
            return "King";
        }

        if (character.Contains("Linlin"))
        {
            return "Big Mom";
        }

        if(character.Contains("Galdino"))
        {
            return "Mr 3";
        }

        if(character.Contains("Marshall D. Teach"))
        {
            return "Barbe Noire";
        }

        if(character.Contains("Edward Newgate"))
        {
            return "Barbe Blanche";
        }

        return character;
    }
    }
}