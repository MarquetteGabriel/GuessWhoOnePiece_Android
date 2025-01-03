// <copyright file="ManageCsv.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the get of the Csv.</summary>
    public static class ReceiveDataCsv
    {
        /// <summary>Receive the character from the character name.</summary>
        /// <param name="characterName">The character's name to get data.</param>
        /// <returns>The character to get.</returns>
        public static async Task<Character> ReceiveCharacter(string characterName)
        {
            Character? character = null;
            await using var stream = File.OpenRead(ManageCsv.CsvPath);
            using var reader = new StreamReader(stream);
            while (await reader.ReadLineAsync() is { } line)
            {
                var values = line.Split(ManageCsv.Separator);

                if (values.Length == 10 && values[0].Equals(characterName))
                {
                    character = new Character(
                        values[0], bool.Parse(values[1]),
                        values[2], int.Parse(values[3]), 
                        values[4], bool.Parse(values[5]),
                        int.Parse(values[6]), values[7], values[8],
                        int.Parse(values[9]));
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
            using var reader = new StreamReader(stream);
            while (await reader.ReadLineAsync() is { } line)
            {
                var values = line.Split(ManageCsv.Separator);

                if (values.Length == 11)
                {
                    characters.Add(new Character(
                        values[0], bool.Parse(values[1]),
                        values[2], int.Parse(values[3]),
                        values[4], bool.Parse(values[5]),
                        int.Parse(values[6]), values[7], values[8],
                        int.Parse(values[9])));
                }
            }

            return characters;
        }
    }
}

