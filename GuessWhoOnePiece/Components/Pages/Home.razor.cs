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
        private string _value = string.Empty;

        private void OnClickChargement()
        {

        }

        public async Task OnShowCharacter()
        {
            var characters = await ReceiveDataCsv.ReceiveAllCharacters();
            foreach (var character in characters)
                {
                _value += character.Name + " ";
            }
        }
    }
}
