using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.Game
{
    public class JudgementAnswer(Character character)
    {
        public bool IsAlive(Character guessedCharacter) => guessedCharacter.Alive == character.Alive;
        
        public bool HasEatenDevilFruit(Character guessedCharacter) => guessedCharacter.DevilFruit == character.DevilFruit;
        
        public BountyType WantedBounty(Character guessedCharacter) => BountyFac.WhatBounty(guessedCharacter, character);
        
        public bool IsSameName(Character guessedCharacter) => guessedCharacter.Name.Equals(character.Name);
        
        public bool WhatOccupation(Character guessedCharacter) => guessedCharacter.Type.Equals(character.Type);
        
        public bool WhatCrew(Character guessedCharacter) => guessedCharacter.Crew.Equals(character.Crew);

        public ChapterType IsNewer(Character guessedCharacter)
        {
            var diff = guessedCharacter.FirstAppearance - character.FirstAppearance;
            return diff switch
            {
                < 0 => ChapterType.NewerChapter, // Arrow up
                > 0 => ChapterType.PreviousChapter, // Arrow down
                _ => ChapterType.SameChapter // Make it green
            };
        }

        public AgeType IsOlder(Character guessedCharacter)
        {
            var diff = guessedCharacter.Age - character.Age;
            return diff switch
            {
                < 0 => AgeType.Younger, // Arrow Up
                > 0 => AgeType.Younger, // Arrow down
                _ => AgeType.Equal // Make it green
            };
        }
    }
}

