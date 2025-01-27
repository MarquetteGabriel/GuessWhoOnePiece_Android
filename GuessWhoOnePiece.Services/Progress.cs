using GuessWhoOnePiece.Model.DataEntries;
using Microsoft.Maui.Storage;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Services
{
    public class Progress
    {
        private const int MAX_PROGRESS_DATA = 70;
        private const int MAX_PROGRESS_POPULARITY = 30;
        private readonly static ControlRoom controlRoom = new();

        public static async Task GetCharactersData()
        {
            await controlRoom.GenerateThreads().ConfigureAwait(false);
            Preferences.Set("Updated", true);
        }

        public static void GetProgress()
        {
            int maxValue = controlRoom.CharacterCount;
            if (maxValue == 0)
            {
                ControlRoomService.CountPercentage = maxValue;
                return;
            }
                

            int Oldvalue = controlRoom.CountPercentage;
            int percentageValue = (int)((double)Oldvalue / maxValue * MAX_PROGRESS_DATA);

            if (Oldvalue < maxValue)
            {
                ControlRoomService.CountPercentage = percentageValue;
                return;
            }
                

            int value = Popularity.CountPopularity;
            percentageValue = value % Oldvalue * MAX_PROGRESS_POPULARITY + MAX_PROGRESS_DATA;

            ControlRoomService.CountPercentage = percentageValue;
        }
    }
}
