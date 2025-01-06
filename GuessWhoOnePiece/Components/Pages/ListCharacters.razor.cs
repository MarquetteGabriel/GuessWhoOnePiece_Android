// <copyright file="ListCharacters.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.ViewModel;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;

namespace GuessWhoOnePiece.Components.Pages;

public partial class ListCharacters : ComponentBase
{
   /* private readonly List<Character> _characters = new List<Character>
    {
        new Character("Katakuri", false, "",
            0, "", false, 48, "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEMfXMi4Z4XqroQx_0UKomNs2Hx_aAz12KMhYZGLmdy6z9c7Iy_I8kycSL0nKaozBes0E&usqp=CAU", 0),
*/
    private List<Character> _characters = [];
    
    private List<string> _characterNames;

    private string SearchText { get; set; }

    protected override async Task OnInitializedAsync()
    {
        _characterNames ??= [];
        _characters = await ReceiveDataCsv.ReceiveAllCharacters();
        foreach (var character in _characters)
            _characterNames.Add(character.Name);
    }

    private async void OnSelectedCharacter(string characterName)
    {
        var character = await ReceiveDataCsv.ReceiveCharacter(characterName);
    }

    private async Task OnInputAsync(ChangeEventArgs args)
    {
        SearchText = args.Value.ToString();
        await FilterItems();
    }

    private async Task OnKeyPressed(KeyboardEventArgs args)
    {
        
    }
    
    private async Task FilterItems()
    {
        _characters.Clear();
        if (string.IsNullOrEmpty(SearchText))
        {
            _characters = await ReceiveDataCsv.ReceiveAllCharacters();
        }
        else
        {
            foreach (var character in _characterNames.Where(character => character.Contains(SearchText, StringComparison.InvariantCultureIgnoreCase)).ToList())
            {
                _characters.Add(await ReceiveDataCsv.ReceiveCharacter(character));
            }
        }
    }
}