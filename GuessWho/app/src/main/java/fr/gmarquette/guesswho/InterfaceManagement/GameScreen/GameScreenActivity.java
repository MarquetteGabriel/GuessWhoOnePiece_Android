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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.GameSystem.GameManager;
import fr.gmarquette.guesswho.R;

public class GameScreenActivity extends AppCompatActivity {

    public static int NUMBER_GUESSED = 0;
    Characters characterToFind;
    private CallDAOAsync callDAOAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        Intent intent = getIntent();
        ArrayList<String> suggestions = intent.getStringArrayListExtra("Suggestions");
        DataBase.getInstance(this);
        callDAOAsync = new CallDAOAsync(this);

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
            getAnswer(selectedValue);
            autoCompleteTextView.setText("");
        });

        NUMBER_GUESSED = 0;
    }

    public void getAnswer(String selectedValue)
    {
        NUMBER_GUESSED++;
        boolean result = answerToRequest(selectedValue);
        if(result)
        {
            // TODO : Show PopUp of Win
        }
        else if(NUMBER_GUESSED == 6)
        {
            // TODO : Show PopUp of Loose
        }
    }

    public boolean answerToRequest(String selectedValue)
    {
        Characters character;
        try {
            character = callDAOAsync.getCharacterAsync(selectedValue).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        String stringAlive = GameManager.isAlive(character, characterToFind);
        String stringAge = GameManager.getAge(character, characterToFind);
        String stringBounty = GameManager.lookingForBounty(character, characterToFind);
        String stringFruit = GameManager.hasEatenDevilFruit(character, characterToFind);
        String stringChapter = GameManager.getAppearanceDiff(character, characterToFind);
        String stringType = GameManager.getType(character, characterToFind);
        String stringCrew = GameManager.getCrew(character, characterToFind);

        TextView textView0 = findViewById(R.id.textViewCharacter0);
        TextView textView1 = findViewById(R.id.textViewCharacter1);
        TextView textView2 = findViewById(R.id.textViewCharacter2);
        TextView textView3 = findViewById(R.id.textViewCharacter3);
        TextView textView4 = findViewById(R.id.textViewCharacter4);
        TextView textView5 = findViewById(R.id.textViewCharacter5);

        switch (NUMBER_GUESSED)
        {
            case 1:
                textView0.setText(selectedValue);
                break;
            case 2:
                textView1.setText(selectedValue);
                break;
            case 3:
                textView2.setText(selectedValue);
                break;
            case 4:
                textView3.setText(selectedValue);
                break;
            case 5:
                textView4.setText(selectedValue);
                break;
            case 6:
                textView5.setText(selectedValue);
                break;
        }



        return GameManager.isSameName(character, characterToFind);
    }

}