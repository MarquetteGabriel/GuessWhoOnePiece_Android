using GuessWhoOnePiece.Services;

namespace GuessWhoOnePiece.Tests.Services
{
    public class IndexMenuServiceTest
    {
        [Fact]
        public void Test_IndexMenuService()
        {
            var IM = new IndexMenuService();
            Assert.Equal(Model.IndexMenuTabBar.Home, IM.IndexMenu);

            IM.IndexMenu = Model.IndexMenuTabBar.Daily;
            Assert.Equal(Model.IndexMenuTabBar.Daily, IM.IndexMenu);

            IM.IndexMenu = Model.IndexMenuTabBar.Settings;
            Assert.Equal(Model.IndexMenuTabBar.Settings, IM.IndexMenu);

            IM.IndexMenu = Model.IndexMenuTabBar.Game;
            Assert.Equal(Model.IndexMenuTabBar.Game, IM.IndexMenu);

            IM.IndexMenu = Model.IndexMenuTabBar.Characters;
            Assert.Equal(Model.IndexMenuTabBar.Characters, IM.IndexMenu);

            IM.IndexMenu = Model.IndexMenuTabBar.Home;
            Assert.Equal(Model.IndexMenuTabBar.Home, IM.IndexMenu);
        }
    }
}
