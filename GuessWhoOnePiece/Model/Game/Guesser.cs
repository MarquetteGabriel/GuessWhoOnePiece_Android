using GuessWhoOnePiece.Model.Characters;
using GuessWhoOnePiece.Model.CsvManager;

namespace GuessWhoOnePiece.Model.Game
{
    public static class Guesser
    {
        public static async Task<Character> SetCharacterToFind()
        {
            var listCharacters = await ReceiveDataCsv.ReceiveAllCharacters();
            var index = RandomizeNumber(listCharacters.Count);
            return listCharacters[index];
        }

        private static int RandomizeNumber(int maxNumber)
        {
            var random = new Random();
            return random.Next(maxNumber);
        }
    }
}

