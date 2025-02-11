// <copyright file="ListCharacters.razor.cs">
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
    private List<InfoCharacter> _characters = [];
    private Dictionary<char, List<InfoCharacter>> _characterGroups = new();

    private List<string> _characterNames;

    private bool isLoading;
    private string SearchText { get; set; }

    private readonly Dictionary<char, bool> _menuStates = new();

    protected override async Task OnInitializedAsync()
    {
        var thread = new Thread(async () =>
        {
            isLoading = true;
            await InvokeAsync(StateHasChanged);
            _characterNames ??= [];
            _characters = await ReceiveDataCsv.ReceiveCharacterInfoList(FileServiceReader);
            _characterNames = _characters.Select(character => character.Name).ToList();
            CreateCharactersGroup(_characters);
            isLoading = false;
            await InvokeAsync(StateHasChanged);
        });

        thread.Start();
    }

    private async void OnSelectedCharacter(string characterName)
    {
        CharacterService.CurrentCharacter = await ReceiveDataCsv.ReceiveCharacter(characterName, FileServiceReader);
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
            _characters = await ReceiveDataCsv.ReceiveCharacterInfoList(FileServiceReader);
            CreateCharactersGroup(_characters);
        }
        else
        {
            isLoading = true;
            foreach (var character in _characterNames.Where(character => character.Contains(SearchText, StringComparison.InvariantCultureIgnoreCase)).ToList())
            {
                _characters.Add(await ReceiveDataCsv.ReceiveCharacterInfo(character, FileServiceReader));
            }
            isLoading = false;
            _characters = _characters.OrderBy(character => character.Name).ToList();
            CreateCharactersGroup(_characters);
        }
    }

    private void ToggleMenu(char key)
    {
        foreach (var keys in _menuStates.Keys.Where(keys => !keys.Equals(key)))
        {
            _menuStates[keys] = false;
        }
        _menuStates[key] = !_menuStates[key];
    }

    private bool IsMenuUnfold(char key)
    {
        return _menuStates[key];
    }

    private void CreateCharactersGroup(List<InfoCharacter> _characters)
    {
        _characterGroups = _characters.GroupBy(c => char.ToUpper(c.Name[0])).ToDictionary(g => g.Key, g => g.ToList());
        foreach (var key in _characterGroups.Keys)
        {
            _menuStates[key] = false;
        }
    }
}
