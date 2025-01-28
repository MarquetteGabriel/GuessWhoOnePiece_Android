// <copyright file="Home.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.CsvManager;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Components.Pages
{
    public partial class Home
    {
        private string _value = string.Empty;

        private void OnClickChargement()
        {
            // Not used now.
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
