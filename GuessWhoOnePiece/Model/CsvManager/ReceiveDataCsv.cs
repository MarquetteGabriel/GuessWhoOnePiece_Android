// <copyright file="ReceiveDataCsv.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using System.Globalization;
using System.Text;

namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the get of the Csv.</summary>
    public static class ReceiveDataCsv
    {
        /// <summary>Length of a data character in the csv file.</summary>
        private const int DataCharacterLength = 11; 

        /// <summary>Receive the character from the character name.</summary>
        /// <param name="characterName">The character's name to get data.</param>
        /// <returns>The character to get.</returns>
        public static async Task<Character> ReceiveCharacter(string characterName)
        {
            Character? character = null;
            await using var stream = File.OpenRead(ManageCsv.CsvPath);
            using var reader = new StreamReader(stream, Encoding.UTF8);
            while (await reader.ReadLineAsync() is { } line)
            {
                var values = line.Split(ManageCsv.Separator);

                if (values.Length == DataCharacterLength && values[0].Equals(characterName))
                {
                    character = CreateCharacterFromFile(values);
                }
            }

            return character!;
        }

        /// <summary>Receive all characters.</summary>
        /// <returns>List of all characters.</returns>
        public static async Task<List<Character>> ReceiveAllCharacters()
        {
            var characters = new List<Character>();
            await using var stream = File.OpenRead(ManageCsv.CsvPath);
            using var reader = new StreamReader(stream, Encoding.UTF8);
            while (await reader.ReadLineAsync() is { } line)
            {
                var values = line.Split(ManageCsv.Separator);

                if (values.Length == DataCharacterLength)
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
                        characterDatas[0], bool.Parse(characterDatas[1]),
                        characterDatas[2], int.Parse(characterDatas[3], CultureInfo.InvariantCulture),
                        characterDatas[4], bool.Parse(characterDatas[5]),
                        int.Parse(characterDatas[6], CultureInfo.InvariantCulture), characterDatas[7], characterDatas[8],
                        int.Parse(characterDatas[9], CultureInfo.InvariantCulture));
        }
    }
}

