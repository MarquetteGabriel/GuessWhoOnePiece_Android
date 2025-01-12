﻿// <copyright file="JudgementAnswer.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.Game;

namespace GuessWhoOnePiece.Tests.Game
{
    public class JudgementAnswerTest
    {
        private Character characterDefaut;
        private Character character1;
        private Character character3;
        private Character character4;
        private Character character5;
        private JudgementAnswer judgmentAnswer;

        public JudgementAnswerTest()
        {
            characterDefaut = new Character("Charlotte Katakuri", true, "1,049 Md", 849, "Pirate", true, 48, "Big Mom Crew", "", 0);
            character1 = new Character ("Monkey D. Luffy", true, "3 Md", 1, "Pirate", true, 19, "Straw Hat Pirates", "", 0);
            character3 = new Character("Portgas D. Ace", false, "550 Mi", 1080, "Pirate", false, 20, "Spade Pirates", "", 0);
            character4 = new Character("Ener", true, "Unknown", 220, "Pirate", true, 40, "Ener Crew", "", 0);
            character5 = new Character("Kizaru", true, "0", 350, "Navy", true, 53, "Navy's Crew", "", 0);
            judgmentAnswer = new JudgementAnswer(characterDefaut);
        }

        [Fact]
        public void Test_IsAlive()
        {
            var result = judgmentAnswer.IsAlive(character3);
            Assert.False(result);

            result = judgmentAnswer.IsAlive(character1);
            Assert.True(result);    
        }

        [Fact]
        public void Test_HasEatenDevilFruit()
        {
            var result = judgmentAnswer.HasEatenDevilFruit(character3);
            Assert.False(result);

            result = judgmentAnswer.HasEatenDevilFruit(character1);
            Assert.True(result);
        }

        [Fact]
        public void Test_WantedBounty()
        {
            var result = judgmentAnswer.WantedBounty(character1);
            Assert.Equal(BountyType.Lower, result);

            result = judgmentAnswer.WantedBounty(character3);
            Assert.Equal(BountyType.Upper, result);

            result = judgmentAnswer.WantedBounty(characterDefaut);
            Assert.Equal(BountyType.Equal, result);

            result = judgmentAnswer.WantedBounty(character4);
            Assert.Equal(BountyType.WrongUnknown, result);
        }

        [Fact]
        public void Test_IsSameName()
        {
            var result = judgmentAnswer.IsSameName(character1);
            Assert.False(result);

            result = judgmentAnswer.IsSameName(characterDefaut);
            Assert.True(result);
        }

        [Fact]
        public void Test_WhatOccupation()
        {
            var result = judgmentAnswer.WhatOccupation(character1);
            Assert.True(result);

            result = judgmentAnswer.WhatOccupation(character5);
            Assert.False(result);
        }

        [Fact]
        public void Test_WhatCrew()
        {
            var result = judgmentAnswer.WhatCrew(characterDefaut);
            Assert.True(result);

            result = judgmentAnswer.WhatCrew(character1);
            Assert.False(result);
        }

        [Fact]
        public void Test_IsNewer()
        {
            var result = judgmentAnswer.IsNewer(character1);
            Assert.Equal(ChapterType.NewerChapter, result);

            result = judgmentAnswer.IsNewer(character3);
            Assert.Equal(ChapterType.PreviousChapter, result);

            result = judgmentAnswer.IsNewer(characterDefaut);
            Assert.Equal(ChapterType.SameChapter, result);
        }

        [Fact]
        public void Test_IsOlder()
        {
            var result = judgmentAnswer.IsOlder(character1);
            Assert.Equal(AgeType.Younger, result);

            result = judgmentAnswer.IsOlder(character5);
            Assert.Equal(AgeType.Older, result);

            result = judgmentAnswer.IsOlder(characterDefaut);
            Assert.Equal(AgeType.Equal, result);
        }
    }
}

