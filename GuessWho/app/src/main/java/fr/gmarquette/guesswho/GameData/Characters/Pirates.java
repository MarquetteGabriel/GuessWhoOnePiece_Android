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
        CaribouCrew();
        SunPiratesCrew();
        WeevilCrew();
        RogerCrew();
        RumbarCrew();
        DoffyCrew();
        FishermanCrew();
        BluejamCrew();
        EnerCrew();
        MonkeyCrew();
        ArlongCrew();
        FalseMugiwaraCrew();
        CaesarCrew();
        BaroqueWorks();
        Solos();

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
        Characters marco = new Characters("Marco", true, "1.374 Md", 234, "Pirate", true, 45, "Newgate's Crew", 0);
        Characters izo = new Characters("Izo", false, "510 Mi", 553, "Pirate", false, 45, "Newgate's Crew", 0);

        listCharacters.add(newgate);
        listCharacters.add(marco);
        listCharacters.add(ace);
        listCharacters.add(joz);
        listCharacters.add(vista);
        listCharacters.add(squardo);
        listCharacters.add(ozjr);
        listCharacters.add(izo);
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
        Characters laura = new Characters("Charlotte Laura", false, "24 Mi", 476, "Pirate", true, 26, "Laura's Crew", 1);

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
        listCharacters.add(laura);

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
        Characters cavendish = new Characters("Cavendish", false, "330 Mi", 704, "Pirate", true, 26, "Mugiwara's Armada Crew", 0);
        Characters suleiman = new Characters("Suleiman", false, "67 Mi", 704, "Pirate", true, 40, "Mugiwara's Armada Crew", 1);
        Characters bartolomeo = new Characters("Bartolomeo", true, "200 Mi", 705, "Pirate", true, 24, "Mugiwara's Armada Crew", 0);
        //Characters gambia = new Characters("Gambia", false, "67 Mi", 705, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        Characters sai = new Characters("Sai", false, "210 Mi", 704, "Pirate", true, 28, "Mugiwara's Armada Crew", 0);
        //Characters boo = new Characters("Boo", false, "Unkwown", 704, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        Characters baby = new Characters("Baby 5", true, "Unkwown", 682, "Pirate", true, 24, "Mugiwara's Armada Crew", 1);
        Characters ideo = new Characters("Ideo", false, "Unkwown", 706, "Pirate", true, 22, "Mugiwara's Armada Crew", 0);
        Characters bluegilly = new Characters("Blue Gilly", false, "Unkwown", 706, "Pirate", true, 24, "Mugiwara's Armada Crew", 1);
        //Characters abdullah = new Characters("Abdullah", false, "Unkwown", 704, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        //Characters jeet = new Characters("Jeet", false, "Unkwown", 704, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        Characters leo = new Characters("Léo", true, "Unkwown", 710, "Pirate", true, 25, "Mugiwara's Armada Crew", 0);
        //Characters kabu = new Characters("Kabu", true, "Unkwown", 710, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        //Characters beeanne = new Characters("Bee Anne", true, "Unkwown", 717, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        Characters hajrudin = new Characters("Hajrudin", false, "Unkwown", 706, "Pirate", true, 81, "Mugiwara's Armada Crew", 0);
        //Characters stansen = new Characters("Stansen", false, "Unkwown", 500, "Pirate", true, , "Mugiwara's Armada Crew", 1);
        Characters road = new Characters("Road", false, "Unkwown", 898, "Pirate", true, 63, "Mugiwara's Armada Crew", 1);
        Characters goldberg = new Characters("Goldberg", false, "Unkwown", 899, "Pirate", true, 63, "Mugiwara's Armada Crew", 1);
        Characters gerd = new Characters("Gerd", false, "Unkwown", 866, "Pirate", true, 75, "Mugiwara's Armada Crew", 1);
        Characters orlumbus = new Characters("Orlumbus", false, "148 Mi", 704, "Pirate", true, 42, "Mugiwara's Armada Crew", 0);

        listCharacters.add(cavendish);
        listCharacters.add(suleiman);
        listCharacters.add(bartolomeo);
        //listCharacters.add(gambia);
        listCharacters.add(sai);
        //listCharacters.add(boo);
        listCharacters.add(baby);
        listCharacters.add(ideo);
        listCharacters.add(bluegilly);
        //listCharacters.add(abdullah);
        //listCharacters.add(jeet);
        listCharacters.add(leo);
        //listCharacters.add(kabu);
        //listCharacters.add(beeanne);
        listCharacters.add(hajrudin);
        //listCharacters.add(stansen);
        listCharacters.add(road);
        listCharacters.add(goldberg);
        listCharacters.add(gerd);
        listCharacters.add(orlumbus);
    }

    private static void ChatNoirCrew()
    {
        Characters kuro = new Characters("Kuro", false, "16 Mi", 23, "Pirate", true, 35, "Black Cat's Crew", 0);
        Characters sham = new Characters("Sham", false, "7 Mi", 31, "Pirate", true, 23, "Black Cat's Crew", 1);
        Characters buchi = new Characters("Buchi", false, "7 Mi", 31, "Pirate", true, 23, "Black Cat's Crew", 1);

        listCharacters.add(kuro);
        listCharacters.add(sham);
        listCharacters.add(buchi);
    }

    private static void KriegCrew()
    {
        Characters krieg = new Characters("Don Krieg", false, "17 Mi", 45, "Pirate", true, 44, "Don Krieg's Crew", 0);
        Characters gyn = new Characters("Gyn", false, "12 Mi", 44, "Pirate", true, 27, "Don Krieg's Crew", 0);
        Characters pearl = new Characters("Pearl", false, "Unkwown", 54, "Pirate", true, 25, "Don Krieg's Crew", 1);

        listCharacters.add(krieg);
        listCharacters.add(gyn);
        listCharacters.add(pearl);
    }

    private static void MonkeyCrew()
    {
        Characters cricket = new Characters("Montblanc Cricket", false, "25 Mi", 227, "Pirate", true, 43, "Monkey's Forces Crew", 0);
        Characters masira = new Characters("Masira", false, "23 Mi", 219, "Pirate", true, 25, "Monkey's Forces Crew", 0);
        Characters shojo = new Characters("Shojo", false, "36 Mi", 226, "Pirate", true, 27, "Monkey's Forces Crew", 0);

        listCharacters.add(cricket);
        listCharacters.add(masira);
        listCharacters.add(shojo);
    }

    private static void FoxyCrew()
    {
        Characters foxy = new Characters("Foxy", true, "24 Mi", 305, "Pirate", true, 38, "Foxy's Crew", 0);
        //Characters porche = new Characters("Porché", false, "Unkwown", 305, "Pirate", true, , "Foxy's Crew", 1);
        //Characters hamburg = new Characters("Hamburg", false, "Unkwown", 305, "Pirate", true, , "Foxy's Crew", 1);

        listCharacters.add(foxy);
        //listCharacters.add(porche);
        //listCharacters.add(hamburg);
    }

    private static void MoriaCrew()
    {
        Characters moria = new Characters("Gecko Moria", true, "320 Mi", 449, "Pirate", true, 50, "Moria's Crew", 0);
        Characters hogback = new Characters("Hogback", false, "Unkwown", 446, "Pirate", true, 47, "Moria's Crew", 0);
        Characters absalom = new Characters("Absalom", true, "Unkwown", 444, "Pirate", false, 36, "Moria's Crew", 0);
        Characters perona = new Characters("Perona", true, "Unkwown", 443, "Pirate", true, 25, "Moria's Crew", 0);

        listCharacters.add(moria);
        listCharacters.add(hogback);
        listCharacters.add(absalom);
        listCharacters.add(perona);
    }

    private static void LawCrew()
    {
        Characters law = new Characters("Trafalgar D. Water Law", true, "3 Md", 498, "Pirate", true, 26, "Heart's Crew", 0);
        Characters bepo = new Characters("Bepo", false, "500", 498, "Pirate", true, 22, "Heart's Crew", 0);
        //Characters jeanbart = new Characters("Jean Bart", false, "Unkwown", 497, "Pirate", true, , "Heart's Crew", 1);
        Characters shachi = new Characters("Shachi", false, "Unkwown", 498, "Pirate", true, 27, "Heart's Crew", 1);
        Characters pingouin = new Characters("Pingouin", false, "Unkwown", 498, "Pirate", true, 28, "Heart's Crew", 1);

        listCharacters.add(law);
        listCharacters.add(bepo);
        listCharacters.add(shachi);
        listCharacters.add(pingouin);
    }

    private static void KiddCrew()
    {
        Characters kidd = new Characters("Eustass Kidd", true, "3 Md", 498, "Pirate", true, 23, "Kidd's Crew", 0);
        Characters killer = new Characters("Killer", false, "200 Mi", 498, "Pirate", true, 27, "Kidd's Crew", 0);

        listCharacters.add(kidd);
        listCharacters.add(killer);
    }

    private static void BegeCrew()
    {
        Characters bege = new Characters("Capone Bege", true, "350 Mi", 498, "Pirate", true, 42, "Bege's Crew", 0);
        Characters vito = new Characters("Vito", false, "95 Mi", 812, "Pirate", true, 36, "Bege's Crew", 1);
        Characters gotti = new Characters("Gotti", false, "90 Mi", 825, "Pirate", true, 33, "Bege's Crew", 1);
        Characters chiffon = new Characters("Charlotte Chiffon", false, "Unkwown", 825, "Pirate", true, 26, "Bege's Crew", 1);
        Characters pets = new Characters("Charlotte Pets", false, "Unkwown", 834, "Pirate", true, 1, "Bege's Crew", 1);


        listCharacters.add(bege);
        listCharacters.add(vito);
        listCharacters.add(gotti);
        listCharacters.add(chiffon);
        listCharacters.add(pets);
    }

    private static void BonneyCrew()
    {
        Characters bonney = new Characters("Jewelry Bonney", true, "320 Mi", 498, "Pirate", true, 24, "Bonney's Crew", 0);
        listCharacters.add(bonney);
    }

    private static void UrougeCrew()
    {
        Characters urouge = new Characters("Urouge", true, "108 Mi", 498, "Pirate", true, 47, "Urouge's Crew", 0);
        listCharacters.add(urouge);
    }

    private static void KujaCrew()
    {
        Characters hancock = new Characters("Boa Hancock", true, "1.659 Md", 516, "Pirate", true, 31, "Kuja's Crew", 0);
        Characters sandersonia = new Characters("Boa Sandersonia", true, "40 Mi", 516, "Pirate", true, 30, "Kuja's Crew", 0);
        Characters marigold = new Characters("Boa Marigold", true, "40 Mi", 516, "Pirate", true, 28, "Kuja's Crew", 0);
        Characters margaret = new Characters("Margaret", false, "Unkwown", 514, "Pirate", true, 18, "Kuja's Crew", 1);

        listCharacters.add(hancock);
        listCharacters.add(sandersonia);
        listCharacters.add(marigold);
        listCharacters.add(margaret);
    }

    private static void CaribouCrew()
    {
        Characters caribou = new Characters("Caribou", true, "210 Mi", 600, "Pirate", true, 32, "Caribou's Crew", 0);
        Characters coribou = new Characters("Coribou", false, "190 Mi", 600, "Pirate", true, 29, "Caribou's Crew", 1);

        listCharacters.add(caribou);
        listCharacters.add(coribou);
    }

    private static void SunPiratesCrew()
    {
        Characters fishertiger = new Characters("Fisher Tiger", false, "230 Mi", 521, "Pirate", false, 48, "Sun Pirates' Crew", 0);
        Characters aladdin = new Characters("Aladdin", false, "Unkwown", 620, "Pirate", true, 46, "Sun Pirates' Crew", 0);
        Characters praline = new Characters("Charlotte Praliné", false, "Unkwown", 830, "Pirate", true, 29, "Sun Pirates' Crew", 1);
        Characters wadatsumi = new Characters("Wadatsumi", false, "Unkwown", 606, "Pirate", true, 25, "Sun Pirates' Crew", 1);
        listCharacters.add(fishertiger);
        listCharacters.add(aladdin);
        listCharacters.add(praline);
        listCharacters.add(wadatsumi);
    }

    private static void WeevilCrew()
    {
        Characters weeble = new Characters("Edward Weeble", false, "480 Mi", 802, "Pirate", true, 35, "Weeble's Crew", 0);
        Characters stussy = new Characters("Buckingham Stussy", false, "Unkwown", 802, "Pirate", true, 76, "Weeble's Crew", 1);
        listCharacters.add(weeble);
        listCharacters.add(stussy);
    }

    private static void RogerCrew()
    {
        Characters roger = new Characters("Gol D. Roger", false, "5.5648 Md", 1, "Pirate", false, 53, "Roger's Crew", 0);
        //Characters gaban = new Characters("Scopper Gaban", false, "Unkwown", 19, "Pirate", true, , "Roger's Crew", 0);
        Characters rayleigh = new Characters("Silvers Rayleigh", false, "Unknown", 19, "Pirate", true, 78, "Roger's Crew", 0);
        Characters oden = new Characters("Oden Kozuki", false, "0", 920, "Pirate", false, 39, "Roger's Crew", 0);

        listCharacters.add(roger);
        //listCharacters.add(gaban);
        listCharacters.add(rayleigh);
        listCharacters.add(oden);
    }

    private static void ArlongCrew()
    {
        Characters arlong = new Characters("Arlong", false, "20 Mi", 69, "Pirate", true, 41, "Arlong's Crew", 0);
        Characters kuroobi = new Characters("Kuroobi", false, "9 Mi", 69, "Pirate", true, 38, "Arlong's Crew", 0);
        Characters smack = new Characters("Smack", false, "5.5 Mi", 69, "Pirate", true, 35, "Arlong's Crew", 0);

        listCharacters.add(arlong);
        listCharacters.add(kuroobi);
        listCharacters.add(smack);
    }

    private static void RumbarCrew()
    {
        //Characters yorki = new Characters("Yorki", false, "Unkwown", 103, "Pirate", false, , "Rumbar's Crew", 1);
        //listCharacters.add(yorki);
    }

    private static void DoffyCrew()
    {
        Characters doflamingo = new Characters("Don Quijote Doflamingo", true, "340 Mi", 233, "Pirate", true, 41, "Doflamingo's Crew", 0);
        Characters trebol = new Characters("Trébol", true, "99 Mi", 700, "Pirate", true, 49, "Doflamingo's Crew", 0);
        Characters diamante = new Characters("Diamante", true, "99 Mi", 700, "Pirate", true, 45, "Doflamingo's Crew", 0);
        Characters pica = new Characters("Pica", true, "99 Mi", 700, "Pirate", true, 40, "Doflamingo's Crew", 0);
        Characters vergo = new Characters("Vergo", false, "Unkwown", 671, "Pirate", false, 41, "Doflamingo's Crew", 0);
        Characters sugar = new Characters("Sugar", true, "Unkwown", 682, "Pirate", true, 22, "Doflamingo's Crew", 0);
        Characters jora = new Characters("Jora", true, "Unkwown", 682, "Pirate", true, 61, "Doflamingo's Crew", 0);
        Characters laog = new Characters("Lao G", false, "61 Mi", 682, "Pirate", true, 70, "Doflamingo's Crew", 0);
        Characters senorpink = new Characters("Señor Pink", true, "58 Mi", 702, "Pirate", true, 46, "Doflamingo's Crew", 0);
        Characters machvise = new Characters("Machvise", true, "11 Mi", 682, "Pirate", true, 52, "Doflamingo's Crew", 0);
        Characters dellinger = new Characters("Dellinger", false, "15 Mi", 702, "Pirate", true, 16, "Doflamingo's Crew", 0);
        Characters gladius = new Characters("Gladius", true, "31 Mi", 682, "Pirate", true, 33, "Doflamingo's Crew", 0);
        Characters buffalo = new Characters("Buffalo", true, "Unkwown", 692, "Pirate", true, 33, "Doflamingo's Crew", 0);
        Characters monet = new Characters("Monet", true, "Unkwown", 657, "Pirate", false, 30, "Doflamingo's Crew", 0);
        Characters bellamy = new Characters("Bellamy", true, "195 Mi", 222, "Pirate", true, 27, "Doflamingo's Crew", 0);
        Characters sarquiss = new Characters("Sarquiss", false, "38 Mi", 222, "Pirate", false, 27, "Doflamingo's Crew", 1);

        listCharacters.add(doflamingo);
        listCharacters.add(trebol);
        listCharacters.add(diamante);
        listCharacters.add(pica);
        listCharacters.add(vergo);
        listCharacters.add(sugar);
        listCharacters.add(jora);
        listCharacters.add(laog);
        listCharacters.add(senorpink);
        listCharacters.add(machvise);
        listCharacters.add(dellinger);
        listCharacters.add(gladius);
        listCharacters.add(buffalo);
        listCharacters.add(monet);
        listCharacters.add(bellamy);
        listCharacters.add(sarquiss);
    }

    private static void FishermanCrew()
    {
        Characters hody = new Characters("Hody Jones", false, "Unkwown", 608, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters dosun = new Characters("Dosun", false, "Unkwown", 611, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters zeo = new Characters("Zeo", false, "Unkwown", 611, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters daruma = new Characters("Daruma", false, "Unkwown", 611, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters ikaros = new Characters("Ikaros Much", false, "Unkwown", 611, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters hyozo = new Characters("Hyozo", false, "Unkwown", 607, "Pirate", true, 30, "Fishmen's Crew", 0);
        Characters decken = new Characters("Vander Decken IX", true, "Unkwown", 606, "Pirate", true, 35, "Fishmen's Crew", 0);

        listCharacters.add(hody);
        listCharacters.add(dosun);
        listCharacters.add(zeo);
        listCharacters.add(daruma);
        listCharacters.add(ikaros);
        listCharacters.add(hyozo);
        listCharacters.add(decken);
    }

    private static void BluejamCrew()
    {
        Characters bluejam = new Characters("Bluejam", false, "14.3 Mi", 584, "Pirate", false, 42, "Bluejam's Crew", 0);
        Characters porchemy = new Characters("Porchemy", false, "3.4 Mi", 583, "Pirate", false, 25, "Bluejam's Crew", 1);
        listCharacters.add(bluejam);
        listCharacters.add(porchemy);
    }

    private static void EnerCrew()
    {
        Characters ener = new Characters("Ener", true, "Unknown", 254, "Pirate", true, 39, "Ener's Crew", 0);
        Characters om = new Characters("Om", false, "0", 241, "Pirate", true, 29, "Ener's Crew", 0);
        Characters satori = new Characters("Satori", false, "0", 241, "Pirate", true, 27, "Ener's Crew", 0);
        Characters shura = new Characters("Shura", false, "0", 241, "Pirate", true, 33, "Ener's Crew", 0);
        Characters yama = new Characters("Yama", false, "0", 254, "Pirate", true, 45, "Ener's Crew", 1);
        Characters hotori = new Characters("Hotori", false, "0", 261, "Pirate", true, 27, "Ener's Crew", 1);
        Characters kotori = new Characters("Kotori", false, "0", 261, "Pirate", true, 27, "Ener's Crew", 1);
        Characters gedatsu = new Characters("Gedatsu", false, "Unknown", 31, "Pirate", true, 31, "Ener's Crew", 0);

        listCharacters.add(ener);
        listCharacters.add(om);
        listCharacters.add(satori);
        listCharacters.add(shura);
        listCharacters.add(yama);
        listCharacters.add(hotori);
        listCharacters.add(kotori);
        listCharacters.add(gedatsu);
    }

    private static void FalseMugiwaraCrew()
    {
        Characters demaro = new Characters("Demaro Black", false, "26 Mi", 598, "Pirate", true, 36, "False Mugiwara's Crew", 0);
        Characters manjaro = new Characters("Manjaro", false, "Unkwown", 598, "Pirate", true, 25, "False Mugiwara's Crew", 1);
        Characters chocolat = new Characters("Chocolat", false, "Unkwown", 598, "Pirate", true, 26, "False Mugiwara's Crew", 1);
        Characters mounblutain = new Characters("Mounblutain", false, "Unkwown", 598, "Pirate", true, 30, "False Mugiwara's Crew", 1);
        Characters drip = new Characters("Drip", false, "Unkwown", 598, "Pirate", true, 24, "False Mugiwara's Crew", 1);
        Characters nora = new Characters("Nora Gitsune", false, "Unkwown", 598, "Pirate", true, 13, "False Mugiwara's Crew", 1);
        Characters cocoa = new Characters("Cocoa", false, "Unkwown", 598, "Pirate", true, 27, "False Mugiwara's Crew", 1);
        Characters turco = new Characters("Turco", false, "Unkwown", 598, "Pirate", true, 45, "False Mugiwara's Crew", 1);
        listCharacters.add(demaro);
        listCharacters.add(manjaro);
        listCharacters.add(chocolat);
        listCharacters.add(mounblutain);
        listCharacters.add(drip);
        listCharacters.add(nora);
        listCharacters.add(cocoa);
        listCharacters.add(turco);
    }

    private static void CaesarCrew()
    {
        Characters barbebrune = new Characters("Barbe Brune", false, "80.6 Mi", 581, "Pirate", true, 45, "Caesar's Crew", 1);
        Characters caesar = new Characters("Caesar Clown", true, "300 Mi", 658, "Pirate", true, 55, "Caesar's Crew", 0);
        Characters rock = new Characters("Rock", false, "20 Mi", 665, "Pirate", true, 25, "Caesar's Crew", 1);
        Characters scotch = new Characters("Scotch", false, "20 Mi", 665, "Pirate", true, 25, "Caesar's Crew", 1);
        Characters smiley = new Characters("Smiley", true, "0", 668, "Citizen", false, 4, "Caesar's Crew", 1);

        listCharacters.add(barbebrune);
        listCharacters.add(caesar);
        listCharacters.add(rock);
        listCharacters.add(scotch);
        listCharacters.add(smiley);
    }

    private static void BaroqueWorks()
    {
        Characters zala = new Characters("Zala / Miss Doublefinger", true, "35 Mi", 155, "Pirate", true, 28, "Baroque Works", 0);
        Characters bonclay = new Characters("Bentham / Mr. 2 Bon Clay", true, "32 Mi", 129, "Pirate", true, 32, "Baroque Works", 0);
        Characters marianne = new Characters("Marianne / Miss Goldenweek", false, "29 Mi", 117, "Pirate", true, 18, "Baroque Works", 0);
        Characters babe = new Characters("Babe / Mr. 4", false, "3.2 Mi", 103, "Pirate", true, 30, "Baroque Works", 0);
        Characters drophy = new Characters("Drophy / Miss Merrychristmas", true, "14 Mi", 160, "Pirate", true, 51, "Baroque Works", 0);
        Characters gemme = new Characters("Gemme / Mr. 5", true, "10 Mi", 110, "Pirate", true, 26, "Baroque Works", 0);
        Characters mikita = new Characters("Mikita / Miss Valentine", true, "7.5 Mi", 110, "Pirate", true, 24, "Baroque Works", 0);

        listCharacters.add(zala);
        listCharacters.add(bonclay);
        listCharacters.add(marianne);
        listCharacters.add(babe);
        listCharacters.add(drophy);
        listCharacters.add(gemme);
        listCharacters.add(mikita);
    }

    private static void Solos()
    {
        Characters dadan = new Characters("Curly Dadan", false, "7.8 Mi", 568, "Pirate", true, 55, "Dadan's Crew", 0);
        Characters higuma = new Characters("Higuma", false, "8 Mi", 1, "Pirate", false, 46, "Moutain's Bandits", 0);
        Characters zeff = new Characters("Zeff", false, "Unknown", 43, "Pirate", true, 67, "Zeff's Crew", 0);
        Characters chinjao = new Characters("Chinjao", false, "542 Mi", 704, "Pirate", true, 78, "8 Treasures's Navy", 0);
        Characters dorry = new Characters("Dorry", false, "100 Mi", 116, "Pirate", true, 160, "Giants' Crew", 0);
        Characters broggy = new Characters("Broggy", false, "100 Mi", 115, "Pirate", true, 160, "Giants' Crew", 0);
        Characters pedro = new Characters("Pedro", false, "382 Mi", 805, "Pirate", false, 32, "Nox's Crew", 0);
        Characters kelly = new Characters("Kelly Funk", true, "57 Mi", 704, "Pirate", true, 36, "Assassin's Mogalo", 1);
        Characters bobby = new Characters("Bobby Funk", false, "36 Mi", 704, "Pirate", true, 33, "Assassin's Mogalo", 1);

        listCharacters.add(dadan);
        listCharacters.add(higuma);
        listCharacters.add(zeff);
        listCharacters.add(chinjao);
        listCharacters.add(dorry);
        listCharacters.add(broggy);
        listCharacters.add(pedro);
        listCharacters.add(kelly);
        listCharacters.add(bobby);

    }
}
