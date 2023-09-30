/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData;

import android.content.Context;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DAO;
import fr.gmarquette.guesswho.GameData.Database.DataBase;

public class MultiGenerateDatas
{
    private final Integer NUMBER_OF_LEVELS = 2;
    final String url_fandom_listcharacter = "https://onepiece.fandom.com/fr/wiki/Liste_des_Personnages_Canon";
    final String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
    final String class_characterData = "pi-item pi-group pi-border-color";
    final String class_fruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
    final String class_occupation = "pi-item pi-data pi-item-spacing pi-border-color";
    private final LinkedHashMap<String, String> listPictures = new LinkedHashMap<>();

    private static final List<String> pirateTypeList = Arrays.asList("Pirate", "Pirates", "Bandit",
            "Bandits", "Charlotte", "Big Mom", "Flotte de Happou", "Clan", "Moria", "Équipage",
            "Edward", "César", "Equipage", "Mihawk", "Thriller", "Mads", "New Comer Land");
    private static final List<String> navyTypeList = Arrays.asList("Marine", "Marines", " Marines", "Cipher Pol",
            "Souverain", "Conseil des 5 doyens", "CP-AIGIS0", "Gouvernement", "Impel", "Navy's Crew");
    private static final List<String> revoTypeList = Arrays.asList("Armée Révolutionnaire",
            "Révolutionnaires", "armée révolutionnaire", "Revolutionary's Crew");
    public Thread getDatas_Thread;

    public List<String> nameList = new CopyOnWriteArrayList<>();
    private int countPercentage, countLevels;
    private DAO database;

    private MultiGenerateDatas()
    {}

    private static class MultiGenerateDatasHolder
    {
        private final static MultiGenerateDatas instance = new MultiGenerateDatas();
    }

    public static MultiGenerateDatas getInstance()
    {
        return MultiGenerateDatasHolder.instance;
    }

    public void getDatasFromOutside(Context context)
    {
        database = DataBase.getInstance(context).dao();
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

                multiThreadForFandom(nameList);
        });
    }

    public void multiThreadForFandom(List<String> characterList)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(86);
        int batchSize = 14;

        for (int i = 0; i < characterList.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, characterList.size());
            List<String> subList = characterList.subList(i, endIndex);

            executorService.submit(() ->
            {
                for (String character : subList) {
                    List<String> listCharacterDatas = extractValuesFromFandom(character);
                    if (!listCharacterDatas.isEmpty()) {
                        createCharacterFromInformation(listCharacterDatas);
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            boolean result = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            if(!result)
            {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getLevels();
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
                        listPictures.put(columns.get(1).text(), columns.select("img").attr("src"));
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
            characterDataList.add(listPictures.get(character));
            characterDataList.add(String.valueOf(NUMBER_OF_LEVELS - 1));


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

    void createCharacterFromInformation(List<String> characterData)
    {
        try
        {
            if(Integer.parseInt(characterData.get(6)) != 0) {
                Characters charactersAlreadyExist = database.getCharacterFromName(characterData.get(0));
                if(charactersAlreadyExist != null)
                {
                    charactersAlreadyExist.setDevilFruit(stateFruit(characterData.get(1)));
                    charactersAlreadyExist.setBounty(fixBounty(characterData.get(2), characterData.get(4)));
                    charactersAlreadyExist.setFirstAppearance(Integer.parseInt(characterData.get(3)));
                    charactersAlreadyExist.setType(fixOccupation(characterData.get(4), characterData.get(8)));
                    charactersAlreadyExist.setAlive(stateAlive(characterData.get(5)));
                    charactersAlreadyExist.setAge(Integer.parseInt(characterData.get(6)));
                    charactersAlreadyExist.setCrew(defineCrew(characterData.get(7), characterData.get(4)));
                    charactersAlreadyExist.setPicture(characterData.get(8));

                    charactersAlreadyExist.setType(fixType(charactersAlreadyExist.getType(), charactersAlreadyExist.getCrew()));

                    database.updateCharacter(charactersAlreadyExist);
                }
                else
                {
                    Characters characters = new Characters(
                            characterData.get(0),
                            stateFruit(characterData.get(1)),
                            fixBounty(characterData.get(2), characterData.get(4)),
                            Integer.parseInt(characterData.get(3)),
                            fixOccupation(characterData.get(4), characterData.get(8)),
                            stateAlive(characterData.get(5)),
                            Integer.parseInt(characterData.get(6)),
                            defineCrew(characterData.get(7), characterData.get(4)),
                            characterData.get(8),
                            Integer.parseInt(characterData.get(9)));

                    characters.setType(fixType(characters.getType(), characters.getCrew()));

                    database.addCharacter(characters);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        countPercentage++;
    }

    private String fixType(String type, String crew)
    {
        if(type.equals("Citizen"))
        {
            for (String pirateType : pirateTypeList)
            {
                if (crew.contains(pirateType)) {
                    return "Pirate";
                }
            }
            for (String navyType : navyTypeList)
            {
                if (crew.contains(navyType)) {
                    return "Navy";
                }
            }
            for (String revoType : revoTypeList)
            {
                if (crew.contains(revoType)) {
                    return "Revolutionary";
                }
            }
        }
        return type;
    }

    private String fixOccupation(String value, String crew)
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

    private static String fixBounty(String bounty, String type)
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
            return occupation;
        }
        else
        {
            return rawCrew;
        }

    }

    private boolean stateAlive(String state)
    {
        return (!state.equals("Décédé"));
    }

    private boolean stateFruit(String fruit)
    {
        return (fruit.equals("Fruit"));
    }

    List<String> listPopularity = new ArrayList<>();

    public void getLevels() {
        try
        {
            Document doc = Jsoup.connect("http://www.volonte-d.com/details/popularite.php").get();
            Elements elements = doc.getElementsByClass("gallery clearfix");
            if(elements.last() != null)
            {
                Elements tables = Objects.requireNonNull(elements.last()).select("table");
                if(!tables.isEmpty())
                {
                    Element table = tables.last();
                    if (table != null)
                    {
                        Elements rows = table.select("tr");
                        for (int i = 1; i < rows.size(); i++)
                        {
                            Element row = rows.get(i);
                            Elements cols = row.select("td");
                            listPopularity.add(cols.get(1).text());
                        }
                    }
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        getMatchCharacterList();
    }

    void getMatchCharacterList() {
        for (String character : nameList) {
            int position = listPopularity.indexOf(character);

            if (position == -1) {
                String newCharacter = getMatcher(character);
                position = listPopularity.indexOf(newCharacter);
            }

            for (int i = 1; i <= (NUMBER_OF_LEVELS - 1); i++) {
                if (position <= ((NUMBER_OF_LEVELS - 1) * i)) {
                    Characters characters = database.getCharacterFromName(character);
                    characters.setLevel(i - 1);
                    database.updateCharacter(characters);
                    countLevels++;
                }
            }

        }
    }

    String getMatcher(String character)
    {
        String newCharacter = "";
        double meilleureRessemblance = 0.0;
        String texteRessemblant = null;
        for (String popularityCharacter : listPopularity)
        {
            if (character.contains("Don Quichotte")) {
                character = character.replace("Don Quichotte", "Donquixote");
            }

            double ressemblance = calculateSimilarity(character, popularityCharacter);

            if (ressemblance >= 0.6 && ressemblance > meilleureRessemblance) {
                meilleureRessemblance = ressemblance;
                texteRessemblant = popularityCharacter;
            }
        }

        if(texteRessemblant != null)
        {
            newCharacter = texteRessemblant;
        }
        else
        {
            for (String popularityCharacter : listPopularity)
            {
                LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

                int distance = levenshteinDistance.apply(character, popularityCharacter);

                int seuilDistance = 6;

                if (distance <= seuilDistance) {
                    newCharacter = popularityCharacter;
                }
            }
        }
        return newCharacter;
    }

    private static double calculateSimilarity(String texte1, String texte2) {
        String[] termes1 = texte1.split(" ");
        String[] termes2 = texte2.split(" ");

        Map<CharSequence, Integer> vector1 = new HashMap<>();
        Map<CharSequence, Integer> vector2 = new HashMap<>();

        for (String terme : termes1) {
            vector1.put(terme, 1);
        }

        for (String terme : termes2) {
            vector2.put(terme, 1);
        }

        CosineSimilarity cosSimilarity = new CosineSimilarity();
        return cosSimilarity.cosineSimilarity(vector1, vector2);
    }

    public List<String> getNameList()
    {
        return this.nameList;
    }

    public int getCountPercentage() {
        return countPercentage;
    }

    public int getCountLevels() {
        return countLevels;
    }
}
