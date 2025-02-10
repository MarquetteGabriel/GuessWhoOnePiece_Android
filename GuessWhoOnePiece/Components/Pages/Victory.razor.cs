using GuessWhoOnePiece.Components.Layout;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Services;
using Microsoft.AspNetCore.Components;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Components.Pages
{
    public partial class Victory : ComponentBase
    {
        private Character? Character { get; set; }
        [CascadingParameter(Name = "MainLayout")] private MainLayout? MainLayout { get; set; }

        protected override void OnInitialized()
        {
            Character = CurrentCharacterService.CurrentCharacter;
            StateHasChanged();
        }

        private void OnHome()
        {
            MainLayout!.GetTabBarRef().ChangeActiveState(3);
            Navigation.NavigateTo("/home");
        }

        private void OnPlayAgain()
        {
            MainLayout!.GetTabBarRef().ChangeActiveState(2);
            Navigation.NavigateTo("/game");
        }
    }
}
