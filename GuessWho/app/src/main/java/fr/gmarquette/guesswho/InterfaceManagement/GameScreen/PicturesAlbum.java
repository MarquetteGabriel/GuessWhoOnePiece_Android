/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import fr.gmarquette.guesswho.R;

public class PicturesAlbum
{
    public int ARROW_UP, ARROW_DOWN;
    public int WRONG_ANSWER, CORRECT_ANSWER, EMPTY_ANSWER;
    public int PIRATES, REVOLUTIONARY_ARMY, NAVY_GOVERNMENT, CITIZENS;
    public int DEVIL_FRUIT, NO_DEVIL_FRUIT;
    public int ALIVE, NOT_ALIVE;
    public int CREW_KAIDO, CREW_MUGIWARA, CREW_NEWGATE, CREW_SHANKS, CREW_TEACH, CROSS_GUILD,
            CREW_BIGMOM, CREW_ENER, CREW_NAVY, CREW_REVOLUTIONARY_ARMY, CREW_CITIZEN;
    private PicturesAlbum()
    {}

    private static class PicturesAlbumHolder
    {
        private final static PicturesAlbum instance = new PicturesAlbum();
    }

    public static PicturesAlbum getInstance()
    {
        return PicturesAlbumHolder.instance;
    }

    public void setImages()
    {
        WRONG_ANSWER = R.drawable.gray_circle;
        CORRECT_ANSWER = R.drawable.green_mark;
        EMPTY_ANSWER = R.drawable.empty_circle;

        ARROW_DOWN = R.drawable.arrow_down;
        ARROW_UP = R.drawable.arrow_up;

        DEVIL_FRUIT = R.drawable.fruit;
        NO_DEVIL_FRUIT = R.drawable.no_fruit;

        ALIVE = R.drawable.vie;
        NOT_ALIVE = R.drawable.mort;

        setImagesOfCrew();
        setImagesOfTypes();
    }

    private void setImagesOfCrew()
    {
        CREW_KAIDO = R.drawable.crew_kaido;
        CREW_MUGIWARA = R.drawable.crew_mugiwara;
        CREW_NEWGATE= R.drawable.crew_newgate;
        CREW_SHANKS= R.drawable.crew_shanks;
        CREW_TEACH = R.drawable.crew_teach;
        CROSS_GUILD = R.drawable.crew_cross_guild;
        CREW_BIGMOM = R.drawable.crew_bigmom;
        CREW_ENER = R.drawable.crew_ener;
        CREW_NAVY = R.drawable.crew_navy;
        CREW_REVOLUTIONARY_ARMY = R.drawable.crew_revolutionary_army;
        CREW_CITIZEN = R.drawable.crew_citizen;
    }

    private void setImagesOfTypes()
    {
        PIRATES = R.drawable.crew_mugiwara;
        REVOLUTIONARY_ARMY = R.drawable.crew_revolutionary_army;
        NAVY_GOVERNMENT = R.drawable.crew_navy;
        CITIZENS = R.drawable.crew_citizen;
    }
}
