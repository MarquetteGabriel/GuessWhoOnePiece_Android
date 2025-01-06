// <copyright file="ItemList.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.ItemList;

public partial class ItemList : ComponentBase
{
    [Parameter] public Character Character { get; set; }
}