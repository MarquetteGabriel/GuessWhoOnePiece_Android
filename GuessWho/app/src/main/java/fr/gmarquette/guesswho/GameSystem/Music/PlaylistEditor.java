/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem.Music;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.gmarquette.guesswho.R;

public class PlaylistEditor
{
    MediaPlayer mediaPlayer;
    private int indTrack;
    Context context;

    List<Integer> playlist = new ArrayList<>();
    ExecutorService executorService;

    public PlaylistEditor(Context context)
    {
        executorService = Executors.newSingleThreadExecutor();
        this.context = context;
        indTrack = 0;
        init();
    }

    private void play(int trackIndex)
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, playlist.get(trackIndex));
            mediaPlayer.setVolume(50, 50);
            mediaPlayer.setNextMediaPlayer(MediaPlayer.create(context, playlist.get(trackIndex+1)));
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, playlist.get(trackIndex));
            mediaPlayer.start();
        }
    }

    private void init()
    {
        playlist.add(R.raw.bs_ado_binksunosake);
        playlist.add(R.raw.bs_ado_im_invincible);
        playlist.add(R.raw.bs_ado_backlight);
        playlist.add(R.raw.bs_ado_newgenesis);
        playlist.add(R.raw.bs_ado_totmusica);
        playlist.add(R.raw.bs_ed_un_memories);
        playlist.add(R.raw.bs_fierce_attack);
        playlist.add(R.raw.bs_it_awakens);
        playlist.add(R.raw.bs_katakuri_system_defence_deploy);
        playlist.add(R.raw.bs_overtaken);
    }

    public void start()
    {
        executorService.submit(() ->
        {
            if(indTrack == this.playlist.size())
            {
                indTrack = 0;
                play(indTrack);
            }
            else
            {
                play(indTrack);
            }
        });
    }

    public void playOpening()
    {
        mediaPlayer = MediaPlayer.create(context, R.raw.bs_op_quinze_wego);
        mediaPlayer.start();
    }

    public void stop()
    {
        mediaPlayer.stop();
    }
}
