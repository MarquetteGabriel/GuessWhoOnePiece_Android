// <copyright file="ControlRoomService.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.DataEntries;
using Microsoft.Maui.Storage;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Services
{
    public static class ControlRoomService
    {
        private static ControlRoom controlRoom = new ControlRoom();

        public static async Task GetCharactersData()
        {
            await controlRoom.GenerateThreads().ConfigureAwait(false);
            Preferences.Set("Updated", true);
        }

        public static int GetProgress()
        {
            int maxValue = controlRoom.GetCharacterCount();
            while (maxValue == 0)
                return maxValue;

            int Oldvalue = controlRoom.GetCountPercentage();
            int percentageValue = (int)((double)Oldvalue / maxValue * 70);

            if (Oldvalue < maxValue)
                return percentageValue;

            int value = Popularity.GetCountPopularity();
            percentageValue = value % Oldvalue * 30 + 70;

            return percentageValue;
        }
    }
}
