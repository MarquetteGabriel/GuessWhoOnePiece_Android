// <copyright file="Pictures.razor.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using Microsoft.AspNetCore.Components;
using Microsoft.Maui.Storage;
using System;
using System.IO;
using System.Threading.Tasks;

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
