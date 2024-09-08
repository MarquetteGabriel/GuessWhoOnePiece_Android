// <copyright file="Popularity.cs">
// Copyright (c) 2024 All Rights Reserved
// </copyright>
// <author>Gabriel Marquette</author>

using GuessWhoOnePiece.Model.Characters;
using HtmlAgilityPack;

namespace GuessWhoOnePiece.Model.DataEntries
{
    /// <summary>Represent the definition of the popularity for each character.</summary>
    public class Popularity
    {
        private const string UrlLevels = "http://www.volonte-d.com/details/popularite.php";

        private static readonly List<string> ListPopularity = [];

        private static int _countLevels;

        /// <summary>Set the popularity of characters.</summary>
        /// <param name="characterNameList">List of characters.</param>
        public static void SetPopularity(List<string> characterNameList, List<Character> characterList)
        {
            var web = new HtmlWeb();
            var doc = web.Load(UrlLevels);
            var elements =
                doc.DocumentNode.SelectNodes("//*[contains(@class, 'gallery') and contains(@class, 'clearfix')]");
            if (elements.LastOrDefault() != null)
            {
                var tables = elements.Last().SelectNodes("table");
                if (tables.Count > 0)
                {
                    var table = tables.Last();
                    if (table != null)
                    {
                        var rows = table.SelectNodes("tr");
                        for (var i = 1; i < rows.Count; i++)
                        {
                            var row = rows[i];
                            var cols = row.SelectNodes("td");
                            ListPopularity.Add(cols[1].InnerText);
                        }
                    }
                }
            }

            foreach (var character in characterNameList)
            {
                string tempCharacterName = character;
                if (tempCharacterName.Contains("alias"))
                {
                    tempCharacterName = character.Replace("\\s*\\(.*?\\)", "");
                }

                var position = ListPopularity.IndexOf(tempCharacterName);

                if (position == -1)
                {
                    string newCharacter = GetMatcher(tempCharacterName);
                    position = ListPopularity.IndexOf(newCharacter);
                    if (position == -1)
                    {
                        position = ListPopularity.Count();
                    }
                }

                foreach (var characters in characterList)
                {
                    if (characters.name.Equals(character))
                    {
                        for (var i = ControlRoom.NumberOfLevels; i >= 1; i--)
                        {
                            if (position <= 200 * i)
                            {
                                characters.level = i - 1;
                            }
                        }

                        if (characters.level == ControlRoom.NumberOfLevels + 1)
                        {
                            characters.level = (ControlRoom.NumberOfLevels - 1);
                        }
                    }
                }

                _countLevels++;
            }
        }
        
        private static string GetMatcher(string character)
        {
            var newCharacter = string.Empty;
            var meilleureRessemblance = 0.0;
            var texteRessemblant = string.Empty;
            foreach (var popularityCharacter in ListPopularity)
            {
                character = DataControl.ExtractExceptionsPopularity(character);
                var ressemblance = CalculateSimilarity(character, popularityCharacter);

                if (!(ressemblance >= 0.6) || !(ressemblance > meilleureRessemblance)) continue;
                meilleureRessemblance = ressemblance;
                texteRessemblant = popularityCharacter;
            }

            if(!string.IsNullOrEmpty(texteRessemblant))
            {
                newCharacter = texteRessemblant;
            }
            else
            {
                foreach (var popularityCharacter in ListPopularity)
                {
                    var distance = LevenshteinDistance(character, popularityCharacter);
                    const int seuilDistance = 6;
                    if (distance <= seuilDistance) {
                        newCharacter = popularityCharacter;
                    }
                }
            }
            return newCharacter;
        }
    
        private static double CalculateSimilarity(string texte1, string texte2) {
            var termes1 = texte1.Split(" ");
            var termes2 = texte2.Split(" ");
            Dictionary<string, int> vector1 = new ();
            Dictionary<string, int> vector2 = new ();
            foreach (var terme in termes1)
            {
                vector1[terme] = 1;
            }
            foreach (var terme in termes2)
            {
                vector2[terme] = 1;
            }
            /*
            CosineSimilarity cosSimilarity = new CosineSimilarity();
            return cosSimilarity.cosineSimilarity(vector1, vector2);*/

            return 0;
        }
        
        private static int LevenshteinDistance(string s, string t)
        {
            int n = s.Length;
            int m = t.Length;
            int[,] d = new int[n + 1, m + 1];

            if (n == 0)
            {
                return m;
            }

            if (m == 0)
            {
                return n;
            }

            for (int i = 0; i <= n; d[i, 0] = i++)
            {
            }

            for (int j = 0; j <= m; d[0, j] = j++)
            {
            }

            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= m; j++)
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
