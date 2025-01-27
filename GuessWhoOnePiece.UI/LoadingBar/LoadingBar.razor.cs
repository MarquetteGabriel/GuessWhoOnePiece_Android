// <copyright file="LoadingBar.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.UI.LoadingBar
{
    public partial class LoadingBar : ComponentBase
    {
        [Parameter] public int ProgressPercentage { get; set; }
    }
}
