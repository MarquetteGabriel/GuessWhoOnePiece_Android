// <copyright file="TabBar.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Layout;

public partial class TabBar : ComponentBase
{
    private void ChangeActiveState(int index)
    {
        IndexMenuService.IndexMenu = index;
    }
}
