// <copyright file="TabBar.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Layout;

public partial class TabBar : ComponentBase
{
    private int Index { get; set; } = 3;

    private void ChangeActiveState(int index)
    {
        Index = index;
    }
}