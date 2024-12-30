using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using GuessWhoOnePiece.Model;
using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;
using GuessWhoOnePiece.Model.Game;

namespace GuessWhoOnePiece.ViewModel
{
    public class GameViewModel : INotifyPropertyChanged
    {
        public readonly ObservableCollection<Character> AnswersList = [];
        
        public List<string> CharacterNameList = [];

        private Character _characterToFind;
        private JudgementAnswer _judgementAnswer;

        public GameViewModel()
        {
            _ = DefineCharacter();
            SetCharacterNames();
            _judgementAnswer = new JudgementAnswer(_characterToFind!);
        }

        private async Task DefineCharacter()
        {
            _characterToFind = await Guesser.SetCharacterToFind();
        }


        public void GetJudgmentDay(Character character)
        {
            _judgementAnswer.SetCharacter(_characterToFind);
            
            var answerAlive = _judgementAnswer.IsAlive(character);
            var answerChapter = _judgementAnswer.IsNewer(character);
            var answerAge = _judgementAnswer.IsOlder(character);
            var answerType = _judgementAnswer.WhatOccupation(character);
            var answerBounty = _judgementAnswer.WantedBounty(character);
            var answerCrew = _judgementAnswer.WhatCrew(character);
            var answerName = _judgementAnswer.IsSameName(character);
            var answerDevilFruit = _judgementAnswer.HasEatenDevilFruit(character);

            if (character.AnswerStateList == null) return;
            
            character.AnswerStateList.Alive = AnswerResult.SetAnswerStateBoolean(answerAlive);
            character.AnswerStateList.FirstAppearance = AnswerResult.SetAnswerStateChapterType(answerChapter);
            character.AnswerStateList.Age = AnswerResult.SetAnswerStateAgeType(answerAge);
            character.AnswerStateList.Type = AnswerResult.SetAnswerStateBoolean(answerType);
            character.AnswerStateList.Bounty = AnswerResult.SetAnswerStateBountyType(answerBounty);
            character.AnswerStateList.Crew = AnswerResult.SetAnswerStateBoolean(answerCrew);
            character.AnswerStateList.Name = AnswerResult.SetAnswerStateBoolean(answerName);
            character.AnswerStateList.DevilFruit = AnswerResult.SetAnswerStateBoolean(answerDevilFruit);
            
            if (character.AnswerImageLink == null) return;
            
            character.AnswerImageLink.Age = DefinePictures.SetAgePicture(answerAge);
            character.AnswerImageLink.DevilFruit = DefinePictures.SetDevilFruitPicture(answerDevilFruit);
            character.AnswerImageLink.FirstAppearance = DefinePictures.SetChapterPicture(answerChapter);
            character.AnswerImageLink.Alive = DefinePictures.SetAlivePicture(answerAlive);
            character.AnswerImageLink.Type = DefinePictures.SetTypePicture(character.Type);
            character.AnswerImageLink.Bounty = DefinePictures.SetBountyPicture(answerBounty);
            character.AnswerImageLink.Name = string.Empty;
            character.AnswerImageLink.Crew = DefinePictures.SetCrewPictures(character.Crew);
        }
        
        public void AddAnswer(Character character)
        {
            AnswersList.Add(character);
            OnPropertyChanged();
        }

        private async void SetCharacterNames()
        {
            var characterlist = await ReceiveDataCsv.ReceiveAllCharacters();
            foreach (var character in characterlist.Where(character => character != null))
            {
                CharacterNameList.Add(character.Name);
            }
        }

        private void PlayAgain()
        {
            _ = DefineCharacter();
            _judgementAnswer.SetCharacter(_characterToFind);
            AnswersList.Clear();
        }

        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string? propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}

