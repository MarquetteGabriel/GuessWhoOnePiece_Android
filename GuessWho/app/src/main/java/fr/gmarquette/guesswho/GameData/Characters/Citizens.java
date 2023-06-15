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

        return listCharacters;
    }
}
