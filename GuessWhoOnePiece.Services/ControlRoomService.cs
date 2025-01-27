using System;

namespace GuessWhoOnePiece.Services
{
    public class ControlRoomService
    {
        public static event Action? OnChange;
        private static int _countPercentage;

        public static int CountPercentage
        {
            get => _countPercentage;
            set
            {
                if (_countPercentage != value)
                {
                    _countPercentage = value;
                    NotifyStateChanged();
                }
            }
        }

        private static void NotifyStateChanged() => OnChange?.Invoke();
    }
}
