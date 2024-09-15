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