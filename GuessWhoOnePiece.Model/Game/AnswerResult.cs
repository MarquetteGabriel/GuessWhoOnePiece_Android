﻿// <copyright file="AnswerResult.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

namespace GuessWhoOnePiece.Model.Game;

public static class AnswerResult
{
    public static AnswerState SetAnswerStateBoolean(bool value)
    {
        return value ? AnswerState.Correct : AnswerState.Wrong;
    }

    public static AnswerState SetAnswerStateChapterType(ChapterType value)
    {
        return value switch
        {
            ChapterType.SameChapter => AnswerState.Correct,
            _ => AnswerState.Wrong,
        };
    }

    public static AnswerState SetAnswerStateBountyType(BountyType value)
    {
        return value switch
        {
            BountyType.Equal => AnswerState.Correct,
            BountyType.WrongUnknown => AnswerState.NotAnswered,
            _ => AnswerState.Wrong,
        };
    }
        
    public static AnswerState SetAnswerStateAgeType(AgeType value)
    {
        return value switch
        {
            AgeType.Equal => AnswerState.Correct,
            _ => AnswerState.Wrong,
        };
    }
}
