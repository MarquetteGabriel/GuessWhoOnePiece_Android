using GuessWhoOnePiece.Model.Characters;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements;

public partial class ItemList(Character character) : ComponentBase
{
    [Parameter] public Character Character { get; set; } = character;
}