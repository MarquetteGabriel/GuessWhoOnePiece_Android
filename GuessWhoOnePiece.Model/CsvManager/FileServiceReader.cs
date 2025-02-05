﻿using Microsoft.Maui.Storage;
using System.IO;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Model.CsvManager
{
    public class FileServiceReader : IFileServiceReader
    {
        public async Task<Stream> ReadAllLinesAsync(string path)
        {
            return File.OpenRead(path);
        }

        public string GetCsvPath { get; } = Path.Combine(FileSystem.Current.AppDataDirectory, "Characters.csv");

        public string GetPicturePath { get; } = Path.Combine(FileSystem.Current.AppDataDirectory, "Images");
    }
}
