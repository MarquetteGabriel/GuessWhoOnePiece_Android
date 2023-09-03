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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;

public class MultiGenerateDatas
{
    private final Integer NUMBER_OF_LEVELS = 2;
    final String url_fandom_listcharacter = "https://onepiece.fandom.com/fr/wiki/Liste_des_Personnages_Canon";
    final String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
    final String url_list_arcs = "https://onepiece.fandom.com/fr/wiki/Cat%C3%A9gorie:Arcs_de_One_Piece";
    final String class_characterData = "pi-item pi-group pi-border-color";
    final String class_fruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
    final String class_occupation = "pi-item pi-data pi-item-spacing pi-border-color";

    private static final List<String> pirateTypeList = Arrays.asList("Pirate", "Pirates", "Bandit", "Bandits", "Famille Charlotte", "Flotte de Happou");
    private static final List<String> navyTypeList = Arrays.asList("Marine", "Cipher Pol", "Souverain", "Conseil des 5 doyens", "CP-AIGIS0");
    private static final List<String> revoTypeList = Arrays.asList("Armée Révolutionnaire", "Révolutionnaires", "armée révolutionnaire");

    public Thread getDatas_Thread;

    private List<String> nameList;
    private int countPercentage, countLevels;


    public void getDatasFromOutside(Context context)
    {
        getDatas_Thread = new Thread(() -> {
            countPercentage = 0;
            countLevels = 0;
            nameList = getListofCharacters();
            for(int i = 0; i < nameList.size() ; i++)
            {
                if (nameList.get(i).contains("("))
                {
                    nameList.set(i, nameList.get(i).replaceAll("\\(.*?\\)", ""));
                }
            }

            multiThreadForFandom(context, nameList);
            });
    }

    public void multiThreadForFandom(Context context, List<String> characterList)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(43);
        int batchSize = 28;

        for (int i = 0; i < characterList.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, characterList.size());
            List<String> subList = characterList.subList(i, endIndex);
/*
            executorService.submit(() ->
            {
                for (String character : subList) {
                    List<String> listCharacterDatas = extractValuesFromFandom(character);
                    if (!listCharacterDatas.isEmpty()) {
                        createCharacterFromInformation(listCharacterDatas, context);
                    }
                }
            });*/
            getCountLevelsDone(context);
        }
    }

    private List<String> getListofCharacters()
    {
        List<String> characterList = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(url_fandom_listcharacter).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String html = doc.getElementsByClass("tabber wds-tabber").html();
        Document doc2 = Jsoup.parse(html);
        Elements tables = doc2.select("table.wikitable");

        for (Element table : tables)
        {
            if (table != null) {
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    Elements columns = row.select("td");
                    if (columns.size() >= 6) {
                        characterList.add(columns.get(1).text());
                    }
                }
            }
        }

        return characterList;
    }

    List<String> extractValuesFromFandom(String character)
    {
        List<String> characterDataList = new ArrayList<>();
        try {
            Element occupation = null, affiliation = null;
            String url_character = character.replace(" ", "_").trim();
            String url = url_fandom + url_character;

            Document doc = Jsoup.connect(url).get();
            String characterData = doc.getElementsByClass(class_characterData).text();
            String fruitElement = doc.getElementsByClass(class_fruit).text();
            Elements occupationElements = doc.getElementsByClass(class_occupation);

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
            characterDataList.add(extractPattern(characterData, "Prime : ([\\d.,\\s]+)").replaceAll("[.,\\s]", "").trim());
            characterDataList.add(extractPattern(characterData, "Chapitre (\\d+)"));
            characterDataList.add(extractPatternType(occupation));
            characterDataList.add(extractPattern(characterData, "Statut : (Vivant|Décédé)"));
            characterDataList.add(extractPatternAge(characterData));
            characterDataList.add(extractPatternCrew(affiliation));
            characterDataList.add("6");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterDataList;
    }

    private static String extractPatternCrew(Element text)
    {
        try
        {
            List<String> affiliationCharacter = new ArrayList<>();
            String[] affiliations = text.select(".pi-data-value").html().split("<br>|<p>");
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
        Matcher matcher = Pattern.compile("(\\d+\\s)?\\d+ ans").matcher(input);
        int maxAge = 0;
        while(matcher.find())
        {
            maxAge = Math.max(maxAge, Integer.parseInt(Objects.requireNonNull(matcher.group(0)).replaceAll("\\D", "")));
        }
        return String.valueOf(maxAge);
    }

    private void createCharacterFromInformation(List<String> characterData, Context context)
    {
        try
        {
            if(Integer.parseInt(characterData.get(6)) != 0) {
                Characters characters = new Characters(
                        characterData.get(0),
                        stateFruit(characterData.get(1)),
                        fixBounty(characterData.get(2), characterData.get(4)),
                        Integer.parseInt(characterData.get(3)),
                        fixOccupation(characterData.get(4), characterData.get(8)),
                        stateAlive(characterData.get(5)),
                        Integer.parseInt(characterData.get(6)),
                        defineCrew(characterData.get(7), characterData.get(4)),
                        Integer.parseInt(characterData.get(8)));


                DataBase.getInstance(context).dao().addCharacter(characters);
                countPercentage++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String fixOccupation(String value, String affiliation)
    {
        if(value.isEmpty())
        {
            for (String pirateType : pirateTypeList)
            {
                if (affiliation.contains(pirateType)) {
                    value = "Pirate";
                    break;
                }
            }
            for (String navyType : navyTypeList)
            {
                if (affiliation.contains(navyType)) {
                    value = "Navy";
                    break;
                }
            }
            for (String revoType : revoTypeList)
            {
                if (affiliation.contains(revoType)) {
                    value = "Revolutionary";
                    break;
                }
            }
        }

        return value;
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
        try
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
                occupation = occupation.replaceAll("\\[.*?]\\s*$", "");
                if(!occupation.contains("anciennement") && !occupation.contains("temporairement"))
                {
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

                    if(occupation.isEmpty())
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

    private String defineCrew(String rawCrew, String occupation)
    {
        if(rawCrew.startsWith("CP"))
        {
            return "Cipher Pol";
        }
        else if (rawCrew.equals("Marine"))
        {
            return "Navy's Crew";
        }
        else if(rawCrew.contains("armée") || rawCrew.contains("Armée"))
        {
            return "Revolutionary's Crew";
        }
        else if(rawCrew.isEmpty())
        {
            return occupation;
        }

        return rawCrew;
    }

    private boolean stateAlive(String state)
    {
        return (!state.equals("Décédé"));
    }

    private boolean stateFruit(String fruit)
    {
        return (fruit.equals("Fruit"));
    }

    private void getCountLevelsDone(Context context)
    {
        new Thread(() ->
        {
            List<String> arcsList = getHistory();
            int segmentSize = nameList.size() / NUMBER_OF_LEVELS;
            for (String character : nameList) {
                int occurences = 0;
                for(String historyOfOnePiece : arcsList)
                {
                    occurences += getCountOccurences(historyOfOnePiece, character);
                }

                for (int i = 0; i < NUMBER_OF_LEVELS; i++)
                {
                    if (occurences <= i * segmentSize) {
                        Characters characters = DataBase.getInstance(context).dao().getCharacterFromName(character);
                        characters.setLevel(i);
                        DataBase.getInstance(context).dao().updateCharater(characters);
                    }
                }
            }
        }).start();
    }

    private List<String> getHistory()
    {
        List<String> history = new ArrayList<>();
        List<String> listArcs = new ArrayList<>();
        try
        {
            Document doc = Jsoup.connect(url_list_arcs).get();
            Elements elements = doc.getElementsByClass("category-page__members-wrapper");
            for (Element subSections : elements)
            {
                Elements listArc = subSections.select("li");
                for(Element element : listArc)
                {
                    String arc = element.text();
                    if(!arc.startsWith("Catégorie:"))
                    {
                        arc = arc.replaceAll("\\s", "_");
                        listArcs.add(arc);
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(listArcs.size());
        for(String arcs : listArcs) {
            executorService.submit(() ->
            {
                try {

                    String url = url_fandom + arcs;
                    Document page = Jsoup.connect(url).get();
                    Element summuary = page.getElementsByClass("mw-parser-output").first();
                    Elements stories = summuary.select("p");
                    for (Element story : stories) {
                        synchronized (history)
                        {
                            history.add(story.text());
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return history;
    }

    private int getCountOccurences(String text, String character)
    {
        int count = 0, lastIndex = 0;

        while((lastIndex = text.indexOf(character, lastIndex)) != -1)
        {
            count++;
            lastIndex += character.length();
        }

        return count;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public int getCountPercentage() {
        return countPercentage;
    }

    public int getCountLevels() {
        return countLevels;
    }
}
