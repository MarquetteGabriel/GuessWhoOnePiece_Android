using System.Threading.Tasks;
using GuessWhoOnePiece.Model.CsvManager;

namespace GuessWhoOnePiece.Tests.CsvManager
{
    /// <summary>Test class "ManageCsv".</summary>
    public class PictureManagerTest
    {
        [Fact (Skip = "Invalid Assembly")]
        public async Task Test_DownloadImageAsync()
        {
            string picturePath = "https://static.wikia.nocookie.net/onepiece/images/8/8e/Anime_Yu_Infobox.png/revision/latest?cb=20130324135429&path-prefix=fr";
            var result = await PictureManager.DownloadImageAsync(picturePath, "Yu");

            Assert.NotNull(result);
            Assert.Equal("Images/Monkey D. Luffy.jpeg", result);
        }
    }
}
