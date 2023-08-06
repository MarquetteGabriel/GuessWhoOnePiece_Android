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
        Characters luffy = new Characters("Monkey D. Luffy", true, "3 Md", 1, "Pirate", true, 19, "Mugiwara's Crew", 0);
        Characters zoro = new Characters("Roronoa Zoro", false, "1.111 Md", 3, "Pirate", true, 21, "Mugiwara's Crew", 0);
        Characters nami = new Characters("Nami", false, "366 Mi", 8, "Pirate", true, 20, "Mugiwara's Crew", 0);
        Characters usopp = new Characters("Usopp", false, "500 Mi", 23, "Pirate", true, 19, "Mugiwara's Crew", 0);
        Characters sanji = new Characters("Vinsmoke Sanji", false, "1.032 Md", 43, "Pirate", true, 21, "Mugiwara's Crew", 0);
        Characters chopper = new Characters("Tony-Tony Chopper", true, "1000", 134, "Pirate", true, 17, "Mugiwara's Crew", 0);
        Characters robin = new Characters("Nico Robin", true, "930 Mi", 114, "Pirate", true, 30, "Mugiwara's Crew", 0);
        Characters franky = new Characters("Franky / Cutty Flam", false, "394 Mi", 329, "Pirate", true, 36, "Mugiwara's Crew", 0);
        Characters brook = new Characters("Brook", true, "383 Mi", 442, "Pirate", true, 90, "Mugiwara's Crew", 0);
        Characters jinbei = new Characters("Jinbei", false, "1.1 Md", 528, "Pirate", true, 46, "Mugiwara's Crew", 0);

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
        Characters shanks = new Characters("Shanks", false, "4.0489 Md", 1, "Pirate", true, 39, "Shanks's Crew", 0);
        Characters beckman = new Characters("Ben Beckmann", false, "Unknown", 1, "Pirate", true, 50, "Shanks's Crew", 0);
        Characters roo = new Characters("Lucky Roo", false, "Unknown", 1, "Pirate", true, 35, "Shanks's Crew", 1);
        Characters yasopp = new Characters("Yasopp", false, "Unknown", 1, "Pirate", true, 47, "Shanks's Crew", 1);
        //Characters limejuice = new Characters("Limejuice", false, "Unknown", 41, "Pirate", true, ??, "Shanks's Crew", 1);
        //Characters bonk = new Characters("Bonk Punch", false, "Unknown", 1, "Pirate", true, ??, "Shanks's Crew", 1);
        //Characters monster = new Characters("Monster", false, "Unknown", 1, "Pirate", true, ??, "Shanks's Crew", 1);
        //Characters snake = new Characters("Building Snake", false, "Unknown", 41, "Pirate", true, ??, "Shanks's Crew", 1);
        //Characters hongo = new Characters("Hongo", false, "Unknown", 1, "Pirate", true, 90, "Shanks's Crew", 1);
        //Characters howling = new Characters("Howling Dab", false, "Unknown", 580, "Pirate", true, ??, "Shanks's Crew", 1);
        //Characters rockstar = new Characters("Rockstar", false, "940 Mi", 234, "Pirate", true, ??, "Shanks's Crew", 1);
        Characters uta = new Characters("Uta", true, "Unknown", 1055, "Pirate", true, 21, "Shanks's Crew", 1);


        listCharacters.add(shanks);
        listCharacters.add(beckman);
        listCharacters.add(roo);
        listCharacters.add(yasopp);
        //listCharacters.add(limejuice);
        //listCharacters.add(bonk);
        //listCharacters.add(monster);
        //listCharacters.add(snake);
        //listCharacters.add(hongo);
        //listCharacters.add(howling);
        //listCharacters.add(rockstar);
        listCharacters.add(uta);
    }

    private static void TeachCrew()
    {

    }

    private static void CrossGuild()
    {

    }

    private static void NewgateCrew()
    {
        Characters ace = new Characters("Portgas D. Ace", true, "550 Mi", 154, "Pirate", false, 20, "Newgate's Crew", 0);

        listCharacters.add(ace);
    }

    private static void BigMomCrew()
    {
        Characters katakuri = new Characters("Charlotte Katakuri", true, "1.057 Md", 860, "Pirate", true, 48, "BigMom's Crew", 0);

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
