/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractorPattern
{
    private static final List<String> pirateTypeList = Arrays.asList("Pirate", "Pirates", "Bandit",
            "Bandits", "Charlotte", "Big Mom", "Flotte de Happou", "Clan", "Moria", "Équipage",
            "Edward", "César", "Equipage", "Mihawk", "Thriller", "Mads", "New Comer Land");
    private static final List<String> navyTypeList = Arrays.asList("Marine", "Marines", " Marines", "Cipher Pol",
            "Souverain", "Conseil des 5 doyens", "CP-AIGIS0", "Gouvernement", "Impel", "Navy's Crew", "Juge");
    private static final List<String> revoTypeList = Arrays.asList("Armée Révolutionnaire",
            "Révolutionnaires", "armée révolutionnaire", "Revolutionary's Crew");


    static String extractPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    static String extractPatternBounty(String input)
    {
        Pattern regex = Pattern.compile("Prime : (.*?)(Statut|Anniversaire|Âge)");
        Matcher matcher = regex.matcher(input);
        long maxBounty = 0;
        try
        {
            if(matcher.find())
            {
                if(matcher.group(1) != null)
                {
                    if(!matcher.group(1).contains("Inconnue") && !matcher.group(1).contains("Aucune"))
                    {
                        String text = matcher.group(1).replaceAll("(?<!\\])\\s", ".").replaceAll(";", " ");
                        text = text.replaceAll("\\[.*?\\]", "").replaceAll(",", ".").replaceAll("\\(.*?\\)", "");
                        String[] bountys = text.split("\\s+");
                        for (String bounty : bountys)
                        {
                            maxBounty = Math.max(maxBounty, Long.parseLong(bounty.replaceAll("\\.", "")));

                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            maxBounty = 0;
        }

        if(maxBounty == 0)
        {
            return "";
        }
        else
        {
            return String.valueOf(maxBounty);
        }
    }

    static String fixBounty(String bounty, String type)
    {
        for (String navyType : navyTypeList)
        {
            if (type.contains(navyType)) {
                type = "Navy";
                break;
            }
        }

        if(type.equals("Navy") || type.equals("Citizen"))
        {
            return "0";
        }
        else if(bounty.equals("") || bounty.equals("Inconnue") || bounty.equals("Aucune"))
        {
            return "Unknown";
        }
        else
        {
            double numBounty = Double.parseDouble(bounty);
            if(numBounty >= 1_000_000_000)
            {
                return String.format(Locale.getDefault() ,"%.3f Md", numBounty / 1_000_000_000).replaceAll("[,.]0+ Md", " Md");
            }
            else if (numBounty >= 1_000_000)
            {
                return String.format(Locale.getDefault(), "%.3f Mi", numBounty / 1_000_000).replaceAll("[,.]0+ Mi", " Mi");
            }
            else
            {
                return bounty;
            }
        }
    }

    static String fixType(String value, String crew)
    {
            for (String pirateType : pirateTypeList)
            {
                if (crew.contains(pirateType)) {
                    value = "Pirate";
                    break;
                }
            }
            for (String navyType : navyTypeList)
            {
                if (crew.contains(navyType)) {
                    value = "Navy";
                    break;
                }
            }
            for (String revoType : revoTypeList)
            {
                if (crew.contains(revoType)) {
                    value = "Revolutionary";
                    break;
                }
        }

        return value;
    }

    static String fixCrew(String rawCrew, String type)
    {
        rawCrew = rawCrew.replaceAll("\\s+$", "");
        if(rawCrew.startsWith("CP"))
        {
            return "Cipher Pol";
        }
        else if(rawCrew.equals("Marine") || rawCrew.equals("Marines") || rawCrew.equals(" Marines"))
        {
            return "Navy's Crew";
        }
        else if(type.equals("Revolutionary"))
        {
            return "Revolutionary's Crew";
        }
        else if(rawCrew.isEmpty())
        {
            return type;
        }
        else if (type.equals("Citizen"))
        {
            return "Citizen";
        }
        else
        {
            return rawCrew;
        }
    }

    static String extractPatternCrew(Element text)
    {
        try
        {
            List<String> affiliationCharacter = new ArrayList<>();
            String[] affiliations = text.select(".pi-data-value").html().split("<br>|<p>|</a>");
            for(String affiliation : affiliations)
            {
                Document docSmallDatas = Jsoup.parse(affiliation);
                String smallText = docSmallDatas.select("small").text();
                affiliation = Jsoup.parse(affiliation).text().replaceAll("\\(.;*?\\)", "").trim() + smallText;
                affiliationCharacter.addAll(Arrays.asList(affiliation.split("[;,]")));
            }
            affiliationCharacter.removeIf(String::isEmpty);

            for(String affiliation : affiliationCharacter)
            {
                affiliation = affiliation.replaceAll("\\[.*?]\\s*$", "");
                if(!affiliation.contains("anciennement") && !affiliation.contains("temporairement"))
                {
                    return affiliation;
                }
            }
            return "Citizen";
        }
        catch (Exception e)
        {
            return "Citizen";
        }
    }

    static int extractPatternAge(String input)
    {
        Matcher matcherChoice = Pattern.compile("Âge : (.*?) Voix | Âge : (.*?) Taille").matcher(input);
        int maxAge = 0;
        if(matcherChoice.find())
        {
            String textAge = matcherChoice.group(0);
            if(textAge.contains("lors de sa mort") || textAge.contains("lors du décès"))
            {
                Matcher matcher = Pattern.compile("(\\d+\\s)?\\d+ ans \\(lors d").matcher(input);
                while ((matcher.find()))
                {
                    maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(0)).replaceAll("\\D", "")));
                }
            }
            else
            {
                textAge = textAge.replaceAll("\\s", "").replaceAll("\\(.*?\\)", " ").replaceAll("\\[.*?\\]", " ");
                String[] ages = textAge.split("[\\D+]");
                for (String age : ages)
                {
                    if(!age.isEmpty())
                    {
                        maxAge = Math.max(maxAge, Integer.parseInt(age));
                    }
                }
            }
        }
        return maxAge;
    }

    static String extractPatternType(Element text)
    {
        try
        {
            List<String> typeCharacter = new ArrayList<>();
            String[] types = text.select(".pi-data-value").html().split("<br>");
            for(String type : types)
            {
                Document docSmallDatas = Jsoup.parse(type);
                String smallText = docSmallDatas.select("small").text();
                type = Jsoup.parse(type).text().replaceAll("\\(.*?\\)", "").trim() + smallText;
                typeCharacter.addAll(Arrays.asList(type.split("[;,]")));
            }
            typeCharacter.removeIf(String::isEmpty);

            String type = "Citizen";
            for(String typeData : typeCharacter)
            {
                typeData = typeData.replaceAll("\\[.*?]\\s*$", "");
                if(!typeData.contains("anciennement") && !typeData.contains("temporairement"))
                {
                    for (String pirateType : pirateTypeList)
                    {
                        if (typeData.contains(pirateType)) {
                            type = "Pirate";
                            break;
                        }
                    }
                    for (String navyType : navyTypeList)
                    {
                        if (typeData.contains(navyType) && !typeData.contains("Prisonnier")) {
                            type = "Navy";
                            break;
                        }
                    }
                    for (String revoType : revoTypeList)
                    {
                        if (typeData.contains(revoType)) {
                            type = "Revolutionary";
                            break;
                        }
                    }

                    if(typeData.isEmpty())
                    {
                        type = "Citizen";
                    }
                }
            }
            return type;
        }
        catch (Exception e)
        {
            return "Citizen";
        }
    }

    static String extractExceptions(String character)
    {
        switch (character)
        {
            case "Chadros Higelyges":
                return "Barbe Brune";
            case "Jabra":
                return "Jabura";
            case "Tama":
                return "Kurozumi Tama";
            case "Kaku (Wano)":
                return "Kaku";
            case "Enel":
                return "Ener";
            default :
                return character;
        }
    }

    static String extractExceptionsPopularity(String character)
    {
        if (character.contains("Don Quichotte"))
        {
            return character.replace("Don Quichotte", "Donquixote");
        }
        else if(character.contains("Alber"))
        {
            return "King";
        }
        else if (character.contains("Linlin"))
        {
            return "Big Mom";
        }
        else if(character.contains("Galdino"))
        {
            return "Mr 3";
        }
        else if(character.contains("Marshall D. Teach"))
        {
            return "Babre Noire";
        }
        else if(character.contains("Edward Newgate"))
        {
            return "Barbe Blanche";
        }
        else
        {
            return character;
        }
    }
}
