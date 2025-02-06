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
    }
}
