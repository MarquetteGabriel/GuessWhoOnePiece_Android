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
        Characters sabo = new Characters("Sabo", true, "602000000", 583, "Revolutionary", true, 22, "Revolutionary's Crew", 0);

        listCharacters.add(sabo);
    }
}
