using System.IO;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.Maui.Storage;
using System.Drawing;
using System.IO;


namespace GuessWhoOnePiece.Model.CsvManager
{
    public class PictureManager
    {
        internal static readonly string CsvPath = Path.Combine(FileSystem.Current.AppDataDirectory, "Images");

        public static async Task<string> DownloadImage (string imageUrl, string fileName)
        {
            fileName = fileName + ".jpeg";
            try
            {
                if (!Directory.Exists(CsvPath))
                    Directory.CreateDirectory(CsvPath);


                var imageData = await new HttpClient().GetByteArrayAsync(imageUrl);

                using (MemoryStream memoryStream = new MemoryStream(imageData))
                {
                    using System.Drawing.Image image = System.Drawing.Image.FromStream(memoryStream);
                    var outputFilePath = Path.Combine(CsvPath, fileName);
                    image.Save(outputFilePath, System.Drawing.Imaging.ImageFormat.Jpeg);

                    return outputFilePath;
                }

            }
            catch (Exception ex)
            {
                return imageUrl;
            }
        }

    }
}
