using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.Answer
{
    public partial class Answer : ComponentBase
    {
        [Parameter] public string? AnswerText { get; set; }
        
        // Correct, Wrong, Not Answered
        [Parameter] public AnswerState? AnswerState { get; set; }

        private void DefineColor()
        {
            switch (AnswerState)
            {
                case Elements.Answer.AnswerState.Correct:
                case Elements.Answer.AnswerState.Wrong:
                case Elements.Answer.AnswerState.NotAnswered:
                default:
                    break;
            }
        }
    }

    public enum AnswerState
    {
        Correct = 0, 
        Wrong = 1, 
        NotAnswered = 2,
    }
}

