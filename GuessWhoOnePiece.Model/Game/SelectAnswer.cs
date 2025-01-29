using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.Game
{
    public class SelectAnswer(Character charcaterSearched)
    {
        private readonly JudgementAnswer _judgementAnswer = new(charcaterSearched);

        internal Character SelectAnswerCharacter(Character character)
        {
            character = SelectAnswerAlive(character);
            character = SelectAnswerAge(character);
            character = SelectAnswerType(character);
            character = SelectAnswerChapter(character);
            character = SelectAnswerBounty(character);
            character = SelectAnswerCrew(character);
            character = SelectAnswerName(character);
            character = SelectAnswerDevilFruit(character);
            return character;
        }

        private Character SelectAnswerAlive(Character character)
        {
            var answerAlive = _judgementAnswer.IsAlive(character);
            character.AnswerStateList!.Alive = AnswerResult.SetAnswerStateBoolean(answerAlive);
            character.AnswerImageLink!.Alive = DefinePictures.SetAlivePicture(answerAlive);
            return character;
        }

        private Character SelectAnswerAge(Character character)
        {
            var answerAge = _judgementAnswer.IsOlder(character);
            character.AnswerStateList!.Age = AnswerResult.SetAnswerStateAgeType(answerAge);
            character.AnswerImageLink!.Age = DefinePictures.SetAgePicture(answerAge);
            return character;
        }

        private Character SelectAnswerType(Character character)
        {
            var answerType = _judgementAnswer.WhatOccupation(character);
            character.AnswerStateList!.Type = AnswerResult.SetAnswerStateBoolean(answerType);
            character.AnswerImageLink!.Type = DefinePictures.SetTypePicture(character.Type);
            return character;
        }

        private Character SelectAnswerChapter(Character character)
        {
            var answerChapter = _judgementAnswer.IsNewer(character);
            character.AnswerStateList!.FirstAppearance = AnswerResult.SetAnswerStateChapterType(answerChapter);
            character.AnswerImageLink!.FirstAppearance = DefinePictures.SetChapterPicture(answerChapter);
            return character;
        }

        private Character SelectAnswerBounty(Character character)
        {
            var answerBounty = _judgementAnswer.WantedBounty(character);
            character.AnswerStateList!.Bounty = AnswerResult.SetAnswerStateBountyType(answerBounty);
            character.AnswerImageLink!.Bounty = DefinePictures.SetBountyPicture(answerBounty);
            return character;
        }

        private Character SelectAnswerCrew(Character character)
        {
            var answerCrew = _judgementAnswer.WhatCrew(character);
            character.AnswerStateList!.Crew = AnswerResult.SetAnswerStateBoolean(answerCrew);
            character.AnswerImageLink!.Crew = DefinePictures.SetCrewPictures(character.Crew);
            return character;
        }

        private Character SelectAnswerName(Character character)
        {
            var answerName = _judgementAnswer.IsSameName(character);
            character.AnswerStateList!.Name = AnswerResult.SetAnswerStateBoolean(answerName);
            character.AnswerImageLink!.Name = string.Empty;
            return character;
        }

        private Character SelectAnswerDevilFruit(Character character)
        {
            var answerDevilFruit = _judgementAnswer.HasEatenDevilFruit(character);
            character.AnswerStateList!.DevilFruit = AnswerResult.SetAnswerStateBoolean(answerDevilFruit);
            character.AnswerImageLink!.DevilFruit = DefinePictures.SetDevilFruitPicture(answerDevilFruit);
            return character;
        }
    }
}
