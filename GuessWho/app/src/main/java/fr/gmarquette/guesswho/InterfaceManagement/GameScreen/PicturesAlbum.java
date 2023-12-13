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
            CREW_BIGMOM, CREW_ENER, CREW_NAVY, CREW_REVOLUTIONARY_ARMY, CREW_CITIZEN, CREW_ARLONG,
            CREW_BW, CREW_CARIBOU, CREW_CELESTIAL, CREW_DOFFY, CREW_GIANTS, CREW_ID, CREW_KIDD,
            CREW_KRIEG, CREW_KUJA, CREW_LAW, CREW_MORIA, CREW_NEWFISH, CREW_ROGER, CREW_RUMBAR,
            CREW_SUNPIRATES, CREW_WORLDGOV, CREW_DEFAULT, CREW_IDEO, CREW_FOXY, CREW_CHATNOIR,
            CREW_WEEBLE, CREW_HAWKINS, CREW_APOO, CREW_PRIMATES, CREW_UROUGE, CREW_SHIKI,
            CREW_FIRETANK, CREW_BONNEY, CREW_XBARRELS;
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
        CREW_ARLONG = R.drawable.crew_arlong;
        CREW_BW = R.drawable.crew_baroqueworks;
        CREW_CARIBOU = R.drawable.crew_caribou;
        CREW_CELESTIAL = R.drawable.crew_celestial_dragons;
        CREW_DOFFY = R.drawable.crew_doffy;
        CREW_GIANTS = R.drawable.crew_giants;
        CREW_ID = R.drawable.crew_impeldown;
        CREW_KIDD = R.drawable.crew_kidd;
        CREW_KRIEG = R.drawable.crew_krieg;
        CREW_KUJA = R.drawable.crew_kuja;
        CREW_LAW = R.drawable.crew_law;
        CREW_MORIA = R.drawable.crew_moria;
        CREW_NEWFISH = R.drawable.crew_newfish;
        CREW_ROGER = R.drawable.crew_roger;
        CREW_RUMBAR = R.drawable.crew_rumbar;
        CREW_SUNPIRATES = R.drawable.crew_sunpirates;
        CREW_APOO = R.drawable.crew_apoo;
        CREW_FOXY = R.drawable.crew_foxy;
        CREW_CHATNOIR = R.drawable.crew_chatnoir;
        CREW_WEEBLE = R.drawable.crew_weeble;
        CREW_HAWKINS = R.drawable.crew_hawkins;
        CREW_IDEO = R.drawable.crew_ideo;
        CREW_PRIMATES = R.drawable.crew_singes;
        CREW_UROUGE = R.drawable.crew_urouge;
        CREW_SHIKI = R.drawable.crew_shiki;
        CREW_FIRETANK = R.drawable.crew_bege;
        CREW_BONNEY = R.drawable.crew_bonney;
        CREW_XBARRELS = R.drawable.crew_xbarrels;
        CREW_WORLDGOV = R.drawable.crew_world_government;
        CREW_DEFAULT = R.drawable.crew_default;

    }

    private void setImagesOfTypes()
    {
        PIRATES = R.drawable.crew_mugiwara;
        REVOLUTIONARY_ARMY = R.drawable.crew_revolutionary_army;
        NAVY_GOVERNMENT = R.drawable.crew_navy;
        CITIZENS = R.drawable.crew_citizen;
    }
}
