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
import fr.gmarquette.guesswho.GameSystem.CrewType;
import fr.gmarquette.guesswho.GameSystem.TypeType;

public class RevolutionaryArmy {


    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getRevolutionary()
    {
        Revolutionary();

        return listCharacters;
    }

    private static void Revolutionary()
    {
        Characters sabo = new Characters("Sabo", true, "602000000", 583, TypeType.REVOLUTIONARY, true, 22, CrewType.REVOLUTIONARY_ARMY, 0);

        listCharacters.add(sabo);
    }
}
