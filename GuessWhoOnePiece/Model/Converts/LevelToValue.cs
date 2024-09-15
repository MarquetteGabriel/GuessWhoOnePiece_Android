// <copyright file="LevelToValue.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

namespace GuessWhoOnePiece.Model.Converts
{
    /// <summary>Represents the conversion from a Level difficulty to an int value.</summary>
    public static class LevelToValue
    {
        /// <summary>Represents the conversion from a Level difficulty to an int value.</summary>
        /// <param name="value">The value to convert.</param>
        /// <returns>The level of difficulty.</returns>
        public static LevelDifficulty GetLevelDifficultyByValue(int value)
        {
            /*
            
            foreach (LevelDifficulty levelDifficulty in values())
            {
                if (levelDifficulty.ordinal() == value)
                {
                    return levelDifficulty;
                }
            }
            
            */
            return LevelDifficulty.Error;
        }
    }
}

