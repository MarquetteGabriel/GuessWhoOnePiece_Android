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
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class MultiGenerateDatasTest {

    @Test
    public void extractValuesFromFandomTest() {
        String character = "Orlu";
        getDatasForEachCharacter(character);
        /*

        String characterTwo = "Jango";
        List<String> resultTwo = multiGenerateDatas.extractValuesFromFandom(characterTwo);
        
        // Assert on Jango
        {
            assertEquals(resultTwo.get(0), "Jango");
            assertEquals(resultTwo.get(1), "No_Fruit");
            assertEquals(resultTwo.get(2), "9000000");
            assertEquals(resultTwo.get(3), "25");
            assertEquals(resultTwo.get(4), "Navy");
            assertEquals(resultTwo.get(5), "Vivant");
            assertEquals(resultTwo.get(6), "29");
            assertEquals(resultTwo.get(7), "Marine");
        }

        String characterThree = "Rob Lucci";
        List<String> resultThree = multiGenerateDatas.extractValuesFromFandom(characterThree);

        // Assert on Lucci
        {
            assertEquals(resultThree.get(0), "Rob Lucci");
            assertEquals(resultThree.get(1), "Fruit");
            assertEquals(resultThree.get(2), "");
            assertEquals(resultThree.get(3), "323");
            assertEquals(resultThree.get(4), "Navy");
            assertEquals(resultThree.get(5), "Vivant");
            assertEquals(resultThree.get(6), "30");
            assertEquals(resultThree.get(7), "CP-AIGIS0");
        }

        String characterFour = "Monkey D. Dragon";
        List<String> resultFour = multiGenerateDatas.extractValuesFromFandom(characterFour);

        // Assert on Dragon
        {
            assertEquals(resultFour.get(0), "Monkey D. Dragon");
            assertEquals(resultFour.get(1), "No_Fruit");
            assertEquals(resultFour.get(2), "");
            assertEquals(resultFour.get(3), "100");
            assertEquals(resultFour.get(4), "Revolutionary");
            assertEquals(resultFour.get(5), "Vivant");
            assertEquals(resultFour.get(6), "55");
            assertEquals(resultFour.get(7), "Armée de la Liberté");
        }*/
    }

    Characters getDatasForEachCharacter(String character)
    {
        try {
            String url_character = character.replace(" ", "_").trim();
            Pattern pattern = Pattern.compile("(.+)_\\(.*\\)");
            Matcher matcher = pattern.matcher(url_character);
            if (matcher.matches()) {
                url_character = matcher.group(1);
            }
            String url_fandom = "https://onepiece.fandom.com/fr/wiki/";
            String url = url_fandom + url_character;

            Element typeElement = null, crewElement = null;
            Document doc = Jsoup.connect(url).get();
            String class_characterData = "pi-item pi-group pi-border-color";
            String characterData = doc.getElementsByClass(class_characterData).text();
            String class_fruit = "portable-infobox pi-background pi-border-color pi-theme-char pi-layout-default";
            String fruitElement = doc.getElementsByClass(class_fruit).text();
            String class_type = "pi-item pi-data pi-item-spacing pi-border-color";
            Elements bountyTypeCrewElements = doc.getElementsByClass(class_type);

            for (Element bountyTypeCrewElement : bountyTypeCrewElements) {
                String dataSource = bountyTypeCrewElement.attr("data-source");

                if (dataSource.equals("occupation")) {
                    typeElement = bountyTypeCrewElement;
                }
                else if (dataSource.equals("affiliation")) {
                    crewElement = bountyTypeCrewElement;
                }
            }

            String crew = ExtractorPattern.extractPatternCrew(crewElement);
            boolean fruit = fruitElement.contains("Fruit du Démon");
            String type = ExtractorPattern.fixType(ExtractorPattern.extractPatternType(typeElement), crew);
            String bounty = ExtractorPattern.fixBounty(ExtractorPattern.extractPatternBounty(characterData).replaceAll("[.,\\s]", "").trim(), type);
            int chapter = Integer.parseInt(ExtractorPattern.extractPattern(characterData, "Chapitre (\\d+)"));
            boolean alived = !ExtractorPattern.extractPattern(characterData, "Statut : (Vivant|Décédé)").equals("Décédé");
            int age = ExtractorPattern.extractPatternAge(characterData);
            crew = ExtractorPattern.fixCrew(crew, type);

            return new Characters(character, fruit, bounty, chapter, type, alived, age, crew, "" , 2 + 1);

        } catch (IOException e) {
            return null;
        }
    }
}