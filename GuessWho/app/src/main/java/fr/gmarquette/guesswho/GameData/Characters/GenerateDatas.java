/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GenerateDatas
{
    String url_wikipedia_onepiece = "https://fr.wikipedia.org/wiki/Personnages_de_One_Piece";


    public String getDatasFromOutside()
    {
        {
            try {
                Document doc = Jsoup.connect(url_wikipedia_onepiece).get();
                return doc.text();
            } catch (IOException e) {
                e.printStackTrace();
                return "Erreur " + e.getMessage();
            }
        }
    }

    public void createCharacterFromInformation()
    {

    }
}
