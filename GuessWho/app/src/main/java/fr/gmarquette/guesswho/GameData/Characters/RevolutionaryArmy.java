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

public class RevolutionaryArmy {


    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getRevolutionary()
    {
        Revolutionary();

        return listCharacters;
    }

    private static void Revolutionary()
    {
        Characters dragon = new Characters("Monkey D Dragon", false, "Unknown", 100, "Revolutionary", true, 55, "Revolutionary's Crew", 0);
        Characters sabo = new Characters("Sabo", true, "602 Mi", 583, "Revolutionary", true, 22, "Revolutionary's Crew", 0);
        Characters ivankov = new Characters("Emporio Ivankov", true, "100 Mi", 537, "Revolutionary", true, 53, "Revolutionary's Crew", 0);
        Characters karasu = new Characters("Karasu / Corbeau", true, "400 Mi", 593, "Revolutionary", true, 47, "Revolutionary's Crew", 1);
        Characters morley = new Characters("Morley", true, "293 Mi", 904, "Revolutionary", true, 160, "Revolutionary's Crew", 1);
        Characters betty = new Characters("Bello Betty", true, "457 Mi", 904, "Revolutionary", true, 34, "Revolutionary's Crew", 1);
        Characters lindbergh = new Characters("Lindbergh", false, "316 Mi", 904, "Revolutionary", true, 37, "Revolutionary's Crew", 1);
        Characters inazuma = new Characters("Inazuma", true, "100 Mi", 593, "Revolutionary", true, 29, "Revolutionary's Crew", 0);
        Characters kuma = new Characters("Bartholomew Kuma", true, "296 Mi", 233, "Revolutionary", true, 47, "Revolutionary's Crew", 0);
        Characters koala = new Characters("Koala", false, "Unknown", 622, "Revolutionary", true, 22, "Revolutionary's Crew", 1);
        Characters hack = new Characters("Hack", false, "Unknown", 706, "Revolutionary", true, 38, "Revolutionary's Crew", 1);

        listCharacters.add(dragon);
        listCharacters.add(sabo);
        listCharacters.add(ivankov);
        listCharacters.add(karasu);
        listCharacters.add(morley);
        listCharacters.add(betty);
        listCharacters.add(lindbergh);
        listCharacters.add(inazuma);
        listCharacters.add(kuma);
        listCharacters.add(koala);
        listCharacters.add(hack);

    }
}
