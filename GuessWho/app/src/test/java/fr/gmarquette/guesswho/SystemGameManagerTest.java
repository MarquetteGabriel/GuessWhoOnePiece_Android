/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameSystem.AgeType;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyType;
import fr.gmarquette.guesswho.GameSystem.GameManager;

public class SystemGameManagerTest
{
    Characters testPirate = new Characters("Test Pirate", true, "300 Mi", 45, "Pirate", true, 26, "Pirate Crew", 0);
    Characters testRevo = new Characters("Test Revo", false, "3 Md", 450, "Revolutionary", false, 36, "Revo Crew", 0);
    Characters testNavy = new Characters("Test Navy", true, "0", 45, "Navy", true, 16, "Navy's Crew", 1);
    Characters testCitizen = new Characters("Test Citizen", true, "Unknown", 45, "Citizen", true, 26, "Citizen", 0);

    @Test
    public void testFruit()
    {
        Characters tester = new Characters("Tester", true, "0", 973, "Citizen", true, 76, "Citizen", 0);

        assertTrue(GameManager.hasEatenDevilFruit(tester, testPirate));
        assertFalse(GameManager.hasEatenDevilFruit(tester, testRevo));
    }

    @Test
    public void testBounty()
    {
        Characters testerMd = new Characters("Tester", true, "4 Md", 973, "Citizen", true, 76, "Citizen", 0);
        Characters testerMi = new Characters("Tester", true, "600 Mi", 973, "Citizen", true, 76, "Citizen", 0);
        Characters testerE = new Characters("Tester", true, "300 Mi", 973, "Citizen", true, 76, "Citizen", 0);
        Characters testerMi2 = new Characters("Tester", true, "100 Mi", 973, "Citizen", true, 76, "Citizen", 0);
        Characters tester0 = new Characters("Tester0", true, "0", 973, "Citizen", true, 76, "Citizen", 0);
        Characters testerUnknown = new Characters("Tester0", true, "Unknown", 973, "Citizen", true, 76, "Citizen", 0);
        Characters testerP = new Characters("TesterP", false, "700", 672, "Citizen", true, 34, "Citizen", 0);

        assertEquals(GameManager.lookingForBounty(testerMd, testPirate), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerMi, testPirate), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerMi2, testPirate), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(tester0, testPirate), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(testerUnknown, testPirate), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerE, testPirate), BountyType.EQUAL);
        assertEquals(GameManager.lookingForBounty(testerP, testPirate), BountyType.UPPER);

        assertEquals(GameManager.lookingForBounty(testerMd, testRevo), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerMi, testRevo), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(testerMi2, testRevo), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(tester0, testRevo), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(testerUnknown, testRevo), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerE, testRevo), BountyType.UPPER);
        assertEquals(GameManager.lookingForBounty(testerP, testRevo), BountyType.UPPER);

        assertEquals(GameManager.lookingForBounty(testerMd, testNavy), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerMi, testNavy), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerMi2, testNavy), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(tester0, testNavy), BountyType.EQUAL);
        assertEquals(GameManager.lookingForBounty(testerUnknown, testNavy), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerE, testNavy), BountyType.LOWER);
        assertEquals(GameManager.lookingForBounty(testerP, testNavy), BountyType.LOWER);

        assertEquals(GameManager.lookingForBounty(testerMd, testCitizen), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerMi, testCitizen), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerMi2, testCitizen), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(tester0, testCitizen), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerUnknown, testCitizen), BountyType.EQUAL);
        assertEquals(GameManager.lookingForBounty(testerE, testCitizen), BountyType.WRONG_UNKNOWN);
        assertEquals(GameManager.lookingForBounty(testerP, testCitizen), BountyType.WRONG_UNKNOWN);
    }

    @Test
    public void testAge()
    {
        assertEquals(GameManager.getAge(testPirate, testRevo), AgeType.YOUNGER);
        assertEquals(GameManager.getAge(testPirate, testNavy), AgeType.OLDER);
        assertEquals(GameManager.getAge(testPirate, testCitizen), AgeType.EQUAL);
    }


    @Test
    public void testAlive()
    {
        Characters tester = new Characters("Tester", true, "0", 973, "Citizen", false, 76, "Citizen", 0);




    }


}
