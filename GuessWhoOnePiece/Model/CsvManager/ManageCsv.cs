// <copyright file="ManageCsv.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using System.Text;
using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the managing of the Csv.</summary>
    public static class ManageCsv
    {
        /// <summary>Path of the Csv.</summary>
        internal static readonly string CsvPath = Path.Combine(FileSystem.Current.AppDataDirectory,"Characters.csv");
        
        /// <summary>Separator of the Csv.</summary>
        internal const string Separator = ";";

        /// <summary>Add a character to the Csv.</summary>
        /// <param name="characters">The list of characters to add.</param>
        public static void SaveCharactersToCsv(List<Character> characters)
        {
            CreateCsvFile(CsvPath);

            using var sw = new StreamWriter(CsvPath, false, Encoding.UTF8);
            foreach (var character in characters)
                sw.WriteLine(SetCharacterToCsv(character));
        }
    
        /// <summary>Create a CSV file.</summary>
        /// <param name="filePath">The path where the csv has to be located. </param>
        private static void CreateCsvFile(string filePath)
        {
            if (File.Exists(filePath)) return;
            using var sw = File.CreateText(filePath);
            sw.WriteLine("sep=" + Separator);
        }

        /// <summary>Delete a CSV file.</summary>
        /// <param name="filePath">The path where the csv has to be located. </param>
        private static void DeleteCsvFile(string filePath)
        {
            if(File.Exists(filePath))
                File.Delete(filePath);
        }

        /// <summary>Set the separator and the different column of the csv for a character.</summary>
        /// <param name="character">The character to add to the csv.</param>
        /// <returns>The string formatted.</returns>
        private static string SetCharacterToCsv(Character character)
        {
            var name = character.name + Separator;
            var age = character.age + Separator;
            var devilFruit = character.devilFruit + Separator;
            var bounty = character.bounty + Separator;
            var firstAppearance = character.firstAppearance + Separator;
            var type = character.type + Separator;
            var alive = character.alive + Separator;
            var crew = character.crew + Separator;
            var picture = character.picture + Separator;
            var level = character.level + Separator;
            
            return (name + age + devilFruit + firstAppearance + bounty + type + crew + picture + level + alive);
        }
        
        
    }
}

