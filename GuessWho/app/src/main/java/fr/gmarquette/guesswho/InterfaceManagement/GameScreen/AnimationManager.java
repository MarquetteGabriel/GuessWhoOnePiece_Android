/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameSystem.AgeType;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyType;
import fr.gmarquette.guesswho.GameSystem.ChapterType;

public class AnimationManager
{
    public static Answering updateFruit(boolean eatenFruit, Characters characters)
    {
        int circle, answer;
        if (characters.hasDevilFruit())
        {
            answer = (PicturesAlbum.getInstance().DEVIL_FRUIT);
        }
        else
        {
            answer = (PicturesAlbum.getInstance().NO_DEVIL_FRUIT);
        }

        if(eatenFruit)
        {
            circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
        }

        return new Answering(circle, answer);
    }

    public static Answering updateBounty(BountyType bountyType, Characters characters)
    {
        int circle = 0, answer = 0;
        String text = characters.getBounty();
        switch (bountyType)
        {
            case EQUAL:
                circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
                break;
            case LOWER:
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_DOWN);
                break;
            case UPPER:
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_UP);
                break;
            case WRONG_UNKNOWN :
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                break;
            default:
                break;
        }
        return new Answering(circle, answer, text);
    }

    public static Answering updateAge(AgeType ageType, Characters characters)
    {
        int circle = 0, answer = 0;
        String text = String.valueOf(characters.getAge());
        switch (ageType)
        {
            case YOUNGER: // Arrow above
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_UP);
                break;
            case OLDER: // Arrow down
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_DOWN);
                break;
            case EQUAL:
                circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
                break;
            default:
                break;
        }
        return new Answering(circle, answer, text);
    }

    public static Answering updateChapter(ChapterType chapterType, Characters characters)
    {
        int circle = 0, answer = 0;
        String text = String.valueOf(characters.getFirstAppearance());
        switch (chapterType)
        {
            case NEWER_CHAPTER: // Arrow down
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_UP);
                break;
            case PREVIOUS_CHAPTER: // Arrow above
                circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
                answer = (PicturesAlbum.getInstance().ARROW_DOWN);
                break;
            case SAME_CHAPTER:
                circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
                break;
            default:
                break;
        }
        return new Answering(circle, answer, text);
    }

    public static Answering updateType(boolean getType, Characters characters)
    {
        int circle, answer = 0;
        switch (characters.getType())
        {
            case "Pirate":
                answer = (PicturesAlbum.getInstance().PIRATES);
                break;
            case "Revolutionary":
                answer = (PicturesAlbum.getInstance().REVOLUTIONARY_ARMY);
                break;
            case "Navy":
                answer = (PicturesAlbum.getInstance().NAVY_GOVERNMENT);
                break;
            case "Citizen":
                answer = (PicturesAlbum.getInstance().CITIZENS);
                break;
            default:
                break;
        }

        if (getType)
        {
            circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
        }
        return new Answering(circle, answer);
    }

    public static Answering updateAlive(boolean alived, Characters characters)
    {
        int circle, answer;
        if (characters.isAlive())
        {
            answer = (PicturesAlbum.getInstance().ALIVE);
        }
        else
        {
            answer = (PicturesAlbum.getInstance().NOT_ALIVE);
        }

        if(alived)
        {
            circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);

        }
        else
        {
            circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
        }
        return new Answering(circle, answer);
    }

    public static Answering updateCrew(boolean belongingCrew, Characters characters)
    {
        int circle, answer;
        if(belongingCrew)
        {
            circle = (PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            circle = (PicturesAlbum.getInstance().WRONG_ANSWER);
        }

        answer = setPictureFromCrew(characters);
        return new Answering(circle, answer);
    }

    private static int setPictureFromCrew(Characters characters)
    {
        switch (characters.getCrew())
        {
            case "Citizen":
                return (PicturesAlbum.getInstance().CREW_CITIZEN);
            case "Navy's Crew":
                return (PicturesAlbum.getInstance().CREW_NAVY);
            case "Clan d'Ener":
                return (PicturesAlbum.getInstance().CREW_ENER);
            case "L'Équipage aux Cent Bêtes":
                return (PicturesAlbum.getInstance().CREW_KAIDO);
            case "L'Équipage de Barbe Noire":
                return (PicturesAlbum.getInstance().CREW_TEACH);
            case "L'Équipage de Big Mom":
            case "Équipage de Big Mom":
            case "Famille Charlotte":
                return (PicturesAlbum.getInstance().CREW_BIGMOM);
            case "Cross Guild":
                return (PicturesAlbum.getInstance().CROSS_GUILD);
            case "L'Équipage du Roux":
                return (PicturesAlbum.getInstance().CREW_SHANKS);
            case "L'Équipage de Barbe Blanche":
            case "Subordonné de L'Équipage de Barbe Blanche":
                return (PicturesAlbum.getInstance().CREW_NEWGATE);
            case "L'Équipage du Chapeau de Paille":
            case "Allié de L'Équipage du Chapeau de Paille":
            case "La Grande Flotte du Chapeau de Paille":
            case "Alliance de l'Équipage du Chapeau de Paille":
                return (PicturesAlbum.getInstance().CREW_MUGIWARA);
            case "Revolutionary's Crew":
                return (PicturesAlbum.getInstance().CREW_REVOLUTIONARY_ARMY);
            case "Gouvernement Mondial":
            case "Cipher Pol":
                return (PicturesAlbum.getInstance().CREW_WORLDGOV);
            case "L'Équipage d'Arlong":
                return (PicturesAlbum.getInstance().CREW_ARLONG);
            case "Baroque Works":
                return (PicturesAlbum.getInstance().CREW_BW);
            case "L'Armada Pirate de Don Krieg":
                return (PicturesAlbum.getInstance().CREW_KRIEG);
            case "Gecko Moria" :
            case "Hogback":
            case "Thriller Bark":
            case "Dracule Mihawk":
                return (PicturesAlbum.getInstance().CREW_MORIA);
            case "L'Équipage du Heart" :
                return (PicturesAlbum.getInstance().CREW_LAW);
            case "L'Équipage de Kid" :
                return (PicturesAlbum.getInstance().CREW_KIDD);
            case "Kujas" :
                return (PicturesAlbum.getInstance().CREW_KUJA);
            case "Capitaine de l'Equipage de Caribou" :
            case "L'Équipage de Caribou":
                return (PicturesAlbum.getInstance().CREW_CARIBOU);
            case "L'Équipage des Pirates du Soleil" :
                return (PicturesAlbum.getInstance().CREW_SUNPIRATES);
            case "L'Équipage des Pirates Roger" :
                return (PicturesAlbum.getInstance().CREW_ROGER);
            case "L'Équipage du New Age":
            case "L'Équipage de Don Quichotte Doflamingo" :
            case "César Clown (espionnage)":
                return (PicturesAlbum.getInstance().CREW_DOFFY);
            case "L'Équipage du Rumbar " :
                return (PicturesAlbum.getInstance().CREW_RUMBAR);
            case "L'Équipage des Pirates Volants":
            case "L'Équipage des Nouveaux Hommes-Poissons" :
                return (PicturesAlbum.getInstance().CREW_NEWFISH);
            case "L'Équipage des Géants":
                return (PicturesAlbum.getInstance().CREW_GIANTS);
            case "Celestial Dragons" :
                return (PicturesAlbum.getInstance().CREW_CELESTIAL);
            case "Ligue des Primates":
            case "Edward Weeble":
            case "L'Équipage du Chat Noir" :
            case "L'Équipage de Foxy":
            case "L'Équipage du Fire Tank" :
            case "L'Équipage de Bonney" :
            case "L'Équipage des Moines Dépravés":
            case "L'Équipage de Bluejam" :
            case "Faux Équipage du Chapeau de Paille" :
            case "L'Équipage de Barbe Brune(dissout)":
            case "César Clown":
            case "Bandits des montagnes":
            case "Bandit":
            case "Gang du Pays des Fleurs (Famille Chinjao)":
            case "L'Équipage d'Idéo":
            case "L'Équipage de X. Barrels":
            case "L'Équipage du On-Air" :
            case "L'Équipage du Rolling":
            case "L'Équipage de Hawkins":
            case "L'Équipage des Magnifiques Pirates":
            case "L'Équipage du Lion d'Or":
            default:
                return (PicturesAlbum.getInstance().CREW_DEFAULT);
        }
    }

}
