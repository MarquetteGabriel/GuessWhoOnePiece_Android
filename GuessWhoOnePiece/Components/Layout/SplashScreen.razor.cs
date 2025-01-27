using System.Threading.Tasks;
using GuessWhoOnePiece.Services;
using Microsoft.Maui.Storage;

namespace GuessWhoOnePiece.Components.Layout
{
    public partial class SplashScreen
    {
        internal bool Loading;
        private int ProgressValue;
        private bool HasStarted = false;
        protected override async Task OnAfterRenderAsync(bool firstRender)
        {
            if (!firstRender || HasStarted)
                return;

            HasStarted = true;
            StartLoadingBar();
        }

        protected override Task OnInitializedAsync()
        {
            if (!Preferences.Get("Updated", false))
                LoadingService.Loading = true;
            else
            {
                LoadingService.Loading = false;
                Navigation.NavigateTo("/home");
            }

            return base.OnInitializedAsync();
        }

        private void StartLoadingBar()
        {
            ProgressValue = 0;

            while (ProgressValue < 100)
            {
                ProgressValue = ControlRoomService.GetProgress();
                if (ProgressValue >= 100)
                    ProgressValue = 100;

                StateHasChanged();
            }
        }
    }
}
