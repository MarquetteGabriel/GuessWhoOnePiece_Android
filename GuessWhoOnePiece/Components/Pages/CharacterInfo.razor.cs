// <copyright file="CharacterInfo.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Pages;

public partial class CharacterInfo : ComponentBase
{
    [Parameter] public Character? Character {get; set; }

    protected override void OnParametersSet()
    {
        Character = CurrentCharacterService.CurrentCharacter;
    }
}
