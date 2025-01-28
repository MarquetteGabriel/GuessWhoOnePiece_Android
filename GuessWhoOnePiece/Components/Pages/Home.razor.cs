// <copyright file="Home.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.Model.DataEntries;
using System;
using System.Diagnostics;
using System.Threading;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Components.Pages
{
    public partial class Home
    {
        readonly ControlRoom _controlRoom = new ControlRoom(null);
        private string _value = string.Empty;

        private void OnClickChargement()
        {
            var thread = new Thread(async () =>
            {
                await InvokeAsync(StateHasChanged);
                var stopwatch = new Stopwatch();
                stopwatch.Start();
                await _controlRoom.GenerateThreads();
                stopwatch.Stop();
                await InvokeAsync(StateHasChanged);
            });

            thread.Start();
        }

        public async Task OnShowCharacter()
        {
            var characters = await ReceiveDataCsv.ReceiveAllCharacters();
            foreach (var character in characters)
                {
                _value += character.Name + " ";
            }

            var stopwatch = new Stopwatch();

            // var character = "Monkey D. Luffy";

            stopwatch.Start();
            // var characters1 = await _controlRoom.DataForCharacter(ControlRoom.SetCharacterLink(character), character);
            // await _controlRoom.GenerateThreads();
            // await CharacterNameListManager.ReceivedCharactersList();
            // var charc = _controlRoom._characterNameList;
            stopwatch.Stop();
            Console.WriteLine($"Method Global: {stopwatch.ElapsedMilliseconds} ms");
            
        }
    }
}
