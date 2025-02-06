﻿using GuessWhoOnePiece.Model.CsvManager;
using Moq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Tests.CsvManager
{
    public class CsvTest
    {

        private const string LocalPath = "C:\\Users\\Gabriel Marquette\\AppData\\Local\\Packages\\com.companyname.guesswhoonepiece_9zz4h110yvjzm\\LocalState\\";

        [Fact]
        public async Task Test_ReadCsvSpecific()
        {
            var mockFileService = new Mock<IFileServiceReader>();
            mockFileService.Setup(pp => pp.GetCsvPath)
                             .Returns(LocalPath + "Characters.csv");

            // Act
            var result = await ReceiveDataCsv.ReceiveCharacter("Yosaku", mockFileService.Object);

            // Assert
            Assert.NotNull(result);
            Assert.Equal("Yosaku", result.Name);
        }

        [Fact]
        public async Task Test_ReadAllCsv()
        {
            var mockFileService = new Mock<IFileServiceReader>();
            mockFileService.Setup(pp => pp.GetCsvPath)
                             .Returns(LocalPath + "Characters.csv");

            // Act
            var result = await ReceiveDataCsv.ReceiveAllCharacters(mockFileService.Object);

            // Assert
            Assert.NotNull(result);
            Assert.Equal(491, result.Count);
        }

        [Fact]
        public async Task Test_ManageCsv()
        {
            var mockFileService = new Mock<IFileServiceReader>();
            mockFileService.Setup(pp => pp.GetCsvPath)
                             .Returns(LocalPath + "Characters.csv");

            var saveData = await ReceiveDataCsv.ReceiveAllCharacters(mockFileService.Object);

            Assert.True(File.Exists(LocalPath + "Characters.csv"));
            ManageCsv.DeleteCsvFile(LocalPath + "Characters.csv");
            Assert.False(File.Exists(LocalPath + "Characters.csv"));
            ManageCsv.SaveCharactersToCsv(saveData, mockFileService.Object);
            ManageCsv.SaveCharactersToCsv(saveData, mockFileService.Object);
            Assert.True(File.Exists(LocalPath + "Characters.csv"));
        }

    }

}

