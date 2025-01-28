// <copyright file="App.xaml.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.DataEntries;
using GuessWhoOnePiece.Services;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Maui.Controls;
using Microsoft.Maui.Storage;
using System;
using System.Threading.Tasks;

namespace GuessWhoOnePiece
{
    public partial class App : Application
    {
        private ControlRoomService _controlRoomServices;

        public App(IServiceProvider serviceProvider)
        {
            _controlRoomServices = serviceProvider.GetRequiredService<ControlRoomService>();
            InitializeComponent();

            MainPage = new MainPage();
        }

        protected override void OnStart()   
        {
            if(!Preferences.Get("Updated", false))
            {
                ControlRoom controlRoom = new(_controlRoomServices);
                Task.Run( async () => await controlRoom.GenerateThreads());
                Task.Run(() => controlRoom.GetPercentage());
            }

            base.OnStart();
        }
    }
}
