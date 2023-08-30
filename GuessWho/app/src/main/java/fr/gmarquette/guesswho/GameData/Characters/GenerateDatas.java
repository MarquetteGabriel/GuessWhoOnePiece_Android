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
import fr.gmarquette.guesswho.GameData.Database.DataBase;

public class GenerateDatas
{
    final String url_wikipedia_onepiece = "https://fr.wikipedia.org/wiki/Personnages_de_One_Piece";
    final String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
    final String class_characterData = "pi-item pi-group pi-border-color";
    final String class_titles = "mw-headline";
    final String class_fruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";

    public static Thread getDatas_Thread;
    List<String> nameList;

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
                String values = headline.select("span").text();
                if (!bannedWordsList.equals(values)) {
                    nameList.add(values);
                }
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
            Element fruitElement = doc.getElementsByClass(class_fruit).first();

            characterDataList.add(character);
            characterDataList.add((!fruitElement.text().contains("Fruit du Démon")) ? "No_Fruit" : "Fruit");
            characterDataList.add(extractPattern(characterData, "Prime : ([\\d.,]+)").replaceAll("[.,]", "").trim());
            characterDataList.add(extractPattern(characterData, "Première Apparition: Chapitre (\\d+)"));
            characterDataList.add(extractPattern(characterData, "Occupations : ([^;]+)")); //
            characterDataList.add(extractPattern(characterData, "Statut : (Vivant|Décédé)"));
            characterDataList.add(extractPatternAge(characterData, "(\\d+) ans"));
            characterDataList.add(extractPattern(characterData, "Affiliations : ([^;]+)"));
            characterDataList.add("0");
        } catch (IOException e) {
            nameList.remove(character);
        }

        return characterDataList;
    }

    private static String extractPattern(String input, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private static String extractPatternAge(String input, String pattern)
    {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        int maxAge = 0;
        while(matcher.find())
        {
            maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(1))));
        }
        return String.valueOf(maxAge);
    }

    void createCharacterFromInformation(List<String> characterData, Context context)
    {
        Log.i("DATABASE_TEST_TYPE", characterData.get(0));
        Log.i("DATABASE_TEST_TYPE", characterData.get(4));
        Characters characters = new Characters(
                characterData.get(0),
                stateFruit(characterData.get(1)),
                characterData.get(2),
                Integer.parseInt(characterData.get(3)),
                characterData.get(4),
                stateAlive(characterData.get(5)),
                Integer.parseInt(characterData.get(6)),
                characterData.get(7),
                Integer.parseInt(characterData.get(8)));

        DataBase.getInstance(context).dao().addCharacter(characters);
    }

    private boolean stateAlive(String state)
    {
        return(state.equals("Vivant"));
    }

    private boolean stateFruit(String fruit)
    {
        return (fruit.equals("Fruit"));
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
    }

    private static String setOccupation(String text)
    {
        if(text.contains("Affiliations : L'Équipage du Chapeau de Paille"));
    }*/

}
