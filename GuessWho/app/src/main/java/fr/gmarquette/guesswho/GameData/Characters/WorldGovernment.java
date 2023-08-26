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

class WorldGovernment
{
    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getWorldGovernment()
    {
        Navy();
        CelestialDragons();
        WorldGovernments();
        CipherPol();
        Vegapunks();
        ImpelDown();

        return listCharacters;
    }

    private static void Navy()
    {
        Characters kizaru = new Characters("Borsalino / Kizaru", true, "0", 397, "Navy", true, 55, "Navy's Crew", 0);
        Characters sakazuki = new Characters("Sakazuki / Akainu", true, "0", 397, "Navy", true, 55, "Navy's Crew", 0);
        Characters issho = new Characters("Issho / Fujitora", true, "0", 701, "Navy", true, 54, "Navy's Crew", 0);
        //Characters aramaki = new Characters("Aramaki / Ryokugyu", true, "0", 905, "Navy", true, , "Navy's Crew", 0);
        Characters garp = new Characters("Monkey D. Garp", false, "0", 92, "Navy", true, 78, "Navy's Crew", 0);
        Characters tsuru = new Characters("Tsuru", true, "0", 234, "Navy", true, 76, "Navy's Crew", 0);
        //Characters johngiant = new Characters("John Giant", false, "0", 96, "Navy", true, , "Navy's Crew", 1);
        Characters momonga = new Characters("Momonga", false, "0", 420, "Navy", true, 48, "Navy's Crew", 1);
        //Characters onigumo = new Characters("Onigumo", true, "0", 420, "Navy", true, , "Navy's Crew", 1);
        //Characters doberman = new Characters("Doberman", false, "0", 420, "Navy", true, , "Navy's Crew", 1);
        //Characters strawberry = new Characters("Strawberry", false, "0", 420, "Navy", true, , "Navy's Crew", 1);
        //Characters yamakaji = new Characters("Yamakaji", false, "0", 533, "Navy", true, , "Navy's Crew", 1);
        //Characters dalmatien = new Characters("Dalmatien", false, "0", 553, "Navy", true, , "Navy's Crew", 1);
        Characters bastille = new Characters("Bastille", false, "0", 553, "Navy", true, 38, "Navy's Crew", 1);
        Characters smoker = new Characters("Smoker", true, "0", 97, "Navy", true, 36, "Navy's Crew", 0);
        //Characters maynard = new Characters("Maynard", false, "0", 705, "Navy", true, , "Navy's Crew", 1);
        //Characters gion = new Characters("Gion", false, "0", 907, "Navy", true, , "Navy's Crew", 1);
        //Characters tokikake = new Characters("Tokikake", false, "0", 907, "Navy", true, , "Navy's Crew", 1);
        //Characters doll = new Characters("Doll", false, "0", 1061, "Navy", true, , "Navy's Crew", 1);
        Characters tbone = new Characters("T. Bone", false, "0", 365, "Navy", false, 53, "Navy's Crew", 1);
        Characters hina = new Characters("Hina", true, "0", 171, "Navy", true, 34, "Navy's Crew", 0);
        //Characters gles = new Characters("Prince Gles", true, "0", 966, "Navy", true, , "Navy's Crew", 1);
        //Characters kujaku = new Characters("Kujaku", true, "0", 966, "Navy", true, , "Navy's Crew", 1);
        //Characters pudding = new Characters("Pudding", false, "0", 75, "Navy", false, , "Navy's Crew", 1);
        //Characters jenfétro = new Characters("Jenfétro", false, "0", 673, "Navy", true, , "Navy's Crew", 1);
        Characters brandnew = new Characters("Brandnew", false, "0", 96, "Navy", true, 56, "Navy's Crew", 0);
        //Characters verygood = new Characters("Verygood", true, "0", 426, "Navy", true, , "Navy's Crew", 1);
        //Characters shu = new Characters("Shu", true, "0", 426, "Navy", true, , "Navy's Crew", 1);
        //Characters sharinguru = new Characters("Sharinguru", true, "0", 428, "Navy", true, , "Navy's Crew", 1);
        Characters tashigi = new Characters("Tashigi", false, "0", 96, "Navy", true, 23, "Navy's Crew", 0);
        Characters kobby = new Characters("Kobby", false, "0", 2, "Navy", true, 18, "Navy's Crew", 0);
        Characters nezumi = new Characters("Nezumi", false, "0", 69, "Navy", true, 36, "Navy's Crew", 1);
        //Characters hibari = new Characters("Hibari", false, "0", 1061, "Navy", true, , "Navy's Crew", 1);
        Characters rosinante = new Characters("Don Quijote Rosinante", true, "0", 761, "Navy", false, 26, "Navy's Crew", 0);
        Characters hermep = new Characters("Hermep", false, "0", 3, "Navy", true, 22, "Navy's Crew", 0);
        Characters fullbody = new Characters("Fullbody", false, "0", 43, "Navy", true, 28, "Navy's Crew", 1);
        Characters jango = new Characters("Jango", false, "0", 25, "Navy", true, 29, "Navy's Crew", 0);
        Characters sengoku = new Characters("Sengoku", true, "0", 234, "Navy", true, 79, "Navy's Crew", 0);
        Characters sentomaru = new Characters("Sentomaru", false, "0", 497, "Navy", true, 34, "Navy's Crew", 0);
        //Characters tensei = new Characters("Tensei", false, "0", 1054, "Navy", true, , "Navy's Crew", 1);
        Characters drake = new Characters("X-Drake", true, "0", 498, "Navy", true, 33, "Navy's Crew", 0);
        Characters morgan = new Characters("Morgan", false, "0", 4, "Navy", true, 44, "Navy's Crew", 1);


        listCharacters.add(kizaru);
        listCharacters.add(sakazuki);
        listCharacters.add(issho);
        listCharacters.add(garp);
        listCharacters.add(tsuru);
        listCharacters.add(momonga);
        listCharacters.add(bastille);
        listCharacters.add(smoker);
        listCharacters.add(tbone);
        listCharacters.add(hina);
        listCharacters.add(brandnew);
        listCharacters.add(tashigi);
        listCharacters.add(kobby);
        listCharacters.add(nezumi);
        listCharacters.add(rosinante);
        listCharacters.add(hermep);
        listCharacters.add(fullbody);
        listCharacters.add(jango);
        listCharacters.add(sengoku);
        listCharacters.add(sentomaru);
        listCharacters.add(drake);
        listCharacters.add(morgan);

    }

    private static void WorldGovernments()
    {

    }
    private static void CelestialDragons()
    {
        Characters roswald = new Characters("Roswald", false, "0", 497, "Navy", true, 55, "Celestial Dragons", 1);
        Characters charloss = new Characters("Charloss", false, "0", 499, "Navy", true, 24, "Celestial Dragons", 0);

        listCharacters.add(roswald);
        listCharacters.add(charloss);
    }


    private static void CipherPol()
    {
        //Characters guernica = new Characters("Guernica", false, "0", 705, "Navy", false, , "Cipher Pol", 0);
        //Characters joseph = new Characters("Joseph", false, "0", 705, "Navy", true, , "Cipher Pol", 1);
        //Characters gismonda = new Characters("Gismonda", false, "0", 705, "Navy", true, , "Cipher Pol", 1);
        Characters lucci = new Characters("Rob Lucci", true, "0", 323, "Navy", true, 30, "Cipher Pol", 0);
        Characters kaku = new Characters("Kaku", true, "0", 323, "Navy", true, 25, "Cipher Pol", 0);
        //Characters stussy = new Characters("Stussy", false, "0", 860, "Navy", true, , "Cipher Pol", 0);
        //Characters maha = new Characters("Maha", false, "0", 929, "Navy", false, , "Cipher Pol", 1);
        Characters spandam = new Characters("Spandam", false, "0", 355, "Navy", true, 41, "Cipher Pol", 0);
        Characters blueno = new Characters("Blueno", true, "0", 325, "Navy", true, 32, "Cipher Pol", 0);
        Characters kalifa = new Characters("Kalifa", true, "0", 323, "Navy", true, 27, "Cipher Pol", 0);
        Characters jabura = new Characters("Jabura", true, "0", 375, "Navy", true, 37, "Cipher Pol", 0);
        Characters kumadori = new Characters("Kumadori", false, "0", 375, "Navy", true, 36, "Cipher Pol", 0);
        Characters fukuro = new Characters("Fukuro", false, "0", 375, "Navy", true, 31, "Cipher Pol", 0);
        Characters jerry = new Characters("Jerry", false, "0", 362, "Navy", true, 46, "Cipher Pol", 1);
        Characters wanze = new Characters("Wanzé", false, "0", 367, "Navy", true, 28, "Cipher Pol", 1);
        Characters nero = new Characters("Nero", false, "0", 367, "Navy", true, 22, "Cipher Pol", 1);
        Characters spandine = new Characters("Spandine", false, "0", 275, "Navy", true, 66, "Cipher Pol", 1);

        listCharacters.add(lucci);
        listCharacters.add(kaku);
        listCharacters.add(spandam);
        listCharacters.add(blueno);
        listCharacters.add(kalifa);
        listCharacters.add(jabura);
        listCharacters.add(kumadori);
        listCharacters.add(fukuro);
        listCharacters.add(jerry);
        listCharacters.add(wanze);
        listCharacters.add(nero);
        listCharacters.add(spandine);
    }

    private static void Vegapunks()
    {
        Characters vegapunk = new Characters("Végapunk", true, "0", 1066, "Navy", true, 65, "Vegapunk's Factory", 0);
        Characters shaka = new Characters("Shaka", true, "0", 1062, "Navy", true, 65, "Vegapunk's Factory", 1);
        Characters lilith = new Characters("Lilith", true, "0", 1061, "Navy", true, 65, "Vegapunk's Factory", 1);
        Characters edison = new Characters("Edison", true, "0", 1065, "Navy", true, 65, "Vegapunk's Factory", 1);
        Characters pythagore = new Characters("Pythagore", true, "0", 1065, "Navy", true, 65, "Vegapunk's Factory", 1);
        Characters atlas = new Characters("Atlas", true, "0", 1062, "Navy", true, 65, "Vegapunk's Factory", 1);
        Characters york = new Characters("York", true, "0", 1065, "Navy", true, 65, "Vegapunk's Factory", 1);
        //Characters ssnake = new Characters("S-Snake", true, "0", 1059, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters shawk = new Characters("S-Hawk", true, "0", 1059, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters sbear = new Characters("S-Bear", true, "0", 1062, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters sshark = new Characters("S-Shark", true, "0", 1065, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters sflamingo = new Characters("S-Flamingo", true, "0", 1086, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters sgecko = new Characters("S-Gecko", true, "0", 1086, "Navy", true, , "Vegapunk's Factory", 1);
        //Characters scrocodile = new Characters("S-Crocodile", true, "0", 1086, "Navy", true, , "Vegapunk's Factory", 1);

        listCharacters.add(vegapunk);
        listCharacters.add(shaka);
        listCharacters.add(lilith);
        listCharacters.add(edison);
        listCharacters.add(pythagore);
        listCharacters.add(atlas);
        listCharacters.add(york);
    }

    private static void ImpelDown()
    {
        Characters hannyabal = new Characters("Hannyabal", false, "0", 526, "Navy", true, 35, "Impel Down", 0);
        Characters magellan = new Characters("Magellan", true, "0", 528, "Navy", true, 47, "Impel Down", 0);
        //Characters domino = new Characters("Domino", false, "0", 526, "Navy", true, , "Impel Down", 1);
        Characters saldeath = new Characters("Saldeath", false, "0", 530, "Navy", true, 18, "Impel Down", 1);
        Characters sadi = new Characters("Sadi", false, "0", 531, "Navy", true, 23, "Impel Down", 1);
        //Characters minotaure = new Characters("Minotaure", true, "0", 525, "Navy", true, , "Impel Down", 1);
        //Characters minokoala = new Characters("Minokoala", true, "0", 531, "Navy", true, , "Impel Down", 1);
        //Characters minorhinocéros = new Characters("Minorhinocéros", true, "0", 532, "Navy", true, , "Impel Down", 1);
        //Characters minozebre = new Characters("Minozèbre", true, "0", 533, "Navy", true, , "Impel Down", 1);
        //Characters minochihuahua = new Characters("Minochihuahua", true, "0", 662, "Navy", true, , "Impel Down", 1);

        listCharacters.add(hannyabal);
        listCharacters.add(magellan);
        listCharacters.add(saldeath);
        listCharacters.add(sadi);
    }

}



