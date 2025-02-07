using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Services;
using Microsoft.AspNetCore.Components;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Components.Pages
{
    public partial class Victory : ComponentBase
    {
        private Character? Character { get; set; }

        protected override async Task OnInitializedAsync()
        {
            Character = CurrentCharacterService.CurrentCharacter;
            StateHasChanged();
        }

        private void OnHome()
        {
            IndexMenuService.IndexMenu = 3;
            StateHasChanged();
            Navigation.NavigateTo("/home");
        }

        private void OnPlayAgain()
        {
            IndexMenuService.IndexMenu = 2;
            StateHasChanged();
            Navigation.NavigateTo($"/game");
        }
    }
}
