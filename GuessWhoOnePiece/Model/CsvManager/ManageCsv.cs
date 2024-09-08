// <copyright file="ManageCsv.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the managing of the Csv.</summary>
    public static class ManageCsv
    {
        /// <summary>Path of the Csv.</summary>
        private static readonly string CsvPath = FileSystem.Current.AppDataDirectory + "Characters.csv";
        /// <summary>Separator of the Csv.</summary>
        private const string Separator = ";";

        /// <summary>Add a character to the Csv.</summary>
        /// <param name="character">The character to add.</param>
        public static void SaveCharacterToCsv(Character character)
        {
            CreateCsvFile(CsvPath);

            using var sw = File.CreateText(CsvPath);
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
            var name = character.Name + Separator;
            var age = character.Age + Separator;
            var devilFruit = character.DevilFruit + Separator;
            var bounty = character.Bounty + Separator;
            var firstAppearance = character.FirstAppearance + Separator;
            var type = character.Type + Separator;
            var alive = character.Alive + Separator;
            var crew = character.Crew + Separator;
            var picture = character.Picture + Separator;
            var level = character.Level + Separator;
            
            return (name + age + devilFruit + firstAppearance + bounty + type + crew + picture + level + alive);
        }
    }
}

