using GuessWhoOnePiece.Model.DataEntries;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.DataBase
{
    public class H_Database
    {
        private ControlRoom controlRoom;
        private const string Unknown = "Unknown";
        public H_Database()
        {
            controlRoom = new ControlRoom();
        }

        [SkippableFact]
        public async Task H_Hache_d_Argent()
        {
            var character = "Hache d'Argent";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hache d'Argent", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hack()
        {
            var character = "Hack";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hack", result!.Name);
            Assert.Equal(38, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(706, result.FirstAppearance);
            Assert.Equal("Revolutionary's Crew", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Revolutionary", result.Type);
        }

        [SkippableFact]
        public async Task H_Haguar_D_Sauro()
        {
            var character = "Haguar D. Sauro";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Haguar D. Sauro", result!.Name);
            Assert.Equal(127, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(392, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hajrudin()
        {
            var character = "Hajrudin";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hajrudin", result!.Name);
            Assert.Equal(81, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(706, result.FirstAppearance);
            Assert.Equal("Allié de L'Équipage du Chapeau de Paille", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hakowan()
        {
            var character = "Hakowan";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hakowan", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hakugan()
        {
            var character = "Hakugan";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hakugan", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Ham_Burger()
        {
            var character = "Ham Burger";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Ham Burger", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hamburg()
        {
            var character = "Hamburg";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hamburg", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hamlet()
        {
            var character = "Hamlet";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hamlet", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hammond()
        {
            var character = "Hammond";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hammond", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hana()
        {
            var character = "Hana";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hana", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hangan()
        {
            var character = "Hangan";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hangan", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hanger()
        {
            var character = "Hanger";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hanger", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hanji()
        {
            var character = "Hanji";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hanji", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hanji_commerçant()
        {
            var character = "Hanji (commerçant)";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hanji (commerçant)", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hanko()
        {
            var character = "Hanko";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hanko", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hannyabal()
        {
            var character = "Hannyabal";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hannyabal", result!.Name);
            Assert.Equal(35, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(525, result.FirstAppearance);
            Assert.Equal("Impel Down", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task H_Hanzo()
        {
            var character = "Hanzo";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hanzo", result!.Name);
            Assert.Equal(42, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(931, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Happa_Yamao()
        {
            var character = "Happa Yamao";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Happa Yamao", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Happygun()
        {
            var character = "Happygun";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Happygun", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hara()
        {
            var character = "Hara";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hara", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Haredas()
        {
            var character = "Haredas";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Haredas", result!.Name);
            Assert.Equal(97, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(523, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hareji()
        {
            var character = "Hareji";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hareji", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Haritsu_Kendiyo()
        {
            var character = "Haritsu Kendiyo";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Haritsu Kendiyo", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Haruta()
        {
            var character = "Haruta";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Haruta", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hatcha()
        {
            var character = "Hatcha";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hatcha", result!.Name);
            Assert.Equal(188, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(980, result.FirstAppearance);
            Assert.Equal("L'Équipage aux Cent Bêtes", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hattori()
        {
            var character = "Hattori";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hattori", result!.Name);
            Assert.Equal(20, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(323, result.FirstAppearance);
            Assert.Equal("Cipher Pol", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task H_Heat()
        {
            var character = "Heat";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Heat", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Helwett()
        {
            var character = "Helwett";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Helwett", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Heppoko()
        {
            var character = "Heppoko";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Heppoko", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hera()
        {
            var character = "Hera";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hera", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Héra()
        {
            var character = "Héra";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Héra", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Heracles()
        {
            var character = "Heracles";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Heracles", result!.Name);
            Assert.Equal(50, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(524, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Herb()
        {
            var character = "Herb";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Herb", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hermep()
        {
            var character = "Hermep";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hermep", result!.Name);
            Assert.Equal(22, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(3, result.FirstAppearance);
            Assert.Equal("Navy's Crew", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task H_Hibari()
        {
            var character = "Hibari";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hibari", result!.Name);
            Assert.Equal(17, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(1061, result.FirstAppearance);
            Assert.Equal("Navy's Crew", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task H_Hidayu()
        {
            var character = "Hidayu";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hidayu", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hideo_Usaguchi()
        {
            var character = "Hideo Usaguchi";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hideo Usaguchi", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Higuma()
        {
            var character = "Higuma";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Higuma", result!.Name);
            Assert.Equal(46, result.Age);
            Assert.False(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(1, result.FirstAppearance);
            Assert.Equal("Bandit", result.Crew);
            Assert.Equal("8 Mi", result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hihimaru()
        {
            var character = "Hihimaru";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hihimaru", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hikoichi()
        {
            var character = "Hikoichi";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hikoichi", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hildon()
        {
            var character = "Hildon";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hildon", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hiluluk()
        {
            var character = "Hiluluk";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hiluluk", result!.Name);
            Assert.Equal(68, result.Age);
            Assert.False(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(140, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Himuro()
        {
            var character = "Himuro";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Himuro", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hina()
        {
            var character = "Hina";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hina", result!.Name);
            Assert.Equal(34, result.Age);
            Assert.True(result.Alive);
            Assert.True(result.DevilFruit);
            Assert.Equal(171, result.FirstAppearance);
            Assert.Equal("Navy's Crew", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Navy", result.Type);
        }

        [SkippableFact]
        public async Task H_Hip()
        {
            var character = "Hip";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hip", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hippo_Gentleman()
        {
            var character = "Hippo-Gentleman";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hippo-Gentleman", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hiramera()
        {
            var character = "Hiramera";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hiramera", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hiroshi()
        {
            var character = "Hiroshi";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hiroshi", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hitsughiskhan()
        {
            var character = "Hitsughiskhan";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hitsughiskhan", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hocha()
        {
            var character = "Hocha";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hocha", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hody_Jones()
        {
            var character = "Hody Jones";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hody Jones", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(608, result.FirstAppearance);
            Assert.Equal("L'Équipage des Nouveaux Hommes-Poissons", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hoe()
        {
            var character = "Hoe";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hoe", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hogback()
        {
            var character = "Hogback";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hogback", result!.Name);
            Assert.Equal(47, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(446, result.FirstAppearance);
            Assert.Equal("Thriller Bark", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hoichael()
        {
            var character = "Hoichael";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hoichael", result!.Name);
            Assert.Equal(14, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(432, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hokker()
        {
            var character = "Hokker";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hokker", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Holdem()
        {
            var character = "Holdem";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Holdem", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.True(result.DevilFruit);
            Assert.Equal(914, result.FirstAppearance);
            Assert.Equal("L'Équipage aux Cent Bêtes", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Holy()
        {
            var character = "Holy";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Holy", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Home()
        {
            var character = "Home";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Home", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Homme_à_la_cicatrice_de_feu()
        {
            var character = "Homme à la cicatrice de feu";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Homme à la cicatrice de feu", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hongo()
        {
            var character = "Hongo";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hongo", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Honner()
        {
            var character = "Honner";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Honner", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hoo()
        {
            var character = "Hoo";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hoo", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hoop_Slap()
        {
            var character = "Hoop Slap";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hoop Slap", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hop()
        {
            var character = "Hop";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hop", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hotei()
        {
            var character = "Hotei";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hotei", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hotori()
        {
            var character = "Hotori";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hotori", result!.Name);
            Assert.Equal(27, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(261, result.FirstAppearance);
            Assert.Equal("Clan d'Ener", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

        [SkippableFact]
        public async Task H_Hou_Hou()
        {
            var character = "Hou Hou";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hou Hou", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_House()
        {
            var character = "House";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("House", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Howling_Gab()
        {
            var character = "Howling Gab";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Howling Gab", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hublot()
        {
            var character = "Hublot";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hublot", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Humphrey()
        {
            var character = "Humphrey";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Humphrey", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hustle()
        {
            var character = "Hustle";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hustle", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hyogoro()
        {
            var character = "Hyogoro";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hyogoro", result!.Name);
            Assert.Equal(70, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(926, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hyota()
        {
            var character = "Hyota";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hyota", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hyotauros()
        {
            var character = "Hyotauros";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hyotauros", result!.Name);
            Assert.Equal(30, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(77, result.FirstAppearance);
            Assert.Equal("Citizen", result.Crew);
            Assert.Equal("0", result.Bounty);
            Assert.Equal("Citizen", result.Type);
        }

        [SkippableFact]
        public async Task H_Hyouzou()
        {
            var character = "Hyouzou";
            var characterLink = ControlRoom.SetCharacterLink(character);
            var result = await controlRoom.DataForCharacter(characterLink, character);

            Skip.If(result == null, "Character not found");
            Assert.Equal("Hyouzou", result!.Name);
            Assert.Equal(39, result.Age);
            Assert.True(result.Alive);
            Assert.False(result.DevilFruit);
            Assert.Equal(607, result.FirstAppearance);
            Assert.Equal("L'Équipage des Nouveaux Hommes-Poissons", result.Crew);
            Assert.Equal(Unknown, result.Bounty);
            Assert.Equal("Pirate", result.Type);
        }

    }
}
