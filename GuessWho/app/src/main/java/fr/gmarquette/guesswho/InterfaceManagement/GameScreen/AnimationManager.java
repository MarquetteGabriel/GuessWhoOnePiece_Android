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
            case "Ener's Crew":
                return (PicturesAlbum.getInstance().CREW_ENER);
            case "Kaido's Crew":
                return (PicturesAlbum.getInstance().CREW_KAIDO);
            case "Teach's Crew":
                return (PicturesAlbum.getInstance().CREW_TEACH);
            case "BigMom's Crew":
                return (PicturesAlbum.getInstance().CREW_BIGMOM);
            case "Cross Guild":
                return (PicturesAlbum.getInstance().CROSS_GUILD);
            case "Shanks's Crew":
                return (PicturesAlbum.getInstance().CREW_SHANKS);
            case "Newgate's Crew":
                return (PicturesAlbum.getInstance().CREW_NEWGATE);
            case "Mugiwara's Crew":
            case "Mugiwara's Armada Crew":
                return (PicturesAlbum.getInstance().CREW_MUGIWARA);
            case "Revolutionary's Crew":
                return (PicturesAlbum.getInstance().CREW_REVOLUTIONARY_ARMY);
            case "World Government":
            case "Cipher Pol":
                return (PicturesAlbum.getInstance().CREW_WORLDGOV);
            case "Arlong's Crew":
                return (PicturesAlbum.getInstance().CREW_ARLONG);
            case "Baroque Works":
                return (PicturesAlbum.getInstance().CREW_BW);
            case "Don Krieg's Crew":
                return (PicturesAlbum.getInstance().CREW_KRIEG);
            case "Moria's Crew" :
                return (PicturesAlbum.getInstance().CREW_MORIA);
            case "Heart's Crew" :
                return (PicturesAlbum.getInstance().CREW_LAW);
            case "Kidd's Crew" :
                return (PicturesAlbum.getInstance().CREW_KIDD);
            case "Kuja's Crew" :
                return (PicturesAlbum.getInstance().CREW_KUJA);
            case "Caribou's Crew" :
                return (PicturesAlbum.getInstance().CREW_CARIBOU);
            case "Sun Pirates' Crew" :
                return (PicturesAlbum.getInstance().CREW_SUNPIRATES);
            case "Roger's Crew" :
                return (PicturesAlbum.getInstance().CREW_ROGER);
            case "Doflamingo's Crew" :
                return (PicturesAlbum.getInstance().CREW_DOFFY);
            case "Rumbar's Crew" :
                return (PicturesAlbum.getInstance().CREW_RUMBAR);
            case "Fishmen's Crew" :
                return (PicturesAlbum.getInstance().CREW_NEWFISH);
            case "Giants' Crew":
                return (PicturesAlbum.getInstance().CREW_GIANTS);
            case "Celestial Dragons" :
                return (PicturesAlbum.getInstance().CREW_CELESTIAL);
            case "Impel Down":
                return (PicturesAlbum.getInstance().CREW_ID);
            case "Monkey's Forces Crew":
            case "Weeble's Crew" :
            case "Black Cat's Crew" :
            case "Foxy's Crew" :
            case "Bege's Crew" :
            case "Bonney's Crew" :
            case "Urouge's Crew" :
            case "Nox's Crew":
            case "Assassin's Mogalo":
            case "Vegapunk's Factory" :
            case "Bluejam's Crew" :
            case "False Mugiwara's Crew" :
            case "Caesar's Crew" :
            case "Dadan's Crew":
            case "Mountain's Bandits":
            case "Zeff's Crew":
            case "8 Treasure's Navy":
            default:
                return (PicturesAlbum.getInstance().CREW_DEFAULT);
        }
    }

}
