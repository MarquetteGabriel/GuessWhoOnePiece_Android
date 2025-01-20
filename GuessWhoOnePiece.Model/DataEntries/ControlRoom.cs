// <copyright file="ControlRoom.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represents the control of threads for web call.</summary>
    public partial class ControlRoom
    {
        public IReadOnlyList<string> _characterNameList = new List<string>();
        /// <summary>Generate threads to get data.</summary>
        /// <returns>The complete list of characters.</returns>
        public async Task<List<Character>> GenerateThreads()
        {
            _characterNameList = await CharacterNameListManager.ReceivedCharactersList();

            var charactersList = new ConcurrentBag<Character>();
            
            await Parallel.ForEachAsync(_characterNameList, new ParallelOptions { MaxDegreeOfParallelism = Environment.ProcessorCount * 2 }, async (characterName, _) =>
            {
                var character = await DataForCharacter(SetCharacterLink(characterName), characterName);
                if (character != null)
                    charactersList.Add(character);
            });

            charactersList = Popularity.SetPopularity(_characterNameList.ToList(), charactersList);
            ManageCsv.SaveCharactersToCsv(charactersList.ToList());

            return charactersList.ToList();
        }

        /// <summary>Set the link to find a character on the fandom.</summary>
        /// <param name="characterName">The character to find.</param>
        /// <returns>The link of the character.</returns>
        public static Uri SetCharacterLink(string characterName)
        {
            var urlCharacter = characterName.Replace(" ", "_", StringComparison.OrdinalIgnoreCase).Trim();
            var matcher = Regexs.CharacterLinkRegex().Match(urlCharacter);
            if (matcher.Success)
            {
                urlCharacter = matcher.Groups[1].Value;
            }

            const string urlFandom = "https://onepiece.fandom.com/fr/wiki/";
            return new Uri(urlFandom + urlCharacter);
        }
    }
}

