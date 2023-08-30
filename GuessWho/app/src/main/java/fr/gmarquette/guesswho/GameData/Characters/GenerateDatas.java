/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;

public class GenerateDatas
{
    final String url_wikipedia_onepiece = "https://fr.wikipedia.org/wiki/Personnages_de_One_Piece";
    final String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
    final String class_characterData = "pi-item pi-group pi-border-color";
    final String class_titles = "mw-headline";
    final String class_fruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
    final String class_occupation = "pi-item pi-data pi-item-spacing pi-border-color";

    public static Thread getDatas_Thread;
    public static List<String> nameList;

    public void getDatasFromOutside(Context context)
    {
        getDatas_Thread = new Thread(() -> {
            extractValuesFromWiki();
            for (String character : nameList)
            {
                List<String> listCharacterDatas = extractValuesFromFandom(character);
                if(!listCharacterDatas.isEmpty())
                {
                    createCharacterFromInformation(listCharacterDatas, context);
                }
            }
            });
        getDatas_Thread.start();
    }

    void extractValuesFromWiki()
    {
        try {
            Document doc = Jsoup.connect(url_wikipedia_onepiece).get();
            nameList = new ArrayList<>();
            List<String> bannedWordsList = new ArrayList<>();

            Elements bannedWords = doc.select("h3, h2");
            for (Element h2Element : bannedWords) {
                String text_raw = h2Element.text();
                String text = text_raw.replace("[modifier | modifier le code]", "").trim();
                bannedWordsList.add(text);
            }


            Elements elements = doc.getElementsByClass(class_titles);
            for (Element headline : elements) {
                boolean temp = false;
                String values = headline.select("span").text();
                for(String bannedWord : bannedWordsList)
                {
                    if(values.equals(bannedWord))
                    {
                        temp = true;
                        break;
                    }
                }

                if(!temp)
                {
                    nameList.add(values);
                }
                //if (!bannedWordsList.equals(values)) {
                  //  nameList.add(values);}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> extractValuesFromFandom(String character)
    {
        List<String> characterDataList = new ArrayList<>();
        try {
            String url_character = character.replace(" ", "_").trim();
            String url = url_fandom + url_character;
            Document doc = Jsoup.connect(url).get();
            String characterData = doc.getElementsByClass(class_characterData).text();
            String fruitElement = doc.getElementsByClass(class_fruit).text();
            Elements occupationElements = doc.getElementsByClass(class_occupation);
            Element occupation = null, affiliation = null;
            for (Element occupationElement : occupationElements) {
                String dataSource = occupationElement.attr("data-source");

                if (dataSource.equals("occupation")) {
                    occupation = occupationElement;
                }
                else if (dataSource.equals("affiliation")) {
                    affiliation = occupationElement;
                }
            }

            characterDataList.add(character);
            characterDataList.add((!fruitElement.contains("Fruit du Démon")) ? "No_Fruit" : "Fruit");
            characterDataList.add(extractPattern(characterData, "Prime : ([\\d.,]+)").replaceAll("[.,]", "").trim());
            characterDataList.add(extractPattern(characterData, "Première Apparition: Chapitre (\\d+)"));
            characterDataList.add(extractPatternType(Objects.requireNonNull(occupation)));
            characterDataList.add(extractPattern(characterData, "Statut : (Vivant|Décédé)"));
            characterDataList.add(extractPatternAge(characterData));
            characterDataList.add(extractPatternCrew(Objects.requireNonNull(affiliation)));//characterData, "Affiliations : ([^;]+)")
            characterDataList.add("0");
        } catch (IOException e) {
            nameList.remove(character);
        }

        return characterDataList;
    }

    private static String extractPatternCrew(Element text)
    {
        List<String> affiliationCharacter = new ArrayList<>();
        String[] affiliations = text.select(".pi-data-value").html().split("<br>");
        for(String affiliation : affiliations)
        {
            Document docSmallDatas = Jsoup.parse(affiliation);
            String smallText = docSmallDatas.select("small").text();
            affiliation = Jsoup.parse(affiliation).text().replaceAll("\\(.*?\\)", "").trim() + smallText;
            affiliationCharacter.addAll(Arrays.asList(affiliation.split("[;,]")));
        }
        affiliationCharacter.removeIf(String::isEmpty);

        for(String affiliation : affiliationCharacter)
        {
            affiliation = affiliation.replaceAll("\\[.*?\\]\\s*$", "");
            if(!affiliation.contains("anciennement") && !affiliation.contains("temporairement"))
            {
                return affiliation;
            }
        }
        return "Citizen";
    }

    private static String extractPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private static String extractPatternAge(String input)
    {
        Matcher matcher = Pattern.compile("(\\d+) ans").matcher(input);
        int maxAge = 0;
        while(matcher.find())
        {
            maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(1))));
        }
        return String.valueOf(maxAge);
    }

    void createCharacterFromInformation(List<String> characterData, Context context)
    {
        Characters characters = new Characters(
                characterData.get(0),
                stateFruit(characterData.get(1)),
                fixBounty(characterData.get(2), characterData.get(4)),
                Integer.parseInt(characterData.get(3)),
                characterData.get(4),
                stateAlive(characterData.get(5)),
                Integer.parseInt(characterData.get(6)),
                characterData.get(7),
                Integer.parseInt(characterData.get(8)));

        if(Integer.parseInt(characterData.get(3)) != 0)
        {
            DataBase.getInstance(context).dao().addCharacter(characters);
        }
        else
        {
            nameList.remove(characterData.get(0));
        }
    }

    private static String fixBounty(String bounty, String type)
    {
        if(type.equals("Navy") || type.equals("Citizen"))
        {
            return "0";
        }
        else if(bounty.equals(""))
        {
            return "Unknown";
        }
        else
        {
            return bounty;
        }
    }

    private static String extractPatternType(Element text)
    {
        List<String> occupationCharacter = new ArrayList<>();
        String[] occupations = text.select(".pi-data-value").html().split("<br>");
        for(String occupation : occupations)
        {
            Document docSmallDatas = Jsoup.parse(occupation);
            String smallText = docSmallDatas.select("small").text();
            occupation = Jsoup.parse(occupation).text().replaceAll("\\(.*?\\)", "").trim() + smallText;
            occupationCharacter.addAll(Arrays.asList(occupation.split("[;,]")));
        }
        occupationCharacter.removeIf(String::isEmpty);

        String type = "Citizen";
        for(String occupation : occupationCharacter)
        {
            occupation = occupation.replaceAll("\\[.*?\\]\\s*$", "");
            if(!occupation.contains("anciennement") && !occupation.contains("temporairement"))
            {
                List<String> pirateTypeList = Arrays.asList("Pirate", "Pirates", "Bandit", "Bandits");
                List<String> navyTypeList = Arrays.asList("Marine", "Cipher Pol", "Souverain", "Conseil des 5 doyens");
                List<String> revoTypeList = Arrays.asList("Armée Révolutionnaire", "Révolutionnaires", "armée révolutionnaire");

                for (String pirateType : pirateTypeList)
                {
                    if (occupation.contains(pirateType)) {
                        type = "Pirate";
                        break;
                    }
                }
                for (String navyType : navyTypeList)
                {
                    if (occupation.contains(navyType)) {
                        type = "Navy";
                        break;
                    }
                }
                for (String revoType : revoTypeList)
                {
                    if (occupation.contains(revoType)) {
                        type = "Revolutionary";
                        break;
                    }
                }
            }
        }
        return type;
    }

    private boolean stateAlive(String state)
    {
        return(state.equals("Vivant"));
    }

    private boolean stateFruit(String fruit)
    {
        return (fruit.equals("Fruit"));
    }
}
