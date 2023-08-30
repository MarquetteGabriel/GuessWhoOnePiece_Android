/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class GenerateDatasTest {

    @Test
    public void extractValuesFromFandomTest()
    {
        String characterOne = "Nami";
        GenerateDatas generateDatas = new GenerateDatas();
        List<String> resultOne = generateDatas.extractValuesFromFandom(characterOne);

        // Assert on Nami
        {
        assertEquals(resultOne.get(0), "Nami");
        assertEquals(resultOne.get(1), "No_Fruit");
        assertEquals(resultOne.get(2), "366000000");
        assertEquals(resultOne.get(3), "8");
        assertEquals(resultOne.get(4), "Pirate");
        assertEquals(resultOne.get(5), "Vivant");
        assertEquals(resultOne.get(6), "20");
        assertEquals(resultOne.get(7), "L'Équipage du Chapeau de Paille");
        }

        String characterTwo = "Jango";
        List<String> resultTwo = generateDatas.extractValuesFromFandom(characterTwo);
        
        // Assert on Jango
        {
            assertEquals(resultTwo.get(0), "Jango");
            assertEquals(resultTwo.get(1), "No_Fruit");
            //assertEquals(resultTwo.get(2), "0");
            assertEquals(resultTwo.get(3), "25");
            //assertEquals(resultTwo.get(4), "Marine");
            assertEquals(resultTwo.get(5), "Vivant");
            assertEquals(resultTwo.get(6), "29");
            //assertEquals(resultTwo.get(7), "Marine");
        }

        String characterThree = "Rob Lucci";
        List<String> resultThree = generateDatas.extractValuesFromFandom(characterThree);

        // Assert on Jango
        {
            assertEquals(resultThree.get(0), "Rob Lucci");
            assertEquals(resultThree.get(1), "Fruit");
            //assertEquals(resultThree.get(2), "0");
            assertEquals(resultThree.get(3), "323");
            //assertEquals(resultThree.get(4), "Marine");
            assertEquals(resultThree.get(5), "Vivant");
            assertEquals(resultThree.get(6), "30");
            //assertEquals(resultThree.get(7), "Ciper Pol");
        }
    }

    /*
    public void getDatasFromOutside(Context context) {
    }

    public void extractValuesFromWiki() {
    }

    public List<String> extractValuesFromFandom(String character) {
    }

    public static String extractPattern(String input, String pattern) {
    }

    public static String extractPatternAge(String input, String pattern) {
    }

    public void createCharacterFromInformation(List<String> characterData, Context context) {
    }

    private boolean stateAlive(String state) {
    }

    private static String setOccupation(String text) {
    }*/

}