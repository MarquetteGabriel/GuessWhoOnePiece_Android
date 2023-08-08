/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.InterfaceManagement.GameScreen.GameScreenActivity;
import fr.gmarquette.guesswho.R;

public class GameSelectionScreenActivity extends AppCompatActivity {

    private CallDAOAsync callDAOAsync;
    private static final List<String> SUGGESTIONS = new ArrayList<>();
    private final ArrayList<String> arrayList = new ArrayList<>();


    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_selection_screen);

        videoView = (VideoView) findViewById(R.id.video_gear5);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.presentation_gear5);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            mp.start();
        });

        videoView.setOnCompletionListener(mp -> {
            mp.seekTo(0);
            mp.start();
        });

        callDAOAsync = new CallDAOAsync(this);

        SUGGESTIONS.clear();
        arrayList.clear();


        Button button = findViewById(R.id.playButton);
        RadioButton easyRadioButton = findViewById(R.id.easyRadioButton);
        RadioButton hardRadioButton = findViewById(R.id.hardRadioButton);

        button.setOnClickListener(view -> {

            if (easyRadioButton.isChecked())
            {
                getSuggestions(LevelDifficulty.EASY.ordinal());
            }
            else if (hardRadioButton.isChecked())
            {
                getSuggestions(LevelDifficulty.EASY.ordinal());
                getSuggestions(LevelDifficulty.HARD.ordinal());
            }

            arrayList.clear();
            arrayList.addAll(SUGGESTIONS);
            Intent intent = new Intent(getApplicationContext(), GameScreenActivity.class);
            intent.putStringArrayListExtra("Suggestions", arrayList);
            startActivity(intent);
            finish();
        });
    }

    private void getSuggestions(int level)
    {
        try {
            SUGGESTIONS.addAll(callDAOAsync.getNamesByDifficultyAsync(level).get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickSwitchRadioButtons(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.easyRadioButton:
                if(checked)
                {
                    RadioButton hardRadioButton = findViewById(R.id.hardRadioButton);
                    hardRadioButton.setChecked(false);
                }
                break;
            case R.id.hardRadioButton:
                if(checked)
                {
                    RadioButton easyRadioButton = findViewById(R.id.easyRadioButton);
                    easyRadioButton.setChecked(false);
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.suspend();
    }
}