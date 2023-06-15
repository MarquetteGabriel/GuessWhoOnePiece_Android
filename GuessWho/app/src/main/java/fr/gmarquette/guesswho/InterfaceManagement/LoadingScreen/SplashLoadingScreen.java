/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.GameScreenActivity;
import fr.gmarquette.guesswho.R;

public class SplashLoadingScreen extends AppCompatActivity {

    private static int LOADING_TIME = 5000;
    private CallDAOAsync callDAOAsync;
    public static List<String> SUGGESTIONS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_loading_screen);

        callDAOAsync = new CallDAOAsync(getApplicationContext());
        DataBase.getInstance(getApplicationContext());
        getSuggestions();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), GameScreenActivity.class));
                finish();
            }
        }, LOADING_TIME);
    }

    private void getSuggestions()
    {
        try {
            SUGGESTIONS = (callDAOAsync.getNamesAsync().get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}