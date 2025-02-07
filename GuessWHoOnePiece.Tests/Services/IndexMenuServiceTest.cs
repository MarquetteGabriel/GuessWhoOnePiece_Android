using GuessWhoOnePiece.Services;

namespace GuessWhoOnePiece.Tests.Services
{
    public class IndexMenuServiceTest
    {
        [Fact]
        public void Test_IndexMenuService()
        {
            var IM = new IndexMenuService();
            Assert.Equal(0, IM.IndexMenu);

            IM.IndexMenu = 1;
            Assert.Equal(1, IM.IndexMenu);

            IM.IndexMenu = 5;
            Assert.Equal(5, IM.IndexMenu);

            IM.IndexMenu = 2;
            Assert.Equal(2, IM.IndexMenu);

            IM.IndexMenu = 4;
            Assert.Equal(4, IM.IndexMenu);

            IM.IndexMenu = 3;
            Assert.Equal(3, IM.IndexMenu);
        }
    }
}
