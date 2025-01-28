// <copyright file="GuesserTest.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using System.Collections.Generic;
using System.Reflection;
using System.Threading.Tasks;
using GuessWhoOnePiece.Model.Game;
using Pose;

namespace GuessWhoOnePiece.Tests.Game
{
    /// <summary>Test class "GuesserTest".</summary>
    public class GuesserTest
    {
        #region SetCharacterToFind Tests
        
        [Fact]
        public async void Test_SetCharacterToFind()
        {
            var result = await Guesser.SetCharacterToFind();

            Assert.NotNull(result);
            Assert.IsType<int>(result);
        }

        #endregion

        #region RandomizeNumer Tests

        [Fact]
        public void Test_RandomizeNumber()
        {
            int maxNumber = 10;
            var method = typeof(Guesser).GetMethod("RandomizeNumber", BindingFlags.NonPublic | BindingFlags.Static);

            Assert.NotNull(method);

            var result = method.Invoke(null, new object[] { maxNumber });

            Assert.NotNull(result);
            Assert.True((int) result >= 0);
            Assert.True((int)result < maxNumber);
        }

        #endregion
    }
}

