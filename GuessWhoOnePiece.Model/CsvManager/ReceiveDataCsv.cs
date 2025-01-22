// <copyright file="ReceiveDataCsv.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using System.Linq;

namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the get of the Csv.</summary>
    public static class ReceiveDataCsv
    {
        /// <summary>Length of a data character in the csv file.</summary>
        private const int DataCharacterLength = 11; 

        // Column of characters
        private const int NameColumn = 0;
        private const int DevilFruitColumn = 1;
        private const int BountyColumn = 2;
        private const int FirstAppearanceColumn = 3;
        private const int TypeColumn = 4;
        private const int AliveColumn = 5;
        private const int AgeColumn = 6;
        private const int CrewColumn = 7;
        private const int PictureColumn = 8;
        private const int LevelColumn = 9;

        /// <summary>Receive the character from the character name.</summary>
        /// <param name="characterName">The character's name to get data.</param>
        /// <returns>The character to get.</returns>
        public static async Task<Character> ReceiveCharacter(string characterName)
        {
            var characters = await ReceiveAllCharacters(values => values[0].Equals(characterName, StringComparison.OrdinalIgnoreCase));
            return characters.FirstOrDefault() ?? throw new Exception("Character not found");
        }

        /// <summary>Receive all characters.</summary>
        /// <returns>List of all characters.</returns>
        public static async Task<List<Character>> ReceiveAllCharacters(Func<string[], bool>? filter = null)
        {
            var characters = new List<Character>();
            await using var stream = File.OpenRead(ManageCsv.CsvPath);
            using var reader = new StreamReader(stream, Encoding.UTF8);
            while (await reader.ReadLineAsync() is { } line)
            {
                var values = line.Split(ManageCsv.Separator);
                if (values.Length == DataCharacterLength && (filter == null || filter(values)))
                {
                    characters.Add(CreateCharacterFromFile(values));
                }
            }

            return characters;
        }

        /// <summary>Create a character from datas in the csv file.</summary>
        /// <param name="characterDatas">Datas from the csv file.</param>
        /// <returns>Character created.</returns>
        private static Character CreateCharacterFromFile(string[] characterDatas)
        {
            return new Character(
                        characterDatas[NameColumn], bool.Parse(characterDatas[DevilFruitColumn]),
                        characterDatas[BountyColumn], int.Parse(characterDatas[FirstAppearanceColumn], CultureInfo.InvariantCulture),
                        characterDatas[TypeColumn], bool.Parse(characterDatas[AliveColumn]),
                        int.Parse(characterDatas[AgeColumn], CultureInfo.InvariantCulture), characterDatas[CrewColumn], characterDatas[PictureColumn],
                        int.Parse(characterDatas[LevelColumn], CultureInfo.InvariantCulture));
        }
    }
}

