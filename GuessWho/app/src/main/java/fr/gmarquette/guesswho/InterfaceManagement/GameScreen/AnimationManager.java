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
            case "Clan d'Ener":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ENER);
                break;
            case "L'Équipage aux Cent Bêtes":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KAIDO);
                break;
            case "L'Équipage de Barbe Noire":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_TEACH);
                break;
            case "L'Équipage de Big Mom":
            case "Équipage de Big Mom":
            case "Famille Charlotte":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_BIGMOM);
                break;
            case "L'Équipage du Clown":
            case "Alliance Baggy et Alvida":
            case "":
            case "Cross Guild":
                imageView.setImageResource(PicturesAlbum.getInstance().CROSS_GUILD);
                break;
            case "L'Équipage du Roux":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_SHANKS);
                break;
            case "L'Équipage de Barbe Blanche":
            case "Subordonné de L'Équipage de Barbe Blanche":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_NEWGATE);
                break;
            case "L'Équipage du Chapeau de Paille":
            case "Allié de L'Équipage du Chapeau de Paille":
            case "La Grande Flotte du Chapeau de Paille":
            case "Alliance de l'Équipage du Chapeau de Paille":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_MUGIWARA);
                break;
            case "Revolutionary's Crew":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_REVOLUTIONARY_ARMY);
                break;
            case "Gouvernement Mondial":
            case "Cipher Pol":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_WORLDGOV);
                break;
            case "L'Équipage d'Arlong":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ARLONG);
                break;
            case "L'Armada Pirate de Don Krieg":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KRIEG);
                break;
            case "Gecko Moria" :
            case "Hogback":
            case "Thriller Bark":
            case "Dracule Mihawk":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_MORIA);
                break;
            case "L'Équipage du Heart" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_LAW);
                break;
            case "L'Équipage de Kid" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KIDD);
                break;
            case "Kujas" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_KUJA);
                break;
            case "Capitaine de l'Equipage de Caribou" :
            case "L'Équipage de Caribou":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_CARIBOU);
                break;
            case "L'Équipage des Pirates du Soleil" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_SUNPIRATES);
                break;
            case "L'Équipage des Pirates Roger" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ROGER);
                break;
            case "L'Équipage du New Age":
            case "L'Équipage de Don Quichotte Doflamingo" :
            case "César Clown (espionnage)":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_DOFFY);
                break;
            case "L'Équipage du Rumbar " :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_RUMBAR);
                break;
            case "L'Équipage des Pirates Volants":
            case "L'Équipage des Nouveaux Hommes-Poissons" :
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_NEWFISH);
                break;
            case "L'Équipage des Géants":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_GIANTS);
                break;
            case "Impel Down":
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_ID);
                break;
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
                imageView.setImageResource(PicturesAlbum.getInstance().CREW_DEFAULT);
                break;
        }
    }
}
