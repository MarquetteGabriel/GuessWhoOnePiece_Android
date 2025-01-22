// <copyright file="PictureData.cs">
// Copyright (c) 2025 All Rights Reserved. 
// </copyright>
// <author>Gabriel Marquette</author>

using HtmlAgilityPack;
using System;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;

namespace GuessWhoOnePiece.Model.DataEntries
{
    public partial class ControlRoom
    {
        private const string MangaPostEllipse = "Manga_Post_Ellipse";
        private const string MangaPreEllipse = "Manga_Pre_Ellipse";
        private const string AnimePostEllipse = "Anime_Post_Ellipse";
        private const string AnimePreEllipse = "Anime_Pre_Ellipse";

        private const string SiteLogo = "Site-logo";
        private const string ImageGift = "data:image/gif";

        private const string Amp = "amp;";
        private const string Href = "href";
        private const string AmpAnd = "&amp";
        private const string Esperluette = "&";

        private const string AndCharacter = "And";
        private const string BelmerCharacter = "Belmer";
        private const string RockCharacter = "Rock";
        private const string EnerCharacter = "Ener";
        private const string JinbeiCharacter = "Jinbei";
        private const string BakkinCharacter = "Bakkin";
        private const string MansherryCharacter = "Mansherry";
        private const string ShishilianCharacter = "Shishilian";
        private const string SuleimanCharacter = "Suleiman";

        private const string AndCharacterPicture = "Baskerville";
        private const string BelmerCharacterPicture = "Bell";
        private const string RockCharacterPicture = "Yeti";
        private const string EnerCharacterPicture = "Enel";
        private const string JinbeiCharacterPicture = "Jinbe";
        private const string BakkinCharacterPicture = "Buckingham";
        private const string MansherryCharacterPicture = "Manshelly";
        private const string ShishilianCharacterPicture = "Sicilion";
        private const string SuleimanCharacterPicture = "Suleyman";




        /// <summary>Gets the link of the image for a character.</summary>
        /// <param name="listOfPictures">List of picture in the web page.</param>
        /// <param name="characterName">Name of the character.</param>
        /// <returns>The link of the image for the character.</returns>
        private static string GetPictureLink(HtmlNodeCollection listOfPictures, string characterName)
        {
            foreach (var picture in listOfPictures.Select(picture => picture.GetAttributeValue(Href, string.Empty)))
            {
                var newPicture = picture.Replace(Amp, string.Empty, StringComparison.OrdinalIgnoreCase).Replace(AmpAnd, Esperluette, StringComparison.OrdinalIgnoreCase);
                if (newPicture.Contains(ImageGift, StringComparison.OrdinalIgnoreCase) || newPicture.Contains(SiteLogo, StringComparison.OrdinalIgnoreCase))
                    continue;
                if (newPicture.Contains(MangaPostEllipse, StringComparison.OrdinalIgnoreCase) || newPicture.Contains(MangaPreEllipse, StringComparison.OrdinalIgnoreCase) ||
                    newPicture.Contains(AnimePostEllipse, StringComparison.OrdinalIgnoreCase) || newPicture.Contains(AnimePreEllipse, StringComparison.OrdinalIgnoreCase))
                    return newPicture;

                if (newPicture.Contains(characterName))
                    return newPicture;


                foreach (var character in characterName.Split(" "))
                {
                    if (newPicture.Contains(WebUtility.UrlEncode(character), StringComparison.OrdinalIgnoreCase))
                        return newPicture;
                    else if (RemoveDiacritics(WebUtility.UrlDecode(newPicture)).Contains(RemoveDiacritics(character), StringComparison.OrdinalIgnoreCase))
                        return newPicture;
                    else
                    {
                        // Empty on purpose.
                    }
                }

                if (CalculateMatchPercentage(newPicture, characterName) > AcceptanceCritera)
                    return newPicture;


                if (characterName.Equals(AndCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(AndCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(BelmerCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(BelmerCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(RockCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(RockCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(EnerCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(EnerCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(JinbeiCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(JinbeiCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(BakkinCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(BakkinCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(MansherryCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(MansherryCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(ShishilianCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(ShishilianCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else if (characterName.Equals(SuleimanCharacter, StringComparison.OrdinalIgnoreCase) && picture.Contains(SuleimanCharacterPicture, StringComparison.OrdinalIgnoreCase))
                    return newPicture;
                else
                {
                    // Empty on purpose.
                }
            }

            return string.Empty;
        }

        /// <summary>Removes diacritics from a string.</summary>
        /// <param name="text">Text to analyse.</param>
        /// <returns>The new text.</returns>
        private static string RemoveDiacritics(string text)
        {
            var normalizedString = text.Normalize(NormalizationForm.FormD);
            var stringBuilder = new StringBuilder();

            foreach (var c in normalizedString)
            {
                var unicodeCategory = CharUnicodeInfo.GetUnicodeCategory(c);
                if (unicodeCategory != UnicodeCategory.NonSpacingMark)
                {
                    stringBuilder.Append(c);
                }
            }

            return stringBuilder.ToString().Normalize(NormalizationForm.FormC);
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
            int[,] d = new int[n + 1, m + 1];

            if (n == 0)
                return m;

            if (m == 0)
                return n;

            for (int i = 0; i <= n; d[i, 0] = i++)
            {
                // Empty on purpose.
            }

            for (int j = 0; j <= m; d[0, j] = j++)
            {
                // Empty on purpose.
            }

            for (int j = 1; j <= m; j++)
            {
                for (int i = 1; i <= n; i++)
                {
                    int cost = (t[j - 1] == s[i - 1]) ? 0 : 1;
                    d[i, j] = Math.Min(
                        Math.Min(d[i - 1, j] + 1, d[i, j - 1] + 1),
                        d[i - 1, j - 1] + cost);
                }
            }

            return d[n, m];
        }
    }
}
