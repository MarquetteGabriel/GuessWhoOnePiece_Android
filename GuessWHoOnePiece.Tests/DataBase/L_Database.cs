using GuessWhoOnePiece.Model.DataEntries;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.DataBase
{
    public class L_Database
    {
        private ControlRoom controlRoom;
        private const string Unknown = "Unknown";
        public L_Database()
        {
            controlRoom = new ControlRoom();
        }

        [SkippableFact]
        public async Task L_Laboon()
        {
            var character = "Laboon";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Laboon", result!.Name);
            Assert.Equal(53, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(102, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lackey()
        {
            var character = "Lackey";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lackey", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lacroix()
        {
            var character = "Lacroix";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lacroix", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lacuba()
        {
            var character = "Lacuba";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lacuba", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Laffitte()
        {
            var character = "Laffitte";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Laffitte", result!.Name);
            Assert.Equal(41, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(234, result.FirstAppearance);
            Assert.Equal("L'Équipage de Barbe Noire", result.Crew);
            Assert.Equal("42,2 Mi", result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task L_Laki()
        {
            var character = "Laki";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Laki", result!.Name);
            Assert.Equal(24, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(249, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lao_G()
        {
            var character = "Lao G";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lao G", result!.Name);
            Assert.Equal(70, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(682, result.FirstAppearance);
            Assert.Equal("L'Équipage de Don Quichotte Doflamingo", result.Crew);
            Assert.Equal("61 Mi", result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task L_Laskey()
        {
            var character = "Laskey";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Laskey", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lassou()
        {
            var character = "Lassou";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lassou", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lemoncheese()
        {
            var character = "Lemoncheese";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lemoncheese", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Leo()
        {
            var character = "Leo";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Leo", result!.Name);
            Assert.Equal(25, result.Age);
            Assert.True(result.Alive);
            Assert.True(result.DevilFruit);
            Assert.Equal(710, result.FirstAppearance);
            Assert.Equal("Allié de L'Équipage du Chapeau de Paille", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task L_Leonero()
        {
            var character = "Leonero";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Leonero", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_L_homme_aux_yeux_rouges()
        {
            var character = "L'homme aux yeux rouges";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("L'homme aux yeux rouges", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lilith()
        {
            var character = "Lilith";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lilith", result!.Name);
            Assert.Equal(24, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(1061, result.FirstAppearance);
            Assert.Equal("Gouvernement Mondial", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task L_Lily()
        {
            var character = "Lily";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lily", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Limejuice()
        {
            var character = "Limejuice";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Limejuice", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lindbergh()
        {
            var character = "Lindbergh";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lindbergh", result!.Name);
            Assert.Equal(37, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(904, result.FirstAppearance);
            Assert.Equal("Revolutionary's Crew", result.Crew);
            Assert.Equal("316 Mi", result.Bounty);
            Assert.Equal("Revolutionary", result.Type);
        }

        [SkippableFact]
        public async Task L_Lines()
        {
            var character = "Lines";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lines", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lip_Doughty()
        {
            var character = "Lip Doughty";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lip Doughty", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Little_Oars_Jr()
        {
            var character = "Little Oars Jr.";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Little Oars Jr.", result!.Name);
            Assert.Equal(72, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(554, result.FirstAppearance);
            Assert.Equal("L'Équipage de Barbe Blanche", result.Crew);
            Assert.Equal("550 Mi", result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task L_Loki()
        {
            var character = "Loki";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Loki", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lombric()
        {
            var character = "Lombric";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lombric", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Longs_Cils()
        {
            var character = "Longs-Cils";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Longs-Cils", result!.Name);
            Assert.Equal(17, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(162, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Louis_Arnote()
        {
            var character = "Louis Arnote";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Louis Arnote", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task L_Lucky_Roux()
        {
            var character = "Lucky Roux";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lucky Roux", result!.Name);
            Assert.Equal(35, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(1, result.FirstAppearance);
            Assert.Equal("L'Équipage du Roux", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task L_Lulis()
        {
            var character = "Lulis";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Lulis", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

    }
}
