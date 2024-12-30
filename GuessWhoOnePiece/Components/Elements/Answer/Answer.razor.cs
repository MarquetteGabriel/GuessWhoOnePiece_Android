using GuessWhoOnePiece.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.JSInterop;
using System.Diagnostics;
using System.Reflection;

namespace GuessWhoOnePiece.Components.Elements.Answer
{
    public partial class Answer : ComponentBase
    {

        [Inject] private IJSRuntime JsRuntime { get; set; }
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

        protected override async Task OnAfterRenderAsync(bool firstRender)
        {
            if (firstRender)
            {
                await LoadAndExecuteJavaScript();
            }
        }

        private async Task LoadAndExecuteJavaScript()
        {
            var assembly = Assembly.GetExecutingAssembly();
            var resourceName = "GuessWhoOnePiece.Components.Elements.Answer.Answer.razor.js";

            using (var stream = assembly.GetManifestResourceStream(resourceName))
            using (var reader = new StreamReader(stream))
            {
                var script = await reader.ReadToEndAsync();
                await JS.InvokeVoidAsync("eval", script);
                await Task.Delay(100);
                await JsRuntime.InvokeVoidAsync("Answer.adjustFontSize");
            }
        }
    }
}

