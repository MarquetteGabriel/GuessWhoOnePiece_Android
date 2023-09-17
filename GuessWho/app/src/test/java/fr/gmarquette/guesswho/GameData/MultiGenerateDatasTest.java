/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class MultiGenerateDatasTest {

    @Test
    public void extractValuesFromFandomTest()
    {
        String characterOne = "Charlotte Uiro";
        MultiGenerateDatas multiGenerateDatas = MultiGenerateDatas.getInstance();
        List<String> resultOne = multiGenerateDatas.extractValuesFromFandom(characterOne);

        // Assert on Nami
        {
        /*assertEquals(resultOne.get(0), "Nami");
        assertEquals(resultOne.get(1), "No_Fruit");
        assertEquals(resultOne.get(2), "366000000");
        assertEquals(resultOne.get(3), "8");
        assertEquals(resultOne.get(4), "Pirate");
        assertEquals(resultOne.get(5), "Vivant");
        assertEquals(resultOne.get(6), "20");*/
            multiGenerateDatas.getLevels();
            multiGenerateDatas.getMatchCharacterList();
            multiGenerateDatas.createCharacterFromInformation(resultOne, null);
        assertEquals(resultOne.get(7), "L'Équipage du Chapeau de Paille");
        }

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
        }
    }
}