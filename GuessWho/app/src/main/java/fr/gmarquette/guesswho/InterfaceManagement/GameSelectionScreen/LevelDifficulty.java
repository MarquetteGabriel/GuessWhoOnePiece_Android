/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen;

public enum LevelDifficulty
{
    EASY,
    HARD;

    public static LevelDifficulty getLevelDifficultyByValue(int value)
    {
        for (LevelDifficulty levelDifficulty : values())
        {
            if(levelDifficulty.ordinal() == value)
            {
                return levelDifficulty;
            }
        }
        return null;
    }
}


