// <copyright file="App.xaml.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Services;
using Microsoft.Maui.Controls;
using Microsoft.Maui.Storage;
using System.Threading.Tasks;

namespace GuessWhoOnePiece
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            MainPage = new MainPage();
        }

        protected override void OnStart()   
        {
            if(!Preferences.Get("Updated", false))
            {
                Task.Run(() => ControlRoomService.GetCharactersData());
            }

            base.OnStart();
        }
    }
}
