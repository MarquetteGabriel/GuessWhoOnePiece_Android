﻿using GuessWhoOnePiece.Model.Characters;

namespace GuessWhoOnePiece.Model.Game;

internal static class DefinePictures
{
    internal static string SetAgePicture(AgeType value)
    {
        return value switch
        {
            AgeType.Younger => PicturesAlbum.ArrowUp,
            AgeType.Equal => string.Empty,
            AgeType.Older => PicturesAlbum.ArrowDown,
            _ => string.Empty,
        };
    }
    
    internal static string SetChapterPicture(ChapterType value)
    {
        return value switch
        {
            ChapterType.PreviousChapter => PicturesAlbum.ArrowUp,
            ChapterType.NewerChapter => PicturesAlbum.ArrowDown,
            ChapterType.SameChapter => string.Empty,
            _ => string.Empty,
        };
    }

    internal static string SetTypePicture(string value)
    {
        return value switch 
        {
            "Pirate" => PicturesAlbum.Pirates,
            "Revolutionary" => PicturesAlbum.Revolutionary,
            "Navy" => PicturesAlbum.Navy,
            "Citizen" => PicturesAlbum.Citizen,
                _ => string.Empty,
        };
    }
    
    internal static string SetBountyPicture(BountyType value)
    {
        return value switch
        {
            BountyType.Lower => PicturesAlbum.ArrowDown,
            BountyType.Upper => PicturesAlbum.ArrowUp,
            BountyType.Equal => string.Empty,
            BountyType.WrongUnknown => string.Empty,
            _ => string.Empty,
        };
    }
    
    internal static string SetAlivePicture(bool value)
    {
        return value ? PicturesAlbum.Alive : PicturesAlbum.NoAlive;
    }
    
    internal static string SetDevilFruitPicture(bool value)
    {
        return value ? PicturesAlbum.DevilFruit : PicturesAlbum.NoDevilFruit;
    }

    internal static string SetCrewPictures(string value)
    {
        return value switch
        {
            "Citizen" => PicturesAlbum.CrewCitizen,
            "Navy's Crew" => PicturesAlbum.CrewNavy,
            "Clan d'Ener" => PicturesAlbum.CrewEner,
            "L'Équipage aux Cent Bêtes" => PicturesAlbum.CrewKaido,
            "L'Équipage de Barbe Noire" => PicturesAlbum.CrewTeach,
            "L'Équipage de Big Mom" => PicturesAlbum.CrewBigmom,
            "Cross Guild" => PicturesAlbum.CrossGuild,
            "L'Équipage du Roux" => PicturesAlbum.CrewShanks,
            "L'Équipage de Barbe Blanche" => PicturesAlbum.CrewNewgate,
            "L'Équipage du Chapeau de Paille" => PicturesAlbum.CrewMugiwara,
            "Allié de L'Équipage du Chapeau de Paille" => PicturesAlbum.CrewMugiwara,
            "Faux Équipage du Chapeau de Paille" => PicturesAlbum.CrewMugiwara,
            "Revolutionary's Crew" => PicturesAlbum.CrewRevolutionaryArmy,
            "Gouvernement Mondial" => PicturesAlbum.CrewWorldGov,
            "Cipher Pol" => PicturesAlbum.CrewWorldGov,
            "L'Équipage d'Arlong" => PicturesAlbum.CrewArlong,
            "Baroque Works" => PicturesAlbum.CrewBW,
            "L'Armada Pirate de Don Krieg" => PicturesAlbum.CrewKrieg,
            "Thriller Bark" => PicturesAlbum.CrewMoria,
            "L'Équipage du Heart" => PicturesAlbum.CrewLaw,
            "L'Équipage de Kid" => PicturesAlbum.CrewKidd,
            "Kujas" => PicturesAlbum.CrewKuja,
            "L'Équipage de Caribou" => PicturesAlbum.CrewCaribou,
            "L'Équipage des Pirates du Soleil" => PicturesAlbum.CrewSunpirates,
            "L'Équipage des Pirates Roger" => PicturesAlbum.CrewRoger,
            "L'Équipage de Don Quichotte Doflamingo" => PicturesAlbum.CrewDoffy,
            "L'Équipage du Rumbar" => PicturesAlbum.CrewRumbar,
            "L'Équipage des Nouveaux Hommes-Poissons" => PicturesAlbum.CrewNewfish,
            "L'Équipage des Géants" => PicturesAlbum.CrewGiants,
            "Celestial Dragons" => PicturesAlbum.CrewCelestial,
            "Impel Down" => PicturesAlbum.CrewID,
            "Ligue des Primates" => PicturesAlbum.CrewPrimates,
            "Edward Weeble" => PicturesAlbum.CrewWeeble,
            "L'Équipage du Chat Noir" => PicturesAlbum.CrewChatnoir,
            "L'Équipage de Foxy" => PicturesAlbum.CrewFoxy,
            "L'Équipage du Fire Tank" => PicturesAlbum.CrewFiretank,
            "L'Équipage de Bonney" => PicturesAlbum.CrewBonney,
            "L'Équipage des Moines Dépravés" => PicturesAlbum.CrewUrouge,
            "L'Équipage de X. Barrels" => PicturesAlbum.CrewXbarrels,
            "L'Équipage du On-Air" => PicturesAlbum.CrewApoo,
            "L'Équipage de Hawkins" => PicturesAlbum.CrewHawkins,
            "L'Équipage du Lion d'Or" => PicturesAlbum.CrewShiki,
            "L'Équipage du Rolling" => PicturesAlbum.CrewDefault,
            "Gang du Pays des Fleurs (Famille Chinjao)" => PicturesAlbum.CrewDefault,
            "Bandit" => PicturesAlbum.CrewDefault,
            "L'Équipage de Bluejam" => PicturesAlbum.CrewDefault,
            "Bandits des montagnes" => PicturesAlbum.CrewDefault,
            "L'Équipage de Wapol" => PicturesAlbum.CrewDefault,
            _ => PicturesAlbum.CrewDefault
        };

    }
}