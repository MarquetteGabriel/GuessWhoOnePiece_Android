using System;

namespace GuessWhoOnePiece.Services
{
    public class IndexMenuService
    {
        public event EventHandler? OnChange;
        private int _index;

        public int IndexMenu
        {
            get => _index;
            set
            {
                if (_index != value)
                {
                    _index = value;
                    NotifyStateChanged();
                }
            }
        }

        private void NotifyStateChanged() => OnChange?.Invoke(this, EventArgs.Empty);

    }
}
