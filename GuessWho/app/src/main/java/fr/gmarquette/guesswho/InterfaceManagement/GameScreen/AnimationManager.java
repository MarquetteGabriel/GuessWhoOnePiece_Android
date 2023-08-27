/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.widget.ImageView;
import android.widget.TextView;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameSystem.AgeType;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyType;
import fr.gmarquette.guesswho.GameSystem.ChapterType;

public class AnimationManager
{
    public static void updateFruit(boolean eatenFruit, Characters characters, ImageView imageViewBackground, ImageView imageViewAnswer)
    {
        if (characters.hasDevilFruit())
        {
            imageViewAnswer.setImageResource(PicturesAlbum.getInstance().DEVIL_FRUIT);
        }
        else
        {
            imageViewAnswer.setImageResource(PicturesAlbum.getInstance().NO_DEVIL_FRUIT);
        }

        if(eatenFruit)
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        }
    }

    public static void updateBounty(BountyType bountyType, Characters characters, ImageView imageViewBackground, ImageView imageViewAnswer, TextView textView)
    {
        String bounty = characters.getBounty();
        switch (bountyType)
        {
            case EQUAL:
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
                textView.setText(bounty);
                break;
            case LOWER:
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().ARROW_DOWN);
                textView.setText(bounty);
                break;
            case UPPER:
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().ARROW_UP);
                textView.setText(bounty);
                break;
            case WRONG_UNKNOWN :
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                textView.setText(bounty);
                break;
            default:
                break;
        }
    }

    public static void updateAge(AgeType ageType, Characters characters, ImageView imageViewBackground, ImageView imageViewArrows, TextView textView)
    {
        String age = String.valueOf(characters.getAge());
        switch (ageType)
        {
            case YOUNGER: // Arrow above
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewArrows.setImageResource(PicturesAlbum.getInstance().ARROW_UP);
                textView.setText(age);
                break;
            case OLDER: // Arrow down
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewArrows.setImageResource(PicturesAlbum.getInstance().ARROW_DOWN);
                textView.setText(age);
                break;
            case EQUAL:
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
                textView.setText(age);
                break;
            default:
                break;
        }
    }

    public static void updateChapter(ChapterType chapterType, Characters characters, ImageView imageViewBackground, ImageView imageViewArrows, TextView textView)
    {
        String chapter = String.valueOf(characters.getFirstAppearance());
        switch (chapterType)
        {
            case NEWER_CHAPTER: // Arrow down
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewArrows.setImageResource(PicturesAlbum.getInstance().ARROW_UP);
                textView.setText(chapter);
                break;
            case PREVIOUS_CHAPTER: // Arrow above
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                imageViewArrows.setImageResource(PicturesAlbum.getInstance().ARROW_DOWN);
                textView.setText(chapter);
                break;
            case SAME_CHAPTER:
                imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
                textView.setText(chapter);
                break;
            default:
                break;
        }
    }

    public static void updateType(boolean getType, Characters characters, ImageView imageViewBackground, ImageView imageViewAnswer)
    {
        switch (characters.getType())
        {
            case "Pirate":
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().PIRATES);
                break;
            case "Revolutionary":
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().REVOLUTIONARY_ARMY);
                break;
            case "Navy":
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().NAVY_GOVERNMENT);
                break;
            case "Citizen":
                imageViewAnswer.setImageResource(PicturesAlbum.getInstance().CITIZENS);
                break;
            default:
                break;
        }

        if (getType)
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        }
    }

    public static void updateAlive(boolean alived, Characters characters, ImageView imageViewBackground, ImageView imageViewAnswer)
    {
        if (characters.isAlive())
        {
            imageViewAnswer.setImageResource(PicturesAlbum.getInstance().ALIVE);
        }
        else
        {
            imageViewAnswer.setImageResource(PicturesAlbum.getInstance().NOT_ALIVE);
        }

        if(alived)
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);

        }
        else
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        }
    }

    public static void updateCrew(boolean belongingCrew, Characters characters, ImageView imageViewBackground, ImageView imageViewAnswer)
    {
        if(belongingCrew)
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().CORRECT_ANSWER);
        }
        else
        {
            imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        }

        setPictureFromCrew(characters, imageViewAnswer);
    }

    private static void setPictureFromCrew(Characters characters, ImageView imageView)
    {
        switch (characters.getCrew())
        {
            case "Citizen":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_CITIZEN);
                break;
            case "Navy's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_NAVY);
                break;
            case "Ener's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ENER);
                break;
            case "Kaido's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KAIDO);
                break;
            case "Teach's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_TEACH);
                break;
            case "BigMom's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_BIGMOM);
                break;
            case "Cross Guild":
                imageView.setImageResource(PicturesAlbum.getInstance().CROSS_GUILD);
                break;
            case "Shanks's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_SHANKS);
                break;
            case "Newgate's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_NEWGATE);
                break;
            case "Mugiwara's Crew":
            case "Mugiwara's Armada Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_MUGIWARA);
                break;
            case "Revolutionary's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_REVOLUTIONARY_ARMY);
                break;
            case "World Government":
            case "Cipher Pol":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_WORLDGOV);
                break;
            case "Arlong's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ARLONG);
                break;
            case "Baroque Works":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_BW);
                break;
            case "Don Krieg's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KRIEG);
                break;
            case "Moria's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_MORIA);
                break;
            case "Heart's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_LAW);
                break;
            case "Kidd's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KIDD);
                break;
            case "Kuja's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KUJA);
                break;
            case "Caribou's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_CARIBOU);
                break;
            case "Sun Pirates' Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_SUNPIRATES);
                break;
            case "Roger's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ROGER);
                break;
            case "Doflamingo's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_DOFFY);
                break;
            case "Rumbar's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_RUMBAR);
                break;
            case "Fishmen's Crew" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_NEWFISH);
                break;
            case "Giants' Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_GIANTS);
                break;
            case "Celestial Dragons" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_CELESTIAL);
                break;
            case "Impel Down":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ID);
                break;
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
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_DEFAULT);
                break;
        }
    }
}
