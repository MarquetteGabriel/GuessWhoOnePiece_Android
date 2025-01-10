using SkiaSharp;


namespace GuessWhoOnePiece.Model.CsvManager
{
    /// <summary>Represents the retrieval of pictures.</summary>
    public class PictureManager
    {
        /// <summary>Path to the folder of pictures.</summary>
        internal static readonly string PicturePath = Path.Combine(FileSystem.Current.AppDataDirectory, "Images");

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

                var imageData = await new HttpClient().GetByteArrayAsync(imageUrl);

                using MemoryStream memoryStream = new(imageData);
                using var skBitmap = SKBitmap.Decode(memoryStream);
                using var image = SKImage.FromBitmap(skBitmap);
                using var data = image.Encode(SKEncodedImageFormat.Jpeg, 100);

                var outputFilePath = Path.Combine(PicturePath, fileName);
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
