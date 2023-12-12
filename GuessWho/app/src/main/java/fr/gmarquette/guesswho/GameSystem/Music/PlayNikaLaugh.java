/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem.Music;

import android.media.MediaPlayer;
import android.view.View;

import fr.gmarquette.guesswho.R;

public class PlayNikaLaugh
{
    private static final int volume = 50;
    private static MediaPlayer mediaPlayer;
    public static void play(View view)
    {
        mediaPlayer = MediaPlayer.create(view.getContext(), R.raw.nika_laugh);
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.start();
    }

    public static void stop()
    {
        mediaPlayer.stop();
    }
}