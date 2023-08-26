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

public class Citizens
{
    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getCitizens()
    {
        EastBlue();
        Drum();
        Alabasta();
        Skypea();
        EnniesLobby();
        Ryukugyu();
        Dressrossa();
        Zo();
        Germa();
        WanoKuni();
        Elbaf();
        Others();

        return listCharacters;
    }

    private static void EastBlue()
    {
        Characters stelly = new Characters("Stelly", false, "0", 586, "Citizen", true, 20, "Citizen", 1);
        Characters makino = new Characters("Makino", false, "0", 1, "Citizen", true, 31, "Citizen", 0);
        Characters koshiro = new Characters("Koshiro Shimotsuki", false, "0", 5, "Citizen", true, 51, "Citizen", 0);
        Characters kuina = new Characters("Kuina Shimotsuki", false, "0", 5, "Citizen", false, 11, "Citizen", 0);
        Characters boodle = new Characters("Boodle", false, "0", 12, "Citizen", true, 75, "Citizen", 1);
        Characters chouchou = new Characters("Chouchou", false, "0", 12, "Citizen", true, 14, "Citizen", 1);
        Characters gaimon = new Characters("Gaimon", false, "0", 22, "Citizen", true, 45, "Citizen", 0);
        Characters kaya = new Characters("Kaya", false, "0", 23, "Citizen", true, 19, "Citizen", 0);
        Characters oignon = new Characters("Oignon", false, "0", 23, "Citizen", true, 11, "Citizen", 0);
        Characters piment = new Characters("Piment", false, "0", 23, "Citizen", true, 11, "Citizen", 0);
        Characters carotte = new Characters("Carotte", false, "0", 23, "Citizen", true, 11, "Citizen", 0);
        Characters patty = new Characters("Patty", false, "0", 44, "Citizen", true, 29, "Citizen", 0);
        Characters carne = new Characters("Carne", false, "0", 45, "Citizen", true, 34, "Citizen", 0);
        Characters belmer = new Characters("Belmer", false, "0", 77, "Citizen", false, 30, "Citizen", 0);
        Characters nojiko = new Characters("Nojiko", false, "0", 70, "Citizen", true, 22, "Citizen", 0);
        Characters genzo = new Characters("Genzo", false, "0", 71, "Citizen", true, 48, "Citizen", 0);
        Characters johnny = new Characters("Johnny", false, "0", 42, "Citizen", true, 25, "Citizen", 0);
        Characters yosaku = new Characters("Yosaku", false, "0", 42, "Citizen", true, 26, "Citizen", 0);
        Characters ipponmatsu = new Characters("Ipponmatsu", false, "0", 97, "Citizen", true, 40, "Citizen", 1);

        listCharacters.add(stelly);
        listCharacters.add(makino);
        listCharacters.add(koshiro);
        listCharacters.add(kuina);
        listCharacters.add(boodle);
        listCharacters.add(chouchou);
        listCharacters.add(gaimon);
        listCharacters.add(kaya);
        listCharacters.add(oignon);
        listCharacters.add(piment);
        listCharacters.add(carotte);
        listCharacters.add(patty);
        listCharacters.add(carne);
        listCharacters.add(belmer);
        listCharacters.add(nojiko);
        listCharacters.add(genzo);
        listCharacters.add(johnny);
        listCharacters.add(yosaku);
        listCharacters.add(ipponmatsu);
    }

    private static void Drum()
    {
        Characters wapol = new Characters("Wapol", true, "0", 131, "Citizen", true, 29, "Citizen", 0);
        Characters dolton = new Characters("Dolton", true, "0", 132, "Citizen", true, 33, "Citizen", 0);
        Characters kureha = new Characters("Kureha", false, "0", 134, "Citizen", true, 141, "Citizen", 0);
        Characters hiluluk = new Characters("Hiluluk", false, "0", 141, "Citizen", false, 68, "Citizen", 0);
        Characters chess = new Characters("Chess", false, "0", 131, "Citizen", true, 32, "Citizen", 1);
        Characters kuromarimo = new Characters("Kuromarimo", false, "0", 131, "Citizen", true, 30, "Citizen", 1);

        listCharacters.add(wapol);
        listCharacters.add(dolton);
        listCharacters.add(kureha);
        listCharacters.add(hiluluk);
        listCharacters.add(chess);
        listCharacters.add(kuromarimo);
    }

    private static void Alabasta()
    {
        Characters cobra = new Characters("Nefertari Cobra", false, "0", 142, "Citizen", false, 50, "Citizen", 0);
        Characters vivi = new Characters("Nefertari Vivi", false, "0", 103, "Citizen", true, 18, "Citizen", 0);
        Characters igaram = new Characters("Igaram", false, "0", 106, "Citizen", true, 50, "Citizen", 0);
        Characters chaka = new Characters("Chaka", true, "0", 155, "Citizen", true, 41, "Citizen", 0);
        Characters pell = new Characters("Pell", true, "0", 155, "Citizen", true, 35, "Citizen", 0);
        Characters kaloo = new Characters("Kaloo", false, "0", 109, "Citizen", true, 16, "Citizen", 0);
        Characters longs = new Characters("Longs-cils", false, "0", 162, "Citizen", true, 17, "Citizen", 1);
        Characters koza = new Characters("Koza", false, "0", 163, "Citizen", true, 22, "Citizen", 0);

        listCharacters.add(cobra);
        listCharacters.add(vivi);
        listCharacters.add(igaram);
        listCharacters.add(chaka);
        listCharacters.add(pell);
        listCharacters.add(kaloo);
        listCharacters.add(longs);
        listCharacters.add(koza);
    }

    private static void Skypea()
    {
        Characters gan = new Characters("Gan Forr", false, "0", 237, "Citizen", true, 68, "Citizen", 0);
        Characters pierre = new Characters("Pierre", true, "0", 237, "Citizen", true, 21, "Citizen", 1);
        Characters conis = new Characters("Conis", false, "0", 239, "Citizen", true, 21, "Citizen", 0);
        Characters pagaya = new Characters("Pagaya", false, "0", 239, "Citizen", true, 54, "Citizen", 1);
        Characters amazon = new Characters("Amazon", false, "0", 238, "Citizen", true, 79, "Citizen", 1);
        Characters chef = new Characters("Chef Shandia", false, "0", 275, "Citizen", true, 65, "Citizen", 1);
        Characters wiper = new Characters("Wiper", false, "0", 237, "Citizen", true, 24, "Citizen", 0);
        Characters kamakiri = new Characters("Kamakiri", false, "0", 249, "Citizen", true, 24, "Citizen", 0);
        Characters braham = new Characters("Braham", false, "0", 249, "Citizen", true, 24, "Citizen", 0);
        Characters genbo = new Characters("Genbo", false, "0", 249, "Citizen", true, 25, "Citizen", 0);
        Characters laki = new Characters("Laki", false, "0", 249, "Citizen", true, 22, "Citizen", 0);
        Characters aisa = new Characters("Aisa", false, "0", 249, "Citizen", true, 11, "Citizen", 0);
        Characters nora = new Characters("Nora", false, "0", 255, "Citizen", true, 400, "Citizen", 1);
        Characters calgara = new Characters("Calgara", false, "0", 286, "Citizen", false, 39, "Citizen", 0);
        Characters montblanc = new Characters("Montblanc Norland", false, "0", 286, "Citizen", false, 39, "Citizen", 0);

        listCharacters.add(gan);
        listCharacters.add(pierre);
        listCharacters.add(conis);
        listCharacters.add(pagaya);
        listCharacters.add(amazon);
        listCharacters.add(chef);
        listCharacters.add(wiper);
        listCharacters.add(kamakiri);
        listCharacters.add(braham);
        listCharacters.add(genbo);
        listCharacters.add(laki);
        listCharacters.add(aisa);
        listCharacters.add(nora);
        listCharacters.add(calgara);
        listCharacters.add(montblanc);
    }

    private static void EnniesLobby()
    {
        Characters clover = new Characters("Clover", false, "0", 391, "Citizen", false, 85, "Citizen", 0);
        Characters olvia = new Characters("Nico Olvia", false, "0", 392, "Citizen", false, 33, "Citizen", 0);
        Characters icebarg = new Characters("Icebarg", false, "0", 323, "Citizen", true, 40, "Citizen", 0);
        Characters pauly = new Characters("Pauly", false, "0", 323, "Citizen", true, 26, "Citizen", 0);
        Characters peeply = new Characters("Peeply Lulu", false, "0", 323, "Citizen", true, 33, "Citizen", 0);
        Characters tilestone = new Characters("Tilestone", false, "0", 323, "Citizen", true, 35, "Citizen", 0);
        Characters zanbai = new Characters("Zanbai", false, "0", 324, "Citizen", true, 35, "Citizen", 1);
        Characters mozu = new Characters("Mozu", false, "0", 329, "Citizen", true, 21, "Citizen", 1);
        Characters kiwi = new Characters("Kiwi", false, "0", 329, "Citizen", true, 22, "Citizen", 1);
        Characters tom = new Characters("Tom", false, "0", 352, "Citizen", false, 67, "Citizen", 1);
        Characters kokoro = new Characters("Kokoro", false, "0", 322, "Citizen", true, 72, "Citizen", 0);
        Characters chimney = new Characters("Chimney", false, "0", 322, "Citizen", true, 10, "Citizen", 1);


        listCharacters.add(clover);
        listCharacters.add(olvia);
        listCharacters.add(icebarg);
        listCharacters.add(pauly);
        listCharacters.add(peeply);
        listCharacters.add(tilestone);
        listCharacters.add(zanbai);
        listCharacters.add(mozu);
        listCharacters.add(kiwi);
        listCharacters.add(tom);
        listCharacters.add(kokoro);
        listCharacters.add(chimney);
    }

    private static void Ryukugyu()
    {
        Characters neptune = new Characters("Neptune", false, "0", 611, "Citizen", true, 70, "Citizen", 0);
        Characters otohime = new Characters("Otohime", false, "0", 621, "Citizen", false, 36, "Citizen", 0);
        Characters fukaboshi = new Characters("Fukaboshi", false, "0", 609, "Citizen", true, 24, "Citizen", 0);
        Characters ryuboshi = new Characters("Ryuboshi", false, "0", 609, "Citizen", true, 23, "Citizen", 0);
        Characters mamboshi = new Characters("Mamboshi", false, "0", 609, "Citizen", true, 20, "Citizen", 0);
        Characters shirahoshi = new Characters("Shirahoshi", false, "0", 612, "Citizen", true, 16, "Citizen", 0);
        Characters senestre = new Characters("Ministre sénestre", false, "0", 612, "Citizen", true, 62, "Citizen", 1);
        Characters dextre = new Characters("Ministre dextre", false, "0", 612, "Citizen", true, 55, "Citizen", 1);
        Characters shyarly = new Characters("Shyarly", false, "0", 610, "Citizen", true, 29, "Citizen", 1);
        Characters keimi = new Characters("Keimi", false, "0", 195, "Citizen", true, 18, "Citizen", 0);
        Characters pappag = new Characters("Pappag", false, "0", 195, "Citizen", true, 33, "Citizen", 0);
        Characters octy = new Characters("Octy", false, "8 Mi", 69, "Pirate", true, 38, "Arlong's Crew", 0);
        Characters den = new Characters("Den", false, "0", 616, "Citizen", true, 62, "Citizen", 1);


        listCharacters.add(neptune);
        listCharacters.add(otohime);
        listCharacters.add(fukaboshi);
        listCharacters.add(ryuboshi);
        listCharacters.add(mamboshi);
        listCharacters.add(shirahoshi);
        listCharacters.add(senestre);
        listCharacters.add(dextre);
        listCharacters.add(shyarly);
        listCharacters.add(keimi);
        listCharacters.add(pappag);
        listCharacters.add(octy);
        listCharacters.add(den);
    }

    private static void Dressrossa()
    {
        Characters riku = new Characters("Riku Dold III", false, "0", 706, "Citizen", true, 60, "Citizen", 0);
        Characters scarlett = new Characters("Scarlett", false, "0", 721, "Citizen", false, 25, "Citizen", 1);
        Characters kyros = new Characters("Kyros", false, "0", 702, "Citizen", true, 44, "Citizen", 0);
        Characters viola = new Characters("Viola", true, "0", 703, "Citizen", true, 29, "Citizen", 0);
        Characters rebecca = new Characters("Rebecca", false, "0", 704, "Citizen", true, 16, "Citizen", 0);
        Characters manshelly = new Characters("Manshelly", true, "0", 717, "Citizen", true, 25, "Citizen", 0);

        listCharacters.add(riku);
        listCharacters.add(scarlett);
        listCharacters.add(kyros);
        listCharacters.add(viola);
        listCharacters.add(rebecca);
        listCharacters.add(manshelly);
    }

    private static void Zo()
    {
        Characters zunesh = new Characters("Zunesh", false, "0", 802, "Citizen", true, 1000, "Citizen", 0);
        Characters carrot = new Characters("Carrot", false, "0", 804, "Citizen", true, 15, "Citizen", 0);
        Characters sicilion = new Characters("Sicilion", false, "0", 808, "Citizen", true, 34, "Citizen", 0);
        Characters concelot = new Characters("Concelot", false, "0", 809, "Citizen", true, 29, "Citizen", 1);
        Characters giovanni = new Characters("Giovanni", false, "0", 809, "Citizen", true, 30, "Citizen", 1);
        Characters wanda = new Characters("Wanda", false, "0", 804, "Citizen", true, 28, "Citizen", 0);

        listCharacters.add(zunesh);
        listCharacters.add(carrot);
        listCharacters.add(sicilion);
        listCharacters.add(concelot);
        listCharacters.add(giovanni);
        listCharacters.add(wanda);

    }

    private static void Germa()
    {
        Characters judge = new Characters("Judge Vinsmoke", false, "0", 832, "Citizen", true, 56, "Citizen", 0);
        Characters reiju = new Characters("Reiju Vinsmoke", false, "0", 826, "Citizen", true, 24, "Citizen", 0);
        Characters ichiji = new Characters("Ichiji Vinsmoke", false, "0", 828, "Citizen", true, 21, "Citizen", 0);
        Characters niji = new Characters("Niji Vinsmoke", false, "0", 828, "Citizen", true, 21, "Citizen", 0);
        Characters yonji = new Characters("Yonji Vinsmoke", false, "0", 825, "Citizen", true, 21, "Citizen", 0);

        listCharacters.add(judge);
        listCharacters.add(reiju);
        listCharacters.add(ichiji);
        listCharacters.add(niji);
        listCharacters.add(yonji);
    }

    private static void WanoKuni()
    {
        Characters sukiyaki = new Characters("Sukiyaki Kozuki", false, "0", 911, "Citizen", true, 81, "Citizen", 1);
        Characters toki = new Characters("Toki Kozuki", true, "0", 919, "Citizen", false, 36, "Citizen", 0);
        Characters momonosuke = new Characters("Momonosuké Kozuki", true, "0", 609, "Citizen", true, 8, "Citizen", 0);
        Characters hiyori = new Characters("Hiyori Kozuki", false, "0", 909, "Citizen", true, 26, "Citizen", 0);
        Characters kinemon = new Characters("Kinémon", true, "0", 656, "Citizen", true, 36, "Citizen", 0);
        Characters denjiro = new Characters("Denjiro", false, "0", 919, "Citizen", true, 47, "Citizen", 0);
        Characters kikunojo = new Characters("Kikunojo", false, "0", 913, "Citizen", true, 22, "Citizen", 0);
        Characters raizo = new Characters("Raizo", true, "0", 817, "Citizen", true, 35, "Citizen", 0);
        Characters ashura = new Characters("Ashura Doji", false, "0", 920, "Citizen", false, 56, "Citizen", 0);
        Characters caborage = new Characters("Caborage / Inuarashi", false, "0", 808, "Citizen", true, 40, "Citizen", 0);
        Characters chavipere = new Characters("Chavipère / Nekomamushi", false, "0", 809, "Citizen", true, 40, "Citizen", 0);
        Characters kawamatsu = new Characters("Kawamatsu", false, "0", 910, "Citizen", true, 41, "Citizen", 0);
        Characters shinobu = new Characters("Shinobu", true, "0", 921, "Citizen", true, 49, "Citizen", 0);
        Characters tama = new Characters("Tama Kurozumi", true, "0", 911, "Citizen", true, 8, "Citizen", 0);
        Characters orochi = new Characters("Orochi Kurozumi", true, "0", 927, "Citizen", false, 54, "Citizen", 0);
        Characters kanjuro = new Characters("Kanjuro Kurozumi", true, "0", 700, "Citizen", false, 34, "Citizen", 0);
        Characters ryuma = new Characters("Ryuma Shimotsuki", false, "0", 448, "Citizen", false, 47, "Citizen", 0);
        Characters yasuie = new Characters("Yasuie Shimotsuki", false, "0", 929, "Citizen", false, 71, "Citizen", 0);
        Characters toko = new Characters("Toko", false, "0", 927, "Citizen", true, 8, "Citizen", 1);
        Characters onimaru = new Characters("Onimaru", true, "0", 936, "Citizen", true, 69, "Citizen", 1);
        Characters hyogoro = new Characters("Hyogoro", false, "0", 926, "Citizen", true, 70, "Citizen", 0);
        Characters tsurujo = new Characters("Tsurujo", false, "0", 912, "Citizen", true, 55, "Citizen", 1);
        Characters yamato = new Characters("Yamato", true, "0", 971, "Citizen", true, 28, "Citizen", 0);

        listCharacters.add(sukiyaki);
        listCharacters.add(momonosuke);
        listCharacters.add(toki);
        listCharacters.add(hiyori);
        listCharacters.add(kinemon);
        listCharacters.add(denjiro);
        listCharacters.add(kikunojo);
        listCharacters.add(raizo);
        listCharacters.add(ashura);
        listCharacters.add(caborage);
        listCharacters.add(chavipere);
        listCharacters.add(kawamatsu);
        listCharacters.add(shinobu);
        listCharacters.add(tama);
        listCharacters.add(orochi);
        listCharacters.add(kanjuro);
        listCharacters.add(ryuma);
        listCharacters.add(yasuie);
        listCharacters.add(toko);
        listCharacters.add(onimaru);
        listCharacters.add(hyogoro);
        listCharacters.add(tsurujo);
        listCharacters.add(yamato);
    }

    private static void Elbaf()
    {
        Characters loki = new Characters("Loki", false, "0", 858, "Citizen", true, 63, "Citizen", 1);
        Characters jorl = new Characters("Jorl", false, "0", 866, "Citizen", false, 344, "Citizen", 1);
        Characters jarl = new Characters("Jarl", false, "0", 866, "Citizen", true, 408, "Citizen", 1);
        Characters oimo = new Characters("Oimo", false, "0", 377, "Citizen", true, 153, "Citizen", 1);
        Characters kaashii = new Characters("Kaashii", false, "0", 377, "Citizen", true, 156, "Citizen", 1);
        Characters haguar = new Characters("Haguar D. Sauro", false, "0", 275, "Citizen", true, 127, "Citizen", 1);

        listCharacters.add(loki);
        listCharacters.add(jorl);
        listCharacters.add(jarl);
        listCharacters.add(oimo);
        listCharacters.add(kaashii);
        listCharacters.add(haguar);
    }

    private static void Others()
    {
        Characters laboon = new Characters("Laboon", false, "0", 102, "Citizen", true, 53, "Citizen", 0);
        Characters tonjit = new Characters("Tonjit", false, "0", 304, "Citizen", true, 63, "Citizen", 1);
        Characters shakuyaku = new Characters("Shakuyaku", false, "0", 498, "Citizen", true, 64, "Citizen", 1);
        Characters duval = new Characters("Duval", false, "0", 491, "Citizen", true, 25, "Citizen", 0);
        Characters heracles = new Characters("Héraclès", false, "0", 524, "Citizen", true, 51, "Citizen", 1);
        Characters elizabello = new Characters("Elizabello II", false, "0", 704, "Citizen", true, 57, "Citizen", 0);
        Characters morgans = new Characters("Morgans", true, "0", 860, "Citizen", true, 53, "Citizen", 0);
        Characters haredas = new Characters("Haredas", false, "0", 523, "Pirate", true, 97, "Citizen", 1);

        listCharacters.add(laboon);
        listCharacters.add(tonjit);
        listCharacters.add(shakuyaku);
        listCharacters.add(duval);
        listCharacters.add(heracles);
        listCharacters.add(elizabello);
        listCharacters.add(morgans);
        listCharacters.add(haredas);
    }

}
