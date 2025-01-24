// <copyright file="BackButton.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.JSInterop;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.UI.AnswerCard;

public partial class AnswerCard : ComponentBase
{
    [Parameter] public string? AnswerText { get; set; }
    [Parameter] public string? AnswerImage { get; set; }
    [Parameter] public AnswerState? AnswerState { get; set; }
    
    protected override async Task OnAfterRenderAsync(bool firstRender)
    {
        // Switch to invoke JS. 
        if (firstRender)
            return;
        try
        {
            var module = await JS.InvokeAsync<IJSObjectReference>("import", "../GuessWhoOnePiece.UI/AnswerCard/AnswerCard.razor.js");
            await module.InvokeVoidAsync("adjustFontSize");
        }
        catch (JSException ex)
        {
            var result = ex.Message;
            return;
        }

    }


    private string DefineColor()
    {
        if (!string.IsNullOrEmpty(AnswerImage))
            return "answer-no-answer";

        return AnswerState switch
        {
            Model.AnswerState.Correct => "answer-correct",
            Model.AnswerState.Wrong => "answer-wrong",
            Model.AnswerState.NotAnswered => "answer-not-answered",
            _ => "answer-no-answer"
        };
    }
}
