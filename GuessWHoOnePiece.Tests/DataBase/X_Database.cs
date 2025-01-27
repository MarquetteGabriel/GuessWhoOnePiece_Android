// <copyright file="X_Database.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.DataEntries;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.DataBase
{
    public class X_Database
    {
        private ControlRoom controlRoom;

        public X_Database()
        {
            controlRoom = new ControlRoom();
        }

        [SkippableFact]
        public async Task XDrake()
        {
            var character = "X Drake";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("X Drake", result!.Name);
            Assert.Equal(33, result.Age);
            Assert.True(result.Alive);
            Assert.True(result.DevilFruit);
            Assert.Equal(498, result.FirstAppearance);
            Assert.Equal("Navy's Crew", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }
    }
}
