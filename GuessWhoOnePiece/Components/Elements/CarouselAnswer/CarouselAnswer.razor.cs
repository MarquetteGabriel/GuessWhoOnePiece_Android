using GuessWhoOnePiece.Model.Characters;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.CarouselAnswer;

public partial class CarouselAnswer : ComponentBase
{
    [Parameter] public Character? Character { get; set; }
}