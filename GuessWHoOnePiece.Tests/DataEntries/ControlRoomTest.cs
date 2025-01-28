// <copyright file="ControlRoomTest.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.Model.DataEntries;
using GuessWhoOnePiece.Model.DataEntries.Picture;
using Moq;
using Pose;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.DataEntries
{
    /// <summary>Test class "ManageCsv".</summary>
    public class ReceiveDataTest
    {
        [Fact (Skip = "Manage.Csv not mocked")]
        public async Task Test_ReceiveCharacter()
        {
            ControlRoom controlRoom = new ControlRoom(null);
            var result = await controlRoom.GenerateThreads();

            Assert.NotNull(result);
            Assert.Equal(485, result.Count);
        }

        #region LevenshteinDistance Tests

        [Fact]
        public void Test_LevenshteinDistance_Equal()
        {
            string str1 = "Monkey D. Luffy";
            string str2 = "Monkey D. Luffy";
            int result = PictureManager.LevenshteinDistance(str1, str2);
            Assert.Equal(0, result);
        }

        [Fact]
        public void Test_LevenshteinDistance_RefEmpty()
        {
            string str1 = string.Empty;
            string str2 = "Monkey D. Luffy";
            int result = PictureManager.LevenshteinDistance(str1, str2);
            Assert.Equal(str2.Length, result);
        }

        [Fact]
        public void Test_LevenshteinDistance_ValueEmpty()
        {
            string str1 = "Monkey D. Luffy";
            string str2 = string.Empty;
            int result = PictureManager.LevenshteinDistance(str1, str2);
            Assert.Equal(str1.Length, result);
        }

        #endregion

        #region CalculateMatchPercentage Tests

        [Fact]
        public void Test_CalculateMatchPercentage_Equal()
        {
            string str1 = "Monkey D. Luffy";
            string str2 = "Monkey D. Luffy";
            double result = PictureManager.CalculateMatchPercentage(str1, str2);
            Assert.Equal(1, result);
        }

        [Fact]
        public void Test_CalculateMatchPercentage_Different()
        {
            string str1 = "Monkey D. Luffy";
            string str2 = "Monkey D. Fluffy";
            double result = PictureManager.CalculateMatchPercentage(str1, str2);
            Assert.Equal(0.875, result);
        }

        [Fact]
        public void Test_CalculateMatchPercentage_Empty()
        {
            string str1 = string.Empty;
            string str2 = string.Empty;
            double result = PictureManager.CalculateMatchPercentage(str1, str2);
            Assert.Equal(1.0, result);
        }

        #endregion

        #region GenerateThreads Tests

        [Fact]
        public async Task Test_GenerateThreads()
        {
            var controlRoom = new ControlRoom(null);
            var _characterNameList = await CharacterNameListManager.ReceivedCharactersList();
            var charactersList = new ConcurrentBag<Character>();
            await Parallel.ForEachAsync(_characterNameList, async (characterName, _) =>
            {
                var character = await controlRoom.DataForCharacter(ControlRoom.SetCharacterLink(characterName), characterName);
                if (character != null)
                    charactersList.Add(character);
            });
            IReadOnlyCollection<Character> characterList = new List<Character>(charactersList.ToList());
            charactersList.Clear();
            characterList = await Popularity.SetPopularity(_characterNameList, characterList);

            Assert.NotNull(characterList);
            Assert.Equal(controlRoom.CharacterCount, controlRoom.CountPercentage);
        }

        #endregion
    }
}
