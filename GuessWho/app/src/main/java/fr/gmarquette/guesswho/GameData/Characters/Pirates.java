/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import java.util.ArrayList;
import java.util.List;

import fr.gmarquette.guesswho.GameData.Database.Characters;

class Pirates {


    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getPirates() {
        MugiwaraCrew();
        ShanksCrew();
        TeachCrew();
        CrossGuild();
        NewgateCrew();
        BigMomCrew();
        KaidoCrew();

        EnerCrew();


        return listCharacters;
    }

    private static void MugiwaraCrew()
    {
        Characters luffy = new Characters("Monkey D. Luffy", true, "3000000000", 1, "Pirate", true, 19, "Mugiwara's Crew", 0);
        Characters zoro = new Characters("Roronoa Zoro", false, "1111000000", 3, "Pirate", true, 21, "Mugiwara's Crew", 0);
        Characters nami = new Characters("Nami", false, "366000000", 8, "Pirate", true, 20, "Mugiwara's Crew", 0);
        Characters usopp = new Characters("Usopp", false, "500000000", 23, "Pirate", true, 19, "Mugiwara's Crew", 0);
        Characters sanji = new Characters("Vinsmoke Sanji", false, "1032000000", 43, "Pirate", true, 21, "Mugiwara's Crew", 0);
        Characters chopper = new Characters("Tony-Tony Chopper", true, "1000", 134, "Pirate", true, 17, "Mugiwara's Crew", 0);
        Characters robin = new Characters("Nico Robin", true, "930000000", 114, "Pirate", true, 30, "Mugiwara's Crew", 0);
        Characters franky = new Characters("Franky / Cutty Flam", false, "394000000", 329, "Pirate", true, 36, "Mugiwara's Crew", 0);
        Characters brook = new Characters("Brook", true, "383000000", 442, "Pirate", true, 90, "Mugiwara's Crew", 0);
        Characters jinbei = new Characters("Jinbei", false, "1100000000", 528, "Pirate", true, 46, "Mugiwara's Crew", 0);

        listCharacters.add(luffy);
        listCharacters.add(zoro);
        listCharacters.add(nami);
        listCharacters.add(usopp);
        listCharacters.add(sanji);
        listCharacters.add(chopper);
        listCharacters.add(robin);
        listCharacters.add(franky);
        listCharacters.add(brook);
        listCharacters.add(jinbei);
    }

    private static void ShanksCrew()
    {

    }

    private static void TeachCrew()
    {

    }

    private static void CrossGuild()
    {

    }

    private static void NewgateCrew()
    {
        Characters ace = new Characters("Portgas D. Ace", true, "550000000", 154, "Pirate", false, 20, "Newgate's Crew", 0);

        listCharacters.add(ace);
    }

    private static void BigMomCrew()
    {
        Characters katakuri = new Characters("Charlotte Katakuri", true, "1057000000", 860, "Pirate", true, 48, "BigMom's Crew", 0);

        listCharacters.add(katakuri);
    }

    private static void KaidoCrew()
    {

    }

    private static void EnerCrew()
    {
        Characters ener = new Characters("Ener", true, "Unknown", 254, "Pirate", true, 39, "Ener's Crew", 0);

        listCharacters.add(ener);
    }
}
