// <copyright file="AnswerResult.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

namespace GuessWhoOnePiece.Model.Game;

public static class AnswerResult
{
    internal static AnswerState SetAnswerStateBoolean(bool value)
    {
        return value ? AnswerState.Correct : AnswerState.Wrong;
    }

    internal static AnswerState SetAnswerStateChapterType(ChapterType value)
    {
        return value switch
        {
            ChapterType.PreviousChapter => AnswerState.Wrong,
            ChapterType.NewerChapter => AnswerState.Wrong,
            ChapterType.SameChapter => AnswerState.Correct,
            _ => AnswerState.NotAnswered,
        };
    }

    internal static AnswerState SetAnswerStateBountyType(BountyType value)
    {
        return value switch
        {
            BountyType.Lower => AnswerState.Wrong,
            BountyType.Upper => AnswerState.Wrong,
            BountyType.Equal => AnswerState.Correct,
            BountyType.WrongUnknown => AnswerState.NotAnswered,
            _ => AnswerState.NotAnswered,
        };
    }
        
    internal static AnswerState SetAnswerStateAgeType(AgeType value)
    {
        return value switch
        {
            AgeType.Younger => AnswerState.Wrong,
            AgeType.Equal => AnswerState.Correct,
            AgeType.Older => AnswerState.Wrong,
            _ => AnswerState.NotAnswered,
        };
    }
}
