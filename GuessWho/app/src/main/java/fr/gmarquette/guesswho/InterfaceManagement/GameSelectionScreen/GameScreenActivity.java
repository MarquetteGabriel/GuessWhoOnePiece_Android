/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen;

import static fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen.SplashLoadingScreen.SUGGESTIONS;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Characters.InitialiseDatabase;
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.InterfaceManagement.GameScreen.GameScreenViewModel;
import fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen.SplashLoadingScreen;
import fr.gmarquette.guesswho.R;

public class GameScreenActivity extends AppCompatActivity {

    public static int NUMBER_GUESSED = 0;
    private GameScreenViewModel gameScreenViewModel;
    private GameInit gameInit;
    private CallDAOAsync callDAOAsync;
    List<String> suggestions;
    Characters characterToFind;

    private DataBase dataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        //GameScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.game_screen);
        //binding.setViewmodel(this.gameScreenViewModel);
        //binding.setLifecycleOwner(this);
        dataBase = DataBase.getInstance(this);
        callDAOAsync = new CallDAOAsync(this);

        InitialiseDatabase initialiseDatabase = new InitialiseDatabase();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        callDAOAsync.getAddElementsAsync(initialiseDatabase.getDatabaseValues());

        gameInit = new GameInit(this);
        characterToFind = gameInit.getCharacterToFound();

        suggestions = SUGGESTIONS;


        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.EnterTextAutoCompleted);


        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedValue = autoCompleteTextView.getAdapter().getItem(position).toString();
            autoCompleteTextView.setText(selectedValue);
            updateFragments(selectedValue);
        });
*/
        NUMBER_GUESSED = 0;
        initialiseDatabase.ClearList();
    }


    void updateText(String newText, TextView textView)
    {
        if(textView != null)
        {
            textView.setText(newText);
        }
    }

}