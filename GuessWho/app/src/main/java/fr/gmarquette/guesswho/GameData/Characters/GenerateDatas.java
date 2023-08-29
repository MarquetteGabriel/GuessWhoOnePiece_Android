/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class GenerateDatas
{
    List<Characters> charactersList = new ArrayList<>();
    String url_wikipedia_onepiece = "https://fr.wikipedia.org/wiki/Personnages_de_One_Piece";
    String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
    String values;
    Thread getDatas_Thread;
    List<String> nameList;

    public void getDatasFromOutside(Context context)
    {
        getDatas_Thread = new Thread(() -> {
            extractValuesFromWiki();
            for (String character : nameList)
            {
                createCharacterFromInformation(extractValuesFromFandom(character), context);
            }
            });
        getDatas_Thread.start();
    }

    public void extractValuesFromWiki()
    {
        try {
            Document doc = Jsoup.connect(url_wikipedia_onepiece).get();
            nameList = new ArrayList<>();
            List<String> list = new ArrayList<>();
            Elements bannedWords = doc.select("h3");
            Elements bannedWords2 = doc.select("h2");
            for (Element h3Element : bannedWords) {
                String text_raw = h3Element.text();
                String text = text_raw.replace("[modifier | modifier le code]", "").trim();
                list.add(text);
            }

            for (Element h2Element : bannedWords2) {
                String text_raw = h2Element.text();
                String text = text_raw.replace("[modifier | modifier le code]", "").trim();
                list.add(text);
            }

            Elements elements = doc.getElementsByClass("mw-headline");
            for (Element headline : elements) {
                values = headline.select("span").text();

                boolean bannedWord = false;
                for(String word : list)
                {
                    if (values.equals(word)) {
                        bannedWord = true;
                        break;
                    }
                }

                if(!bannedWord)
                {
                    nameList.add(values);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> extractValuesFromFandom(String character)
    {
        List<String> characterDataList = new ArrayList<>();
        try {
            String url_character = character.replace(" ", "_").trim();
            String url = url_fandom + url_character;

            Document doc = Jsoup.connect(url).get();
            values = doc.getElementsByClass("pi-item pi-group pi-border-color").text();

            String fruitDemonPattern = "Fruit du Demon : ([^;]+)";
            String occupationPattern = "Occupations : (.+) ; ";

            characterDataList.add(character);
            characterDataList.add(extractPattern(values, fruitDemonPattern));
            characterDataList.add(extractPattern(values, "Prime : ([\\d,]+)").replace(",", "").trim());
            characterDataList.add(extractPattern(values, "Première Apparition: Chapitre (\\d+)"));
            characterDataList.add(extractPattern(values, occupationPattern));
            characterDataList.add(extractPattern(values, "Statut : (Vivant|Décédé)"));
            characterDataList.add(extractPatternAge(values, "(\\d+) ans"));
            characterDataList.add(extractPattern(values, "Affiliations : ([^;]+)"));
            characterDataList.add("0");
        } catch (IOException e) {
            nameList.remove(character);
        }

        return characterDataList;
    }

    public static String extractPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String extractPatternAge(String input, String pattern)
    {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        int maxAge = 0;
        while(matcher.find())
        {
            maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(1))));
        }
        return "" + maxAge;
    }

    public void createCharacterFromInformation(List<String> characterData, Context context)
    {
        Log.i("DATABASE_TEST_TYPE", characterData.get(0));
        Log.i("DATABASE_TEST_TYPE", characterData.get(4));
        Characters characters = new Characters(
                characterData.get(0),
                Boolean.getBoolean(characterData.get(1)),
                characterData.get(2),
                Integer.parseInt(characterData.get(3)),
                characterData.get(4),
                stateAlive(characterData.get(5)),
                Integer.parseInt(characterData.get(6)),
                characterData.get(7),
                Integer.parseInt(characterData.get(8)));

        charactersList.add(characters);

        //DataBase.getInstance(context).dao().addCharacter(characters);
    }

    private boolean stateAlive(String state)
    {
        return(state.equals("Vivant"));
    }

    /*
    private String setOccupations(String occupation)
    {
        if(occupation.contains("Pirate"))
        {
            return "Pirate";
        }
        else if(occupation.contains("Marine"))
        {
            return "Marine";
        }
    }*/

}
