using GuessWhoOnePiece.Model.Characters;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements;

public partial class ItemList : ComponentBase
{
    [Parameter] public Character Character { get; set; }
}