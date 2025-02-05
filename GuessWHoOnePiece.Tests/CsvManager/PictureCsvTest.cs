using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.Model.DataEntries.Picture;
using Moq;
using System.IO;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.CsvManager
{
    public class PictureCsvTest
    {
        private const string LocalPath = "C:\\Users\\Gabriel Marquette\\AppData\\Local\\Packages\\com.companyname.guesswhoonepiece_9zz4h110yvjzm\\LocalState\\";

        [Fact]
        public async Task Test_DownloadImage_Null()
        {
            var mockFileService = new Mock<IFileServiceReader>();
            mockFileService.Setup(pp => pp.GetPicturePath)
                           .Returns(LocalPath + "Images");
            var result = await PictureManager.DownloadImageAsync("Yosaku", "", mockFileService.Object);

            Assert.NotNull(result);
            Assert.Equal("Yosaku", result);
        }

        [Fact]
        public async Task Test_DownloadImage()
        {
            var mockFileService = new Mock<IFileServiceReader>();
            mockFileService.Setup(pp => pp.GetPicturePath)
                           .Returns(LocalPath + "Images");

            var result = await PictureManager.DownloadImageAsync("", "Yosaku", mockFileService.Object);
            Assert.NotNull(result);
            Assert.Equal("Images/Yosaku.jpeg", result);

            if(File.Exists(LocalPath + "Images/Yosaku.jpeg"))
                File.Delete(LocalPath + "Images/Yosaku.jpeg");

            result = await PictureManager.DownloadImageAsync("https://static.wikia.nocookie.net/onepiece/images/1/1e/Yosaku_Manga_Post_Ellipse_Infobox.png/revision/latest?cb=20150809210655&path-prefix=fr", "Yosaku", mockFileService.Object);
            Assert.NotNull(result);
            Assert.Equal("Images/Yosaku.jpeg", result);
        }
    }
}
