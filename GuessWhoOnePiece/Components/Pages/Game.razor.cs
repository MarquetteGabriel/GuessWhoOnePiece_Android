using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.ViewModel;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Pages;

public partial class Game : ComponentBase
{
    public GameViewModel _gameViewModel = new();

    private async void OnCharacterClicked(string characterName)
    {  
         var character = await ReceiveDataCsv.ReceiveCharacter(characterName);
         _gameViewModel.GetJudgmentDay(character);
         _gameViewModel.AddAnswer(character);
        StateHasChanged();
    }
}