/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.GameSystem.GameManager;
import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.GameSelectionScreenActivity;
import fr.gmarquette.guesswho.R;

public class GameScreenActivity extends AppCompatActivity {

    public static int NUMBER_GUESSED = 0;
    Characters characterToFind;
    private CallDAOAsync callDAOAsync;
    private GameInit gameInit;

    TextView textViewCharacterName0, textViewCharacterName1, textViewCharacterName2, textViewCharacterName3, textViewCharacterName4, textViewCharacterName5;
    TextView textViewDevilFruit0, textViewDevilFruit1, textViewDevilFruit2, textViewDevilFruit3, textViewDevilFruit4, textViewDevilFruit5;
    TextView textViewBounty0, textViewBounty1, textViewBounty2, textViewBounty3, textViewBounty4, textViewBounty5;
    TextView textViewChapter0, textViewChapter1, textViewChapter2, textViewChapter3, textViewChapter4, textViewChapter5;
    TextView textViewType0, textViewType1, textViewType2, textViewType3, textViewType4, textViewType5;
    TextView textViewAlive0, textViewAlive1, textViewAlive2, textViewAlive3, textViewAlive4, textViewAlive5;
    TextView textViewAge0, textViewAge1, textViewAge2, textViewAge3, textViewAge4, textViewAge5;
    TextView textViewCrew0, textViewCrew1, textViewCrew2, textViewCrew3, textViewCrew4, textViewCrew5;

    private ArrayList<String> suggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        Intent intent = getIntent();
        suggestions = intent.getStringArrayListExtra("Suggestions");
        DataBase.getInstance(this);
        callDAOAsync = new CallDAOAsync(this);

        gameInit = new GameInit(this);
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
            displayWinDialog();
        }
        else if(NUMBER_GUESSED == 6)
        {
            displayLooseDialog();
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

        textViewCharacterName0 = findViewById(R.id.textViewCharacter0);
        textViewCharacterName1 = findViewById(R.id.textViewCharacter1);
        textViewCharacterName2 = findViewById(R.id.textViewCharacter2);
        textViewCharacterName3 = findViewById(R.id.textViewCharacter3);
        textViewCharacterName4 = findViewById(R.id.textViewCharacter4);
        textViewCharacterName5 = findViewById(R.id.textViewCharacter5);

        textViewDevilFruit0 = findViewById(R.id.textviewFruit1);
        textViewDevilFruit1 = findViewById(R.id.textviewFruit2);
        textViewDevilFruit2 = findViewById(R.id.textviewFruit3);
        textViewDevilFruit3 = findViewById(R.id.textviewFruit4);
        textViewDevilFruit4 = findViewById(R.id.textviewFruit5);
        textViewDevilFruit5 = findViewById(R.id.textviewFruit6);

        textViewBounty0 = findViewById(R.id.textviewBounty1);
        textViewBounty1 = findViewById(R.id.textviewBounty2);
        textViewBounty2 = findViewById(R.id.textviewBounty3);
        textViewBounty3 = findViewById(R.id.textviewBounty4);
        textViewBounty4 = findViewById(R.id.textviewBounty5);
        textViewBounty5 = findViewById(R.id.textviewBounty6);

        textViewChapter0 = findViewById(R.id.textviewChapter1);
        textViewChapter1 = findViewById(R.id.textviewChapter2);
        textViewChapter2 = findViewById(R.id.textviewChapter3);
        textViewChapter3 = findViewById(R.id.textviewChapter4);
        textViewChapter4 = findViewById(R.id.textviewChapter5);
        textViewChapter5 = findViewById(R.id.textviewChapter6);

        textViewType0 = findViewById(R.id.textviewType1);
        textViewType1 = findViewById(R.id.textviewType2);
        textViewType2 = findViewById(R.id.textviewType3);
        textViewType3 = findViewById(R.id.textviewType4);
        textViewType4 = findViewById(R.id.textviewType5);
        textViewType5 = findViewById(R.id.textviewType6);

        textViewAlive0 = findViewById(R.id.textviewAlive1);
        textViewAlive1 = findViewById(R.id.textviewAlive2);
        textViewAlive2 = findViewById(R.id.textviewAlive3);
        textViewAlive3 = findViewById(R.id.textviewAlive4);
        textViewAlive4 = findViewById(R.id.textviewAlive5);
        textViewAlive5 = findViewById(R.id.textviewAlive6);

        textViewAge0 = findViewById(R.id.textviewAge1);
        textViewAge1 = findViewById(R.id.textviewAge2);
        textViewAge2 = findViewById(R.id.textviewAge3);
        textViewAge3 = findViewById(R.id.textviewAge4);
        textViewAge4 = findViewById(R.id.textviewAge5);
        textViewAge5 = findViewById(R.id.textviewAge6);

        textViewCrew0 = findViewById(R.id.textviewCrew1);
        textViewCrew1 = findViewById(R.id.textviewCrew2);
        textViewCrew2 = findViewById(R.id.textviewCrew3);
        textViewCrew3 = findViewById(R.id.textviewCrew4);
        textViewCrew4 = findViewById(R.id.textviewCrew5);
        textViewCrew5 = findViewById(R.id.textviewCrew6);

        switch (NUMBER_GUESSED)
        {
            case 1:
                textViewCharacterName0.setText(selectedValue);
                textViewDevilFruit0.setText(stringFruit);
                textViewBounty0.setText(stringBounty);
                textViewChapter0.setText(stringChapter);
                textViewType0.setText(stringType);
                textViewAlive0.setText(stringAlive);
                textViewAge0.setText(stringAge);
                textViewCrew0.setText(stringCrew);
                break;
            case 2:
                textViewCharacterName1.setText(selectedValue);
                textViewDevilFruit1.setText(stringFruit);
                textViewBounty1.setText(stringBounty);
                textViewChapter1.setText(stringChapter);
                textViewType1.setText(stringType);
                textViewAlive1.setText(stringAlive);
                textViewAge1.setText(stringAge);
                textViewCrew1.setText(stringCrew);
                break;
            case 3:
                textViewCharacterName2.setText(selectedValue);
                textViewDevilFruit2.setText(stringFruit);
                textViewBounty2.setText(stringBounty);
                textViewChapter2.setText(stringChapter);
                textViewType2.setText(stringType);
                textViewAlive2.setText(stringAlive);
                textViewAge2.setText(stringAge);
                textViewCrew2.setText(stringCrew);
                break;
            case 4:
                textViewCharacterName3.setText(selectedValue);
                textViewDevilFruit3.setText(stringFruit);
                textViewBounty3.setText(stringBounty);
                textViewChapter3.setText(stringChapter);
                textViewType3.setText(stringType);
                textViewAlive3.setText(stringAlive);
                textViewAge3.setText(stringAge);
                textViewCrew3.setText(stringCrew);
                break;
            case 5:
                textViewCharacterName4.setText(selectedValue);
                textViewDevilFruit4.setText(stringFruit);
                textViewBounty4.setText(stringBounty);
                textViewChapter4.setText(stringChapter);
                textViewType4.setText(stringType);
                textViewAlive4.setText(stringAlive);
                textViewAge4.setText(stringAge);
                textViewCrew4.setText(stringCrew);
                break;
            case 6:
                textViewCharacterName5.setText(selectedValue);
                textViewDevilFruit5.setText(stringFruit);
                textViewBounty5.setText(stringBounty);
                textViewChapter5.setText(stringChapter);
                textViewType5.setText(stringType);
                textViewAlive5.setText(stringAlive);
                textViewAge5.setText(stringAge);
                textViewCrew5.setText(stringCrew);
                break;
        }

        return GameManager.isSameName(character, characterToFind);
    }

    public void restart()
    {
        clearText();
        try {
            characterToFind = gameInit.getCharacterToFound(suggestions);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        NUMBER_GUESSED = 0;
    }

    private void clearText()
    {
        textViewCharacterName0.setText("");
        textViewDevilFruit0.setText("");
        textViewBounty0.setText("");
        textViewChapter0.setText("");
        textViewType0.setText("");
        textViewAlive0.setText("");
        textViewAge0.setText("");
        textViewCrew0.setText("");

        textViewCharacterName1.setText("");
        textViewDevilFruit1.setText("");
        textViewBounty1.setText("");
        textViewChapter1.setText("");
        textViewType1.setText("");
        textViewAlive1.setText("");
        textViewAge1.setText("");
        textViewCrew1.setText("");

        textViewCharacterName2.setText("");
        textViewDevilFruit2.setText("");
        textViewBounty2.setText("");
        textViewChapter2.setText("");
        textViewType2.setText("");
        textViewAlive2.setText("");
        textViewAge2.setText("");
        textViewCrew2.setText("");

        textViewCharacterName3.setText("");
        textViewDevilFruit3.setText("");
        textViewBounty3.setText("");
        textViewChapter3.setText("");
        textViewType3.setText("");
        textViewAlive3.setText("");
        textViewAge3.setText("");
        textViewCrew3.setText("");

        textViewCharacterName4.setText("");
        textViewDevilFruit4.setText("");
        textViewBounty4.setText("");
        textViewChapter4.setText("");
        textViewType4.setText("");
        textViewAlive4.setText("");
        textViewAge4.setText("");
        textViewCrew4.setText("");

        textViewCharacterName5.setText("");
        textViewDevilFruit5.setText("");
        textViewBounty5.setText("");
        textViewChapter5.setText("");
        textViewType5.setText("");
        textViewAlive5.setText("");
        textViewAge5.setText("");
        textViewCrew5.setText("");
    }

    private void displayWinDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        yesButton.setOnClickListener(view -> {
            restart();
            dialog.dismiss();
        });

        noButton.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), GameSelectionScreenActivity.class);
            startActivity(intent);
        });

        dialog.show();
    }

    private void displayLooseDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        yesButton.setOnClickListener(view -> {
            restart();
            dialog.dismiss();
        });

        noButton.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), GameSelectionScreenActivity.class);
            startActivity(intent);
        });
    }

}