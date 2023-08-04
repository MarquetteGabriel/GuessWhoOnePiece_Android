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
import fr.gmarquette.guesswho.R;

public class AnimationManager
{
    private static final ImageView ARROW_UP = null;
    private static final ImageView ARROW_DOWN = null;
    private static final ImageView BLANK_ANSWER = null;
    private static final ImageView CORRECT_ANSWER = null;

    public static void setImages()
    {
        BLANK_ANSWER.setImageResource(R.drawable.gray_circle);
        CORRECT_ANSWER.setImageResource(R.drawable.green_mark);
        ARROW_DOWN.setImageResource(R.drawable.gray_circle);
        ARROW_UP.setImageResource(R.drawable.gray_circle);
    }

    public static void updateFruit(boolean eatenFruit, Characters characters, ImageView imageView)
    {
        if (characters.hasDevilFruit())
        {
            imageView.setImageResource(R.drawable.green_mark);
        }
        else
        {
            imageView.setImageResource(R.drawable.green_mark);
        }

        if(eatenFruit)
        {
            imageView = CORRECT_ANSWER;
        }
        else
        {
            imageView.setImageResource(R.drawable.gray_circle);
        }
    }

    public static void updateBounty(BountyType bountyType, Characters characters, ImageView imageView, TextView textView)
    {
        String bounty = characters.getBounty();
        switch (bountyType)
        {
            case EQUAL:
            case CORRECT_NO_RESEARCHED:
            case CORRECT_UNKNOWN:
                imageView = CORRECT_ANSWER;
                textView.setText(bounty);
                break;
            case UPPER: // Arrow Down
                imageView = ARROW_DOWN;
                textView.setText(bounty);
                break;
            case LOWER: // Arrow Up
                imageView = ARROW_UP;
                textView.setText(bounty);
                break;
            case UNKNOWN:
            case NO_RESEARCHED:
                imageView.setImageResource(R.drawable.gray_circle);
                textView.setText(bounty);
                break;
            default:
                break;
        }
    }

    public static void updateAge(AgeType ageType, Characters characters, ImageView imageView, TextView textView)
    {
        String age = String.valueOf(characters.getAge());
        switch (ageType)
        {
            case YOUNGER: // Arrow above
                imageView.setImageResource(R.drawable.gray_circle);
                textView.setText(age);
                break;
            case OLDER: // Arrow down
                imageView.setImageResource(R.drawable.back);
                textView.setText(age);
                break;
            case EQUAL:
                imageView = CORRECT_ANSWER;
                textView.setText(age);
                break;
            default:
                break;
        }
    }

    public static void updateChapter(ChapterType chapterType, Characters characters, ImageView imageView, TextView textView)
    {
        String chapter = String.valueOf(characters.getFirstAppearance());
        switch (chapterType)
        {
            case NEWER_CHAPTER: // Arrow down
                imageView.setImageResource(R.drawable.gray_circle);
                textView.setText(chapter);
                break;
            case PREVIOUS_CHAPTER: // Arrow above
                imageView.setImageResource(R.drawable.back);
                textView.setText(chapter);
                break;
            case SAME_CHAPTER:
                imageView = CORRECT_ANSWER;
                textView.setText(chapter);
                break;
            default:
                break;
        }
    }

    public static void updateType(boolean getType, Characters characters, ImageView imageView)
    {
        switch (characters.getType())
        {
            case PIRATE:
                imageView.setImageResource(R.drawable.green_mark);
                break;
            case REVOLUTIONARY:
                imageView.setImageResource(R.drawable.green_mark);
                break;
            case NAVY:
                imageView.setImageResource(R.drawable.green_mark);
                break;
            case CITIZEN:
                imageView.setImageResource(R.drawable.green_mark);
                break;
            default:
                break;
        }

        if (getType)
        {
            imageView = CORRECT_ANSWER;
        }
        else
        {
            imageView.setImageResource(R.drawable.gray_circle);
        }
    }

    public static void updateAlive(boolean alived, Characters characters, ImageView imageView)
    {
        if (characters.isAlive())
        {
            imageView.setImageResource(R.drawable.green_mark);
        }
        else
        {
            imageView.setImageResource(R.drawable.green_mark);
        }

        if(alived)
        {
            imageView = CORRECT_ANSWER;

        }
        else
        {
            imageView.setImageResource(R.drawable.gray_circle);
        }
    }

    public static void updateCrew(boolean belongingCrew, Characters characters, ImageView imageView)
    {
        if(belongingCrew)
        {
            imageView = CORRECT_ANSWER;
        }
        else
        {
            imageView.setImageResource(R.drawable.gray_circle);
        }

        setPictureFromCrew(characters, imageView);
    }

    private static void setPictureFromCrew(Characters characters, ImageView imageView)
    {
        switch (characters.getCrew())
        {
            case CITIZEN:
                break;
            case NAVY:
                break;
            case ENER_CREW:
                break;
            case KAIDO_CREW:
                break;
            case TEACH_CREW:
                break;
            case BIGMOM_CREW:
                break;
            case CROSS_GUILD:
                break;
            case SHANKS_CREW:
                break;
            case NEWGATE_CREW:
                imageView.setImageResource(R.drawable.crew_newgate);
                break;
            case MUGIWARA_CREW:
                imageView.setImageResource(R.drawable.crew_mugiwara);
                break;
            case REVOLUTIONARY_ARMY:
                break;
            default:
                break;
        }
    }
}
