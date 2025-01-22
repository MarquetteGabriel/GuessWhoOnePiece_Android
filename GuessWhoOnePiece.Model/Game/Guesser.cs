// <copyright file="Guesser.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using System;
using System.Security.Cryptography;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Model.Game
{
    public static class Guesser
    {
        public static async Task<Character> SetCharacterToFind()
        {
            var listCharacters = await ReceiveDataCsv.ReceiveAllCharacters();
            var index = RandomizeNumber(listCharacters.Count);
            return listCharacters[index];
        }

        private static int RandomizeNumber(int maxNumber)
        {
            return RandomNumberGenerator.GetInt32(maxNumber);
        }
    }
}

