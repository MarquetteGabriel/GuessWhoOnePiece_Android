// <copyright file="Game.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.ViewModel;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Pages;

public partial class Game : ComponentBase
{
    private GameViewModel? _gameViewModel;
    private bool isVictory;

    public Game()
    {
        FileServiceReader ??= new FileServiceReader();
        _gameViewModel = new GameViewModel(FileServiceReader);
    }

    private async void OnCharacterClicked(string characterName)
    {
        var character = await ReceiveDataCsv.ReceiveCharacter(characterName, FileServiceReader);
        isVictory = _gameViewModel!.GetJudgmentDay(character);
        StateHasChanged();

        if (isVictory)
        {
            CurrentCharacterService.CurrentCharacter = character;
            Navigation.NavigateTo("/victory");
        }
    }
}
