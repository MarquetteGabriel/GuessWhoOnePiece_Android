using Microsoft.Maui.Storage;
using SkiaSharp;
using System;
using System.IO;
using System.Net.Http;
using System.Threading.Tasks;


namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the retrieval of pictures.</summary>
    public static class PictureManager
    {
        /// <summary>Path to the folder of pictures.</summary>
        internal static readonly string PicturePath = Path.Combine(FileSystem.Current.AppDataDirectory, "Images");

        private static readonly HttpClient _httpClient = new();

        /// <summary>Dowload picture to the folder.</summary>
        /// <param name="imageUrl">Link of the picture.</param>
        /// <param name="fileName">Name of the character which is used in the fileName.</param>
        /// <returns>The fileName.</returns>
        public static async Task<string> DownloadImageAsync (string imageUrl, string fileName)
        {
            fileName = fileName + ".jpeg";
            try
            {
                if (!Directory.Exists(PicturePath))
                    Directory.CreateDirectory(PicturePath);

                var outputFilePath = Path.Combine(PicturePath, fileName);
                if (File.Exists(outputFilePath))
                    return @"Images/" + fileName;

                var imageData = await _httpClient.GetByteArrayAsync(imageUrl);

                using MemoryStream memoryStream = new(imageData);
                using var skBitmap = SKBitmap.Decode(memoryStream);
                using var image = SKImage.FromBitmap(skBitmap);
                using var data = image.Encode(SKEncodedImageFormat.Jpeg, 100);

                await File.WriteAllBytesAsync(outputFilePath, data.ToArray());

                return @"Images/" + fileName;
            }
            catch (Exception)
            {
                return imageUrl;
            }
        }

    }
}
