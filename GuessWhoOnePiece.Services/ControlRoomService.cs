using System;

namespace GuessWhoOnePiece.Services
{
    public class ControlRoomService
    {
        public event EventHandler? OnChange;
        private static int _countPercentage;

        public int CountPercentage
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

        private void NotifyStateChanged() => OnChange?.Invoke(null, EventArgs.Empty);
    }
}
