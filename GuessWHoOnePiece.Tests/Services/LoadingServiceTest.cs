using GuessWhoOnePiece.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.Services
{
    public class LoadingServiceTest
    {
        [Fact]
        public void Test_LoadingService()
        {
            var LS = new LoadingService();
            Assert.False(LS.Loading);

            LS.Loading = true;
            Assert.True(LS.Loading);
        }
    }
}
