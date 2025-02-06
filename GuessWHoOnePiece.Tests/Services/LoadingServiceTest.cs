﻿using GuessWhoOnePiece.Services;

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

            LS.Loading = true;
            Assert.True(LS.Loading);

            LS.Loading = false;
            Assert.False(LS.Loading);
        }
    }
}
