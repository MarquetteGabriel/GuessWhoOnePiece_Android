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
        ArmadaMugiwaraCrew();
        ChatNoirCrew();
        KriegCrew();
        FoxyCrew();
        MoriaCrew();
        LawCrew();
        KiddCrew();
        BegeCrew();
        BonneyCrew();
        UrougeCrew();
        KujaCrew();
        ShikiCrew();
        CaribouCrew();
        SunPiratesCrew();
        WeevilCrew();
        RogerCrew();
        RumbarCrew();
        DoffyCrew();
        FishermanCrew();
        BluejamCrew();
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
        Characters teach = new Characters("Marshall D. Teach", true, "3.996 Md", 223, "Pirate", true, 40, "Teach's Crew", 0);
        Characters burgess = new Characters("Jesus Burgess", true, "200 Mi", 222, "Pirate", true, 29, "Teach's Crew", 0);
        Characters shiliew = new Characters("Shiliew", true, "Unknown", 538, "Pirate", true, 44, "Teach's Crew", 0);
        Characters auger = new Characters("Van Auger", true, "640 Mi", 222, "Pirate", true, 27, "Teach's Crew", 0);
        Characters pizarro = new Characters("Avalo Pizarro", true, "Unknown", 575, "Pirate", true, 42, "Teach's Crew", 0);
        Characters laffitte = new Characters("Laffitte", true, "422 Mi", 234, "Pirate", true, 41, "Teach's Crew", 0);
        Characters devon = new Characters("Catarina Devon", true, "Unknown", 575, "Pirate", true, 36, "Teach's Crew", 0);
        Characters wolf = new Characters("Sanjuan Wolf", true, "Unknown", 575, "Pirate", true, 99, "Teach's Crew", 0);
        Characters vasco = new Characters("Vasco Shot", true, "Unknown", 575, "Pirate", true, 38, "Teach's Crew", 0);
        Characters doq = new Characters("Doc Q", true, "720 Mi", 223, "Pirate", true, 28, "Teach's Crew", 0);
        Characters kuzan = new Characters("Kuzan / Aokiji", true, "Unknown", 303, "Pirate", true, 49, "Teach's Crew", 0);
        //Characters pinkBeard = new Characters("Barbe Rose", true, "52000000", 904, "Pirate", true, ??, "Teach's Crew", 1);

        listCharacters.add(teach);
        listCharacters.add(burgess);
        listCharacters.add(shiliew);
        listCharacters.add(auger);
        listCharacters.add(pizarro);
        listCharacters.add(laffitte);
        listCharacters.add(devon);
        listCharacters.add(wolf);
        listCharacters.add(vasco);
        listCharacters.add(doq);
        listCharacters.add(kuzan);
    }

    private static void CrossGuild()
    {
        Characters baggy = new Characters("Baggy", true, "3.189 Md", 9, "Pirate", true, 39, "Cross Guild", 0);
        Characters mihawk = new Characters("Dracule Mihawk", false, " 3.590 Md", 49, "Pirate", true, 43, "Cross Guild", 0);
        Characters crocodile = new Characters("Crocodile", true, "1.965 Md", 126, "Pirate", true, 46, "Cross Guild", 0);
        Characters das = new Characters("Das Bones / Mr 1", true, "750 Mi", 160, "Pirate", true, 31, "Cross Guild", 0);
        Characters alvida = new Characters("Alvida", true, "5 Mi", 2, "Pirate", true, 27, "Cross Guild", 0);
        Characters galdino = new Characters("Galdino / Mr 3", true, "24 Mi", 117, "Pirate", true, 37, "Cross Guild", 1);
        Characters morge = new Characters("Morge", false, "Unknown", 9, "Pirate", true, 29, "Cross Guild", 1);
        Characters cabadji = new Characters("Cabadji", false, "Unknown", 9, "Pirate", true, 34, "Cross Guild", 1);

        listCharacters.add(baggy);
        listCharacters.add(mihawk);
        listCharacters.add(crocodile);
        listCharacters.add(das);
        listCharacters.add(alvida);
        listCharacters.add(galdino);
        listCharacters.add(morge);
        listCharacters.add(cabadji);
    }

    private static void NewgateCrew()
    {
        Characters newgate = new Characters("Edward Newgate", true, "5.500 Md", 234, "Pirate", false, 72, "Newgate's Crew", 0);
        Characters ace = new Characters("Portgas D. Ace", true, "550 Mi", 154, "Pirate", false, 20, "Newgate's Crew", 0);
        Characters joz = new Characters("Joz", true, "Unknown", 234, "Pirate", true, 42, "Newgate's Crew", 0);
        //Characters thatch = new Characters("Thatch", false, "Unknown", 440, "Pirate", false, ??, "Newgate's Crew", 1);
        Characters vista = new Characters("Vista", false, "Unknown", 552, "Pirate", true, 47, "Newgate's Crew", 0);
        Characters squardo = new Characters("Sqaurdo", false, "210 Mi", 551, "Pirate", true, 52, "Newgate's Crew", 1);
        Characters ozjr = new Characters("Little Oz Junior", false, "550 Mi", 554, "Pirate", true, 72, "Newgate's Crew", 1);

        listCharacters.add(newgate);
        listCharacters.add(ace);
        listCharacters.add(joz);
        listCharacters.add(vista);
        listCharacters.add(squardo);
        listCharacters.add(ozjr);
    }

    private static void BigMomCrew()
    {
        Characters bigmom = new Characters("Charlotte Linlin / BigMom", true, "4.388 Md", 651, "Pirate", true, 68, "BigMom's Crew", 0);
        Characters katakuri = new Characters("Charlotte Katakuri", true, "1.057 Md", 860, "Pirate", true, 48, "BigMom's Crew", 0);
        Characters smoothie = new Characters("Charlotte Smoothie", true, "932 Mi", 846, "Pirate", true, 35, "BigMom's Crew", 0);
        Characters cracker = new Characters("Charlotte Cracker", true, "860 Mi", 835, "Pirate", true, 45, "BigMom's Crew", 0);
        Characters perospero = new Characters("Charlotte Perospero", true, "700 Mi", 834, "Pirate", true, 50, "BigMom's Crew", 0);
        Characters daifuku = new Characters("Charlotte Daifuku", true, "300 Mi", 861, "Pirate", true, 48, "BigMom's Crew", 0);
        Characters oven = new Characters("Charlotte Oven", true, "300 Mi", 861, "Pirate", true, 48, "BigMom's Crew", 1);
        Characters amande = new Characters("Charlotte Amande", false, "Unknown", 827, "Pirate", true, 47, "BigMom's Crew", 1);
        Characters opera = new Characters("Charlotte Opéra", true, "Unknown", 829, "Pirate", true, 46, "BigMom's Crew", 1);
        Characters brulee = new Characters("Charlotte Brulée", true, "Unknown", 831, "Pirate", true, 43, "BigMom's Crew", 0);
        Characters montdor = new Characters("Charlotte Mont d'or", true, "120 Mi", 829, "Pirate", true, 38, "BigMom's Crew", 1);
        Characters galette = new Characters("Charlotte Galette", true, "Unknown", 829, "Pirate", true, 31, "BigMom's Crew", 1);
        Characters snack = new Characters("Charlotte Snack", true, "600 Mi", 894, "Pirate", true, 30, "BigMom's Crew", 1);
        Characters pudding = new Characters("Charlotte Pudding", true, "Unknown", 651, "Pirate", true, 16, "BigMom's Crew", 0);
        Characters flampe = new Characters("Charlotte Flampe", false, "Unknown", 891, "Pirate", true, 15, "BigMom's Crew", 1);

        listCharacters.add(bigmom);
        listCharacters.add(katakuri);
        listCharacters.add(smoothie);
        listCharacters.add(cracker);
        listCharacters.add(perospero);
        listCharacters.add(daifuku);
        listCharacters.add(oven);
        listCharacters.add(amande);
        listCharacters.add(opera);
        listCharacters.add(brulee);
        listCharacters.add(montdor);
        listCharacters.add(galette);
        listCharacters.add(snack);
        listCharacters.add(pudding);
        listCharacters.add(flampe);

    }

    private static void KaidoCrew()
    {
        Characters kaido = new Characters("Kaido", true, "4.611 Md", 795, "Pirate", true, 59, "Kaido's Crew", 0);
        Characters king = new Characters("King / Alber", true, "1.390 Md", 925, "Pirate", true, 47, "Kaido's Crew", 0);
        Characters queen = new Characters("Queen", true, "1.320 Md", 925, "Pirate", true, 56, "Kaido's Crew", 0);
        Characters jack = new Characters("Jack", true, "1 Md", 801, "Pirate", true, 28, "Kaido's Crew", 0);
        Characters pageone = new Characters("Page One", true, "290 Mi", 929, "Pirate", true, 20, "Kaido's Crew", 1);
        Characters ulti = new Characters("Ulti", true, "400 Mi", 977, "Pirate", true, 22, "Kaido's Crew", 1);
        Characters whoswho = new Characters("Who's who", true, "546 Mi", 977, "Pirate", true, 38, "Kaido's Crew", 1);
        Characters blackmaria = new Characters("Black Maria", true, "480 Mi", 977, "Pirate", true, 29, "Kaido's Crew", 1);
        Characters sasaki = new Characters("Sasaki", true, "472 Mi", 977, "Pirate", true, 34, "Kaido's Crew", 1);
        Characters hawkins = new Characters("Basil Hawkins", true, "320 Md", 498, "Pirate", true, 31, "Kaido's Crew", 0);
        Characters apoo = new Characters("Scratchmen Apoo", true, "350 Mi", 498, "Pirate", true, 31, "Kaido's Crew", 0);

        listCharacters.add(kaido);
        listCharacters.add(king);
        listCharacters.add(queen);
        listCharacters.add(jack);
        listCharacters.add(pageone);
        listCharacters.add(ulti);
        listCharacters.add(whoswho);
        listCharacters.add(blackmaria);
        listCharacters.add(sasaki);
        listCharacters.add(hawkins);
        listCharacters.add(apoo);

    }

    private static void ArmadaMugiwaraCrew()
    {

    }

    private static void ChatNoirCrew()
    {

    }

    private static void KriegCrew()
    {

    }

    private static void FoxyCrew()
    {

    }

    private static void MoriaCrew()
    {

    }

    private static void LawCrew()
    {

    }

    private static void KiddCrew()
    {

    }

    private static void BegeCrew()
    {

    }

    private static void BonneyCrew()
    {

    }

    private static void UrougeCrew()
    {

    }

    private static void KujaCrew()
    {

    }

    private static void ShikiCrew()
    {

    }

    private static void CaribouCrew()
    {

    }

    private static void SunPiratesCrew()
    {

    }

    private static void WeevilCrew()
    {

    }

    private static void RogerCrew()
    {

    }

    private static void RumbarCrew()
    {

    }

    private static void DoffyCrew()
    {

    }

    private static void FishermanCrew()
    {

    }

    private static void BluejamCrew()
    {

    }

    private static void EnerCrew()
    {
        Characters ener = new Characters("Ener", true, "Unknown", 254, "Pirate", true, 39, "Ener's Crew", 0);

        listCharacters.add(ener);
    }
}
