using GuessWhoOnePiece.Model;
using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.Answer
{
    public partial class Answer : ComponentBase
    {
        [Parameter] public string? AnswerText { get; set; }
        
        [Parameter] public string? AnswerImage { get; set; }
        [Parameter] public AnswerState? AnswerState { get; set; }

        private string DefineColor()
        {
            return AnswerState switch
            {
                Model.AnswerState.Correct => "answer-correct",
                Model.AnswerState.Wrong => "answer-wrong",
                Model.AnswerState.NotAnswered => "answer-not-answered",
                _ => "answer-no-answer"
            };
        }
    }
}

