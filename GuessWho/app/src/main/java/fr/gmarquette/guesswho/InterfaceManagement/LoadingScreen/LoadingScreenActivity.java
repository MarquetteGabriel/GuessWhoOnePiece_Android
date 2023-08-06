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
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Characters.InitialiseDatabase;
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.GameSelectionScreenActivity;
import fr.gmarquette.guesswho.R;

public class LoadingScreenActivity extends AppCompatActivity {

    private static final int LOADING_TIME = 2000;
    private CallDAOAsync callDAOAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_loading_screen);

        callDAOAsync = new CallDAOAsync(getApplicationContext());
        callDAOAsync.deleteAllAsync();
        DataBase.getInstance(getApplicationContext());
        possibleAddElements();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), GameSelectionScreenActivity.class);
            startActivity(intent);
        }, LOADING_TIME);
    }

    private void possibleAddElements()
    {
        try {
            if(callDAOAsync.getCountAsync().get() == 0)
            {
                callDAOAsync.deleteAllAsync();
                callDAOAsync.getAddElementsAsync(InitialiseDatabase.getDatabaseValues());
            }
            else if(callDAOAsync.getCountAsync().get() < InitialiseDatabase.getDatabaseValues().size())
            {
                callDAOAsync.deleteAllAsync();
                callDAOAsync.getAddElementsAsync(InitialiseDatabase.getDatabaseValues());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}