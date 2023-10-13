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
            "Souverain", "Conseil des 5 doyens", "CP-AIGIS0", "Gouvernement", "Impel", "Navy's Crew");
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

    static String fixBounty(String bounty, String type)
    {
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
                return String.format(Locale.getDefault() ,"%.3f Md", numBounty / 1_000_000_000);
            }
            else if (numBounty >= 1_000_000)
            {
                return String.format(Locale.getDefault(), "%.3f Mi", numBounty / 1_000_000);
            }
            else
            {
                return bounty;
            }
        }
    }

    static String fixType(String value, String crew)
    {
        if(value.isEmpty() || crew.equals("Citizen"))
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
        else if(rawCrew.contains("armée") || rawCrew.contains("Armée") || rawCrew.equals("Révolutionaires"))
        {
            return "Revolutionary's Crew";
        }
        else if(rawCrew.isEmpty())
        {
            return type;
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
            String[] affiliations = text.select(".pi-data-value").html().split("<br>|<p>");
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
        Matcher matcher = Pattern.compile("(\\d+\\s)?\\d+ ans | Âge\\s*:\\s*(\\d+)").matcher(input);
        int maxAge = 0;
        while(matcher.find())
        {
            maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(0)).replaceAll("\\D", "")));
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
                        if (typeData.contains(navyType)) {
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
}
