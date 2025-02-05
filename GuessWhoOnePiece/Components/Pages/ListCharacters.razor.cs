﻿// <copyright file="ListCharacters.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Components.Pages;

public partial class ListCharacters : ComponentBase
{
    private List<Character> _characters = [];
    
    private List<string> _characterNames;

    private bool isLoading;
    private string SearchText { get; set; }

    protected override async Task OnInitializedAsync()
    {
        var thread = new Thread(async () =>
        {
            isLoading = true;
            await InvokeAsync(StateHasChanged);
            _characterNames ??= [];
            _characters = await ReceiveDataCsv.ReceiveAllCharacters(FileServiceReader);
            foreach (var character in _characters)
                _characterNames.Add(character.Name);

            isLoading = false;
            await InvokeAsync(StateHasChanged);
        });

        thread.Start();
    }

    private async void OnSelectedCharacter(string characterName)
    {
        var character = await ReceiveDataCsv.ReceiveCharacter(characterName, FileServiceReader);
    }

    private async Task OnInput(ChangeEventArgs args)
    {
        SearchText = args.Value.ToString();
        await FilterItems();
    }

    private async Task OnKeyPressed(KeyboardEventArgs args)
    {
        // Empty on purpose.
    }
    
    private async Task FilterItems()
    {
        _characters.Clear();
        if (string.IsNullOrEmpty(SearchText))
        {
            _characters = await ReceiveDataCsv.ReceiveAllCharacters(FileServiceReader);
        }
        else
        {
            foreach (var character in _characterNames.Where(character => character.Contains(SearchText, StringComparison.InvariantCultureIgnoreCase)).ToList())
            {
                _characters.Add(await ReceiveDataCsv.ReceiveCharacter(character, FileServiceReader));
            }
        }
    }
}
