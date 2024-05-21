/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData;

import static org.junit.Assert.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class ExtractorPatternTest
{


    @Test
    public void extractPattern() {
        String input = "Ceci est un @test";
        String pattern = "(@test)";
        String expectedResult = "@test";
        String actualResult = ExtractorPattern.extractPattern(input, pattern);
        assertEquals(expectedResult, actualResult);

        String inputNoMatch = "Ceci est un test";
        String patternNoMatch = "(@test)";
        String expectedResultNoMatch = "";
        String actualResultNoMatch = ExtractorPattern.extractPattern(inputNoMatch, patternNoMatch);
        assertEquals(expectedResultNoMatch, actualResultNoMatch);

        String inputInvalidPattern = "Ceci est un test";
        String patternInvalidPattern = "[*";
        String expectedResultInvalidPattern = "";
        String actualResultInvalidPattern = ExtractorPattern.extractPattern(inputInvalidPattern, patternInvalidPattern);
        assertEquals(expectedResultInvalidPattern, actualResultInvalidPattern);
    }

    @Test
    public void extractPatternBounty() {
        String input = "Prime : 100 000 000 (Statut : Vivant)";
        String expectedResult = "100000000";
        String actualResult = ExtractorPattern.extractPatternBounty(input);
        assertEquals(expectedResult, actualResult);

        String inputInvalidBounty = "Prime : Inconnue (Âge : 22 ans)";
        String expectedResultInvalidBounty = "";
        String actualResultInvalidBounty = ExtractorPattern.extractPatternBounty(inputInvalidBounty);
        assertEquals(expectedResultInvalidBounty, actualResultInvalidBounty);

        String inputNoBounty = "Prime : Aucune (Anniversaire : 5 mai)";
        String expectedResultNoBounty = "";
        String actualResultNoBounty = ExtractorPattern.extractPatternBounty(inputNoBounty);
        assertEquals(expectedResultNoBounty, actualResultNoBounty);

        String inputMultipleBounties = "Prime : 100 000 000; 200 000 000 (Statut : Décédé)";
        String expectedResultMultipleBounties = "200000000";
        String actualResultMultipleBounties = ExtractorPattern.extractPatternBounty(inputMultipleBounties);
        assertEquals(expectedResultMultipleBounties, actualResultMultipleBounties);

        String inputInvalidStatut = "Prime : )Inconnue)-@ ";
        String expectedResultInvalidStatut = "";
        String actualResultInvalidStatut = ExtractorPattern.extractPatternBounty(inputInvalidStatut);
        assertEquals(expectedResultInvalidStatut, actualResultInvalidStatut);
    }

    @Test
    public void fixBounty() {
        assertEquals("0", ExtractorPattern.fixBounty("", "Marine"));
        assertEquals("0", ExtractorPattern.fixBounty("Inconnue", "Marine"));
        assertEquals("0", ExtractorPattern.fixBounty("Aucune", "Marine"));
        assertEquals("0", ExtractorPattern.fixBounty("123", "Marine"));
        assertEquals("0", ExtractorPattern.fixBounty("", "Citizen"));
        assertEquals("0", ExtractorPattern.fixBounty("Inconnue", "Citizen"));
        assertEquals("0", ExtractorPattern.fixBounty("Aucune", "Citizen"));
        assertEquals("0", ExtractorPattern.fixBounty("123", "Citizen"));
        assertEquals("Unknown", ExtractorPattern.fixBounty("", "Pirate"));
        assertEquals("Unknown", ExtractorPattern.fixBounty("Inconnue", "Pirate"));
        assertEquals("Unknown", ExtractorPattern.fixBounty("Aucune", "Pirate"));
        assertEquals("123", ExtractorPattern.fixBounty("123", "Pirate"));
        assertEquals("1,235 Mi", ExtractorPattern.fixBounty("1234566", "Pirate"));
        assertEquals("1,235 Md", ExtractorPattern.fixBounty("1234566789", "Pirate"));

    }

    @Test
    public void fixType() {
        assertEquals("Navy", ExtractorPattern.fixType("", "Celestial Dragons"));
        assertEquals("Navy", ExtractorPattern.fixType("Dragon Celestes", "Celestial Dragons"));
        assertEquals("Dragon Celestes", ExtractorPattern.fixType("Dragon Celestes", "Marine"));
        assertEquals("Pirate", ExtractorPattern.fixType("", "Straw Hat Pirates"));
        assertEquals("Pirate", ExtractorPattern.fixType("", "Heart Pirates"));
        assertEquals("Pirate", ExtractorPattern.fixType("", "Beasts Pirates"));
        assertEquals("Navy", ExtractorPattern.fixType("", "Marine"));
        assertEquals("Navy", ExtractorPattern.fixType("", "CP-AIGIS0"));
        assertEquals("Revolutionary", ExtractorPattern.fixType("", "Revolutionary's Crew"));
    }

    @Test
    public void fixCrew() {
        assertEquals("Cipher Pol", ExtractorPattern.fixCrew("CP0", ""));
        assertEquals("Celestial Dragons", ExtractorPattern.fixCrew("Dragon Celestes", ""));
        assertEquals("Revolutionary's Crew", ExtractorPattern.fixCrew("Revolutionary", ""));
        assertEquals("Navy's Crew", ExtractorPattern.fixCrew("Marines", ""));
        assertEquals("Allié de L'Équipage du Chapeau de Paille", ExtractorPattern.fixCrew("La Grande Flotte du Chapeau de Paille", ""));
        assertEquals("L'Équipage de Big Mom", ExtractorPattern.fixCrew("Équipage de Big Mom", ""));
        assertEquals("Baroque Works", ExtractorPattern.fixCrew("Spiders Café", ""));
        assertEquals("L'Équipage de Don Quichotte Doflamingo", ExtractorPattern.fixCrew("L'Équipage du New Age", ""));
        assertEquals("Thriller Bark", ExtractorPattern.fixCrew("Gecko Moria", ""));
        assertEquals("Cross Guild", ExtractorPattern.fixCrew("L'Équipage du Clown", ""));
        assertEquals("Citizen", ExtractorPattern.fixCrew("Citizen", "Citizen"));
        assertEquals("Citizen", ExtractorPattern.fixCrew("", "Citizen"));
        assertEquals("L'Équipage de Barbe Noire", ExtractorPattern.fixCrew("L'Équipage de Barbe Noire", "Pirate"));

        assertEquals("L'Équipage de Barbe Blanche", ExtractorPattern.fixCrew("Subordonné de L'Équipage de Barbe Blanche", ""));
        assertEquals("L'Équipage de Caribou", ExtractorPattern.fixCrew("Capitaine de l'Equipage de Caribou", ""));
        assertEquals("L'Équipage des Nouveaux Hommes-Poissons", ExtractorPattern.fixCrew("L'Équipage des Pirates Volants", ""));
        assertEquals("L'Équipage du Fire Tank", ExtractorPattern.fixCrew("L'Équipage du Firetank", ""));
        assertEquals("Citizen", ExtractorPattern.fixCrew("L'Équipage du Capitaine Usopp", ""));


    }

    @Test
    public void extractPatternCrew() {
        String html = "<div class=\"pi-data-value\">" +
                "<a href=\"/fr/one-piece/equipage/1000000\">L'Équipage du Chapeau de Paille</a>" +
                "<span>anciennement</span>" +
                "<a href=\"/fr/one-piece/equipage/1000000\">L'Équipage de Shanks</a>" +
                "<span>temporairement</span>" +
                "</div>";
        Document doc = Jsoup.parse(html);
        Element text = doc.select(".pi-data-value").first();

        String result = ExtractorPattern.extractPatternCrew(text);

        assertEquals("Citizen", result);
    }

    @Test
    public void extractPatternCrewError()
    {
        String html = "<div class=\"pi-data-value\"><a href=\"/link1\">Crew1</a><a href=\"/link2\">Crew2 (anciennement)</a><a href=\"/link3\">Crew3 (temporairement)</a><a href=\"/link4\">Citizen (dissous)</a></div>";
        Document doc = Jsoup.parse(html);
        Element text = doc.select(".pi-data-value").first();

        String result = ExtractorPattern.extractPatternCrew(text);

        assertEquals("Citizen", result);
    }

    @Test
    public void testExtractPatternCrewNoCrew() {
        String html = "<div class=\"pi-data-value\">" +
                "<a href=\"/fr/one-piece/equipage/1000000\">L'Équipage du Chapeau de Paille</a>" +
                "<span>anciennement</span>" +
                "<a href=\"/fr/one-piece/equipage/1000000\">L'Équipage de Shanks</a>" +
                "<span>temporairement</span>" +
                "<a href=\"/fr/one-piece/equipage/1000000\">L'Équipage de Barbe Blanche</a> </div>";
        Document doc = Jsoup.parse(html);
        Element text = doc.select(".pi-data-value").first();

        String result = ExtractorPattern.extractPatternCrew(text);

        assertEquals("L'Équipage de Barbe Blanche", result);
    }

    @Test
    public void extractPatternAge() {
        assertEquals(25, ExtractorPattern.extractPatternAge("Âge : 20 Voix, Âge : 25 Taille"));
        assertEquals(30, ExtractorPattern.extractPatternAge("Âge : 30 ans (lors de sa mort) Voix : française"));
        assertEquals(0, ExtractorPattern.extractPatternAge("Taille : 1m75, Voix : française"));
    }

    @Test
    public void extractPatternType() {
        String html = "<div class=\"pi-data-value\">Pirate</div>";
        Element text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Pirate", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Marine<br>Vice-Amiral</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Navy", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Armée Révolutionnaire<br>Chef d'Etat-Major</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Revolutionary", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Nobles Mondiaux</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Dragon Celestes", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Cuisinier</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Citizen", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Pirate<br>Chasseur de Primes</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Pirate", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Marine<br>CP9</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Navy", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\">Armée Révolutionnaire<br>Géant</div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Revolutionary", ExtractorPattern.extractPatternType(text));

        html = "<div class=\"pi-data-value\"><p>[test]</p></div>";
        text = Jsoup.parse(html).select(".pi-data-value").first();
        assertEquals("Citizen", ExtractorPattern.extractPatternType(text));

    }

    @Test
    public void extractExceptions() {
        assertEquals("Barbe Brune", ExtractorPattern.extractExceptions("Chadros Higelyges"));
        assertEquals("Jabura", ExtractorPattern.extractExceptions("Jabra"));
        assertEquals("Kurozumi Tama", ExtractorPattern.extractExceptions("Tama"));
        assertEquals("Kaku", ExtractorPattern.extractExceptions("Kaku (Wano)"));
        assertEquals("Ener", ExtractorPattern.extractExceptions("Enel"));
        assertEquals("Bakkin", ExtractorPattern.extractExceptions("Buckingham Stussy"));
        assertEquals("Luffy", ExtractorPattern.extractExceptions("Luffy"));
    }

    @Test
    public void extractExceptionsPopularity() {
        assertEquals("Donquixote Doflamingo", ExtractorPattern.extractExceptionsPopularity("Don Quichotte Doflamingo"));
        assertEquals("King", ExtractorPattern.extractExceptionsPopularity("Alber"));
        assertEquals("Big Mom", ExtractorPattern.extractExceptionsPopularity("Charlotte Linlin"));
        assertEquals("Mr 3", ExtractorPattern.extractExceptionsPopularity("Galdino"));
        assertEquals("Barbe Noire", ExtractorPattern.extractExceptionsPopularity("Marshall D. Teach"));
        assertEquals("Barbe Blanche", ExtractorPattern.extractExceptionsPopularity("Edward Newgate"));
        assertEquals("Monkey D. Luffy", ExtractorPattern.extractExceptionsPopularity("Monkey D. Luffy"));

    }
}
