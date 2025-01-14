// <copyright file="DataControlTest.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.DataEntries;
using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities.Resources;
using System;

namespace GuessWhoOnePiece.Tests.DataEntries
{
    public class DataControlTest
    {
        #region ExtractPattern Tests

        [Fact]
        public void Test_ExtractPattern()
        {
            var textInput = "InformationsNom Japonais :さらへび先生Nom Romanisé :Sarahebi-senseiPremière Apparition:Chapitre 919 ; Épisode 909[1]Affiliations :L'Équipage aux Cent BêtesOccupations :Gifter,[2] Enseignante[1]Statut :VivanteVoix Japonaise :Yuka Komatsu Fruit du DémonNom Japonais :Smile du serpent[3]Type :SMILE";
            var pattern = "Chapitre (\\d+)";
            var result = DataControl.ExtractPattern(textInput, pattern);
            Assert.Equal("919", result);
        }

        [Fact]
        public void Test_ExtractPatternEmpty()
        {
            var textInput = "";
            var pattern = "Chapitre (\\d+)";
            var result = DataControl.ExtractPattern(textInput, pattern);
            Assert.Equal("", result);
        }

        #endregion

        #region ExtractPatternBounty Tests

        [Fact (Skip = "Non Written")]
        public void Test_ExtractPatternBounty()
        {
            var input = "\"InformationsNom Japonais :ロブソンNom Romanisé :RobusonNom Français :RobsonPremière Apparition:Chapitre 135,Épisode 81[1]Affiliations :Royaume de Drum (anciennement), L’Équipage de Wapol (anciennement)[1]Occupations :Monture de Wapol[1]Voix Japonaise :Kenji NomuraVoix Française :Kyle Phillips\"";
            var result = DataControl.ExtractPatternBounty(input, "Pirate", "Luffy");
            Assert.Equal("", result);
        }

        #endregion

        #region FixBounty Tests
        /*
        [Fact]
        public void Test_FixBounty()
        {
            var bounty = "";
            var type = "Citizen";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("0", result);
        }

        [Fact]
        public void Test_FixBounty_NavyType()
        {
            var bounty = "100000000";
            var type = "Navy";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("0", result);
        }

        [Fact]
        public void Test_FixBounty_CitizenType()
        {
            var bounty = "100000000";
            var type = "Citizen";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("0", result);
        }

        [Fact]
        public void Test_FixBounty_UnknownBounty()
        {
            var bounty = "Inconnue";
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("Unknown", result);
        }

        [Fact]
        public void Test_FixBounty_NoBounty()
        {
            var bounty = "Aucune";
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("Unknown", result);
        }

        [Fact]
        public void Test_FixBounty_EmptyBounty()
        {
            var bounty = string.Empty;
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("Unknown", result);
        }

        [Fact]
        public void Test_FixBounty_BillionsDollarsValue()
        {
            var bounty = "5000000000";
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("5 Md", result);
        }

        [Fact]
        public void Test_FixBounty_MillionsDollarsValue()
        {
            var bounty = "5000000";
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("5 Mi", result);
        }

        [Fact]
        public void Test_FixBounty_LessThanMillion()
        {
            var bounty = "100000";
            var type = "Pirate";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("100000", result);
        }

        [Fact]
        public void Test_FixBounty_NavyTypeList()
        {
            var bounty = "100000000";
            var type = "Marine";
            var result = DataControl.FixBounty(bounty, type);
            Assert.Equal("0", result);
        }
        */
        #endregion

        #region FixType Tests

        /*
        [Fact]
        public void Test_FixType()
        {
            var value = "Citizen";
            var crew = "Citizen";
            var result = DataControl.FixType(value, crew);
            Assert.Equal("Citizen", result);
        }

        [Fact]
        public void Test_FixType_CelestialDragons()
        {
            var value = "Citizen";
            var crew = "Celestial Dragons";
            var result = DataControl.FixType(value, crew);
            Assert.Equal("Navy", result);
        }

        [Fact]
        public void Test_FixType_ReturnValue()
        {
            var crew = "Citizen";
            var value = "Dragon Celestes";
            var result = DataControl.FixType(value, crew);
            Assert.Equal(value, result);
        }

        [Fact]
        public void Test_FixType_Pirate()
        {
            var crew = "Bandits";
            var value = "Dragons Celestes";
            var result = DataControl.FixType(value, crew);
            Assert.Equal("Pirate", result);
        }

        [Fact]
        public void Test_FixType_Navy()
        {
            var crew = "Souverain";
            var value = "Dragons Celestes";
            var result = DataControl.FixType(value, crew);
            Assert.Equal("Navy", result);
        }

        [Fact]
        public void Test_FixType_Revos()
        {
            var crew = "Armée de la Liberté";
            var value = "Dragons Celestes";
            var result = DataControl.FixType(value, crew);
            Assert.Equal("Revolutionary", result);
        }
        */

        #endregion

        #region FixCrew Tests
        /*
        [Fact]
        public void Test_FixCrew()
        {
            var rawcrew = "Clan d'Ener";
            var type = "Citizen";
            var result = DataControl.FixCrew(rawcrew, type);
            Assert.Equal("Citizen", result);
        }

        [Fact]
        public void Test_FixCrew_Aigis()
        {
            var rawcrew = "CP0 - Aigis";
            var type = "Navy";
            var result = DataControl.FixCrew(rawcrew, type);
            Assert.Equal("Cipher Pol", result);
        }

        [Fact]
        public void Test_FixCrew_Null()
        {
            var rawcrew = string.Empty;
            var type = "Citizen";
            var result = DataControl.FixCrew(rawcrew, type);
            Assert.Equal("Citizen", result);
        }

        [Fact]
        public void Test_FixCrew_CelestialDragons()
        {
            var rawcrew = "Dragon Celestes";
            var type = "Navy";
            var result = DataControl.FixCrew(rawcrew, type);
            Assert.Equal("Celestial Dragons", result);
        }

        [Fact]
        public void Test_FixCrew_RevolutionarysCrew()
        {
            var rawCrew = "Revolutionary";
            var type = "Revolutionary";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Revolutionary's Crew", result);
        }

        [Fact]
        public void Test_FixCrew_NavyCrew()
        {
            var rawCrew = "Marine";
            var type = "Navy";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Navy's Crew", result);
        }

        [Fact]
        public void Test_FixCrew_WhitebeardPirates()
        {
            var rawCrew = "Subordonné de L'Équipage de Barbe Blanche";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage de Barbe Blanche", result);
        }

        [Fact]
        public void Test_FixCrew_StrawHatAllies()
        {
            var rawCrew = "La Grande Flotte du Chapeau de Paille";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Allié de L'Équipage du Chapeau de Paille", result);
        }

        [Fact]
        public void Test_FixCrew_BigMomPirates()
        {
            var rawCrew = "Équipage de Big Mom";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage de Big Mom", result);
        }

        [Fact]
        public void Test_FixCrew_BaroqueWorks()
        {
            var rawCrew = "Spiders Café";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Baroque Works", result);
        }

        [Fact]
        public void Test_FixCrew_CaribouPirates()
        {
            var rawCrew = "Capitaine de l'Equipage de Caribou";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage de Caribou", result);
        }

        [Fact]
        public void Test_FixCrew_DoflamingoPirates()
        {
            var rawCrew = "L'Équipage de Barbe Brune(dissout)";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage de Don Quichotte Doflamingo", result);
        }

        [Fact]
        public void Test_FixCrew_NewFishmanPirates()
        {
            var rawCrew = "L'Équipage des Pirates Volants";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage des Nouveaux Hommes-Poissons", result);
        }

        [Fact]
        public void Test_FixCrew_FireTankPirates()
        {
            var rawCrew = "L'Équipage du Firetank";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("L'Équipage du Fire Tank", result);
        }

        [Fact]
        public void Test_FixCrew_ThrillerBark()
        {
            var rawCrew = "Gecko Moria";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Thriller Bark", result);
        }

        [Fact]
        public void Test_FixCrew_CrossGuild()
        {
            var rawCrew = "Alliance Baggy et Alvida";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Cross Guild", result);
        }

        [Fact]
        public void Test_FixCrew_Citizen()
        {
            var rawCrew = "L'Équipage du Capitaine Usopp";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal("Citizen", result);
        }

        [Fact]
        public void Test_FixCrew_Default()
        {
            var rawCrew = "Unknown Crew";
            var type = "Pirate";
            var result = DataControl.FixCrew(rawCrew, type);
            Assert.Equal(rawCrew, result);
        }

        */
        #endregion

        #region ExtractPatternCrew Tests
        /*
        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternCrew()
        {
            var result = DataControl.ExtractPatternCrew(null);
        }
        */
        #endregion


        #region ExtractPatternAge Tests

        [Fact]
        public void Test_ExtractPatternAge_Basic()
        {
            var result = DataControl.ExtractPatternAge("rebc Âge : 18 ans ; Taille : 1,72m", "Monkey D. Luffy");
            Assert.Equal(18, result); 
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_Error()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_Mozu()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_Kiwi()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_Kerville()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_Bas()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternAge_And()
        {
            var result = DataControl.ExtractPatternAge("", "");
        }

        #endregion

        #region ExtractPatternType Tests

        [Fact(Skip = "Non Written")]
        public void Test_ExtractPatternType()
        {
            var result = DataControl.ExtractPatternType(null, "");
        }

        #endregion

        #region ExtractExceptions Tests

        [Fact]
        public void Test_ExtractExceptions_Unchanged()
        {
            var character = "Monkey D. Luffy";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Monkey D. Luffy", result);
        }

        [Fact]
        public void Test_ExtractExceptions_BBrune()
        {
            var character = "Chadros Higelyges";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Barbe Brune", result);
        }

        [Fact]
        public void Test_ExtractExceptions_Jabura()
        {
            var character = "Jabra";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Jabura", result);
        }

        [Fact]
        public void Test_ExtractExceptions_Tama()
        {
            var character = "Tama";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Kurozumi Tama", result);
        }

        [Fact]
        public void Test_ExtractExceptions_Kaku()
        {
            var character = "Kaku (Wano)";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Kaku", result);
        }

        [Fact]
        public void Test_ExtractExceptions_Ener()
        {
            var character = "Enel";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Ener", result);
        }

        [Fact]
        public void Test_ExtractExceptions_Bakkin()
        {
            var character = "Buckingham Stussy";
            var result = DataControl.ExtractExceptions(character);
            Assert.Equal("Bakkin", result);
        }

        #endregion

        #region ExtractExceptionsPopularity Tests

        [Fact]
        public void Test_ExtractExceptionsPopularity_Unchanged()
        {
            var character = "Monkey D. Luffy";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Monkey D. Luffy", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_DonQuichotte()
        {
            var character = "Don Quichotte Dofflamingo";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Donquixote Dofflamingo", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_King()
        {
            var character = "Alber";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("King", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_Linlin()
        {
            var character = "Charlotte Linlin";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Big Mom", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_Galdino()
        {
            var character = "Galdino";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Mr 3", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_Teach()
        {
            var character = "Marshall D. Teach";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Barbe Noire", result);
        }

        [Fact]
        public void Test_ExtractExceptionsPopularity_Newgate()
        {
            var character = "Edward Newgate";
            var result = DataControl.ExtractExceptionsPopularity(character);
            Assert.Equal("Barbe Blanche", result);
        }

        #endregion
    }
}
