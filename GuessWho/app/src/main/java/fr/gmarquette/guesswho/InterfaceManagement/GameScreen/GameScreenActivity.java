/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.R;

public class GameScreenActivity extends AppCompatActivity {

    public static int NUMBER_GUESSED = 0;
    Characters characterToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        Intent intent = getIntent();
        ArrayList<String> suggestions = intent.getStringArrayListExtra("Suggestions");
        DataBase.getInstance(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        GameInit gameInit = new GameInit(this);
        try {
            characterToFind = gameInit.getCharacterToFound(suggestions);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);


        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedValue = autoCompleteTextView.getAdapter().getItem(position).toString();
            autoCompleteTextView.setText(selectedValue);
        });

        NUMBER_GUESSED = 0;
    }

}