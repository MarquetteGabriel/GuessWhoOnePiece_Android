using System;

namespace GuessWhoOnePiece.Services
{
    public class LoadingService
    {
        public event Action? OnChange;
        private bool _loading;

        public bool Loading
        {
            get => _loading;
            set
            {
                if (_loading != value)
                {
                    _loading = value;
                    NotifyStateChanged();
                }
            }
        }

        private void NotifyStateChanged() => OnChange?.Invoke();
    }
}
