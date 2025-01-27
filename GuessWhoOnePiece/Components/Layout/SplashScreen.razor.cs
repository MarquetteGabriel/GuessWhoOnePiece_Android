// <copyright file="SplashScreen.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using System.Threading.Tasks;
using GuessWhoOnePiece.Services;
using Microsoft.Maui.Storage;

namespace GuessWhoOnePiece.Components.Layout
{
    public partial class SplashScreen
    {
        internal bool Loading;
        private int ProgressValue;
        private bool HasStarted;
        private const int MAX_PROGRESS = 100;
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

            while (ProgressValue < MAX_PROGRESS)
            {
                ProgressValue = ControlRoomService.CountPercentage;
                if (ProgressValue >= MAX_PROGRESS)
                    ProgressValue = MAX_PROGRESS;

                StateHasChanged();
            }
        }
    }
}
