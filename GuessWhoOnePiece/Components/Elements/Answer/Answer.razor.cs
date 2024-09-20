using GuessWhoOnePiece.Model;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.Answer
{
    public partial class Answer : ComponentBase
    {
        [Parameter] public string? AnswerText { get; set; }
        
        [Parameter] public string? AnswerImage { get; set; }
        [Parameter] public AnswerState? AnswerState { get; set; }

        private void DefineColor()
        {
            switch (AnswerState)
            {
                case Model.AnswerState.Correct:
                case Model.AnswerState.Wrong:
                case Model.AnswerState.NotAnswered:
                default:
                    break;
            }
        }
    }
}

