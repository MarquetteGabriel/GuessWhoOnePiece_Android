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

public class Citizens
{
    private static final List<Characters> listCharacters = new ArrayList<>();

    static List<Characters> getCitizens()
    {
        EastBlue();

        return listCharacters;
    }

    private static void EastBlue()
    {
        Characters stelly = new Characters("Stelly", false, "0", 586, TypeType.CITIZEN, true, 20, CrewType.CITIZEN, 1);
        Characters makino = new Characters("Makino", false, "0", 1, TypeType.CITIZEN, true, 31, CrewType.CITIZEN, 0);

        listCharacters.add(stelly);
        listCharacters.add(makino);

    }
}
