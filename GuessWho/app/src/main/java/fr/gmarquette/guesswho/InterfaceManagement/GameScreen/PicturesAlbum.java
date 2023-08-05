/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.widget.ImageView;

import fr.gmarquette.guesswho.R;

public class PicturesAlbum
{
    public ImageView ARROW_UP, ARROW_DOWN;
    public ImageView BLANK_ANSWER, CORRECT_ANSWER;
    public ImageView CREW_KAIDO, CREW_MUGIWARA, CREW_NEWGATE, CREW_SHANKS, CREW_TEACH, CROSS_GUILD,
            CREW_BIGMOM, CREW_ENER, NAVY, REVOLUTIONARY_ARMY, CITIZEN;
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
        this.BLANK_ANSWER.setImageResource(R.drawable.gray_circle);
        this.CORRECT_ANSWER.setImageResource(R.drawable.green_mark);

        this.ARROW_DOWN.setImageResource(R.drawable.gray_circle);
        this.ARROW_UP.setImageResource(R.drawable.gray_circle);

        setImagesOfCrew();
    }

    private void setImagesOfCrew()
    {
        CREW_KAIDO.setImageResource(R.drawable.crew_kaido);
        CREW_MUGIWARA.setImageResource(R.drawable.crew_mugiwara);
        CREW_NEWGATE.setImageResource(R.drawable.crew_newgate);
        CREW_SHANKS.setImageResource(R.drawable.crew_shanks);
        CREW_TEACH.setImageResource(R.drawable.crew_teach);
        CROSS_GUILD.setImageResource(R.drawable.crew_cross_guild);
    }
}
