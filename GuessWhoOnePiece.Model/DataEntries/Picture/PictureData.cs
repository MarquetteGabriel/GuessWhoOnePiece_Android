// <copyright file="PictureData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using HtmlAgilityPack;
using System;
using System.Linq;
using System.Net;

namespace GuessWhoOnePiece.Model.DataEntries.Picture
{
    public static partial class PictureManager
    {
        private const string MangaPostEllipse = "Manga_Post_Ellipse";
        private const string MangaPreEllipse = "Manga_Pre_Ellipse";
        private const string AnimePostEllipse = "Anime_Post_Ellipse";
        private const string AnimePreEllipse = "Anime_Pre_Ellipse";
        private const string AnimeInfobox = "Anime_Infobox";

        private const string Amp = "amp;";
        private const string Href = "href";
        private const string AmpAnd = "&amp";
        private const string Esperluette = "&";

        /// <summary>Gets the link of the image for a character.</summary>
        /// <param name="listOfPictures">List of picture in the web page.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The link of the image for the character.</returns>
        internal static string GetPictureLink(HtmlNodeCollection listOfPictures, string characterName)
        {
            foreach (var picture in listOfPictures.Select(picture => picture.GetAttributeValue(Href, string.Empty)))
            {
                var newPicture = picture.Replace(Amp, string.Empty, StringComparison.OrdinalIgnoreCase).Replace(AmpAnd, Esperluette, StringComparison.OrdinalIgnoreCase);
                if (IsSpecialPicture(newPicture))
                    return newPicture;

                if (newPicture.Contains(characterName))
                    return newPicture;

                if (ExtractPictureHtmlWebDecode(characterName, newPicture) != null)
                    return newPicture;
            }

            return string.Empty;
        }

        private static bool IsSpecialPicture(string picture) =>
            picture.Contains(MangaPostEllipse, StringComparison.OrdinalIgnoreCase) || picture.Contains(MangaPreEllipse, StringComparison.OrdinalIgnoreCase) ||
            picture.Contains(AnimePostEllipse, StringComparison.OrdinalIgnoreCase) || picture.Contains(AnimePreEllipse, StringComparison.OrdinalIgnoreCase) ||
            picture.Contains(AnimeInfobox, StringComparison.OrdinalIgnoreCase);

        private static string? ExtractPictureHtmlWebDecode(string characterName, string picture)
        {
            characterName = characterName.Replace("/", "", StringComparison.OrdinalIgnoreCase);
            var pictureName = characterName.Split(" ").FirstOrDefault(character => picture.Contains(WebUtility.UrlEncode(character), StringComparison.OrdinalIgnoreCase));
            return pictureName != null ? picture : null;
        }

        /// <summary>Calculate the percentage of match between two strings.</summary>
        /// <param name="picture">Url of the picture.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The percentage.</returns>
        internal static double CalculateMatchPercentage(string picture, string characterName)
        {
            int levenshteinDistance = LevenshteinDistance(picture, characterName);
            int maxLength = Math.Max(picture.Length, characterName.Length);

            return maxLength == 0 ? 1.0 : 1.0 - (double)levenshteinDistance / maxLength;
        }

        /// <summary>Compute the Levenshtein distance.</summary>
        /// <param name="s">First string to compare.</param>
        /// <param name="t">Second string to compare.</param>
        /// <returns>The Levenshtein distance.</returns>
        internal static int LevenshteinDistance(string s, string t)
        {
            int n = s.Length;
            int m = t.Length;
            int[][] d = new int[n + 1][];

            // Initialisation des lignes du tableau jagged.
            for (int i = 0; i <= n; i++)
            {
                d[i] = new int[m + 1];
            }

            if (n == 0)
                return m;

            if (m == 0)
                return n;

            for (int i = 0; i <= n; i++)
            {
                d[i][0] = i;
            }

            for (int j = 0; j <= m; j++)
            {
                d[0][j] = j;
            }

            for (int j = 1; j <= m; j++)
            {
                for (int i = 1; i <= n; i++)
                {
                    int cost = (t[j - 1] == s[i - 1]) ? 0 : 1;
                    d[i][j] = Math.Min(
                        Math.Min(d[i - 1][j] + 1, d[i][j - 1] + 1),
                        d[i - 1][j - 1] + cost);
                }
            }

            return d[n][m];
        }

    }
}
