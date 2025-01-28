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
        public App()
        {
            InitializeComponent();

            MainPage = new MainPage();
        }
    }
}
