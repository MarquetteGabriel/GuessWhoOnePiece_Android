using GuessWhoOnePiece.Model.Converts;
using System.IO;

namespace GuessWhoOnePiece.Tests.Converts
{
    public class StreamTest
    {
        [Fact]
        public void Test_GetImageStream()
        {
            var pictureName = "error.jpg";
            var result = PictureStream.GetImageStream(pictureName);

            Assert.NotNull(result);
        }
    }
}
