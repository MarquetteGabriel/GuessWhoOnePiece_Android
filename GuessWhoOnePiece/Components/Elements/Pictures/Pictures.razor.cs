using Microsoft.AspNetCore.Components;

namespace GuessWhoOnePiece.Components.Elements.Pictures
{
    public partial class Pictures
    {
        [Parameter] public required string PicturePath { get; set; }

        private string? PathPicture;

        protected override async Task OnInitializedAsync()
        {
            if (string.IsNullOrEmpty(PicturePath))
                return;

            var filePath = Path.Combine(FileSystem.Current.AppDataDirectory, PicturePath);
            var fileBytes = await File.ReadAllBytesAsync(filePath);
            var base64string = Convert.ToBase64String(fileBytes);
            PathPicture = $"data:image/png;base64, {base64string}";
        }
    }
}
