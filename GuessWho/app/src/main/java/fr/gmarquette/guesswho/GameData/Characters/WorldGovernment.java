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

        return listCharacters;
    }

    private static void Navy()
    {
        Characters kizaru = new Characters("Borsalino / Kizaru", true, "0", 397, "Navy", true, 55, "Navy's Crew");

        listCharacters.add(kizaru);
    }

}



