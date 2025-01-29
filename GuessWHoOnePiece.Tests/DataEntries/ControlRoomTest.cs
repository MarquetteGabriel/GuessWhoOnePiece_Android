﻿// <copyright file="ControlRoomTest.cs">
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
        [Fact]
        public async Task Test_ReceiveCharacter()
        {
            var result = await CharacterNameListManager.ReceivedCharactersList();

            Assert.NotNull(result);
            Assert.Equal(1386, result.Count);
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
            var controlRoom = new ControlRoom();
            var characterList = await controlRoom.GenerateThreads();

            Assert.NotNull(characterList);
            Assert.Equal(controlRoom.CharacterCount, controlRoom.CountPercentage);
            Assert.Equal(100, controlRoom.Percentage);
        }

        #endregion
    }
}
