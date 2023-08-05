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
import fr.gmarquette.guesswho.GameSystem.AgeType;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyType;
import fr.gmarquette.guesswho.GameSystem.ChapterType;
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
    private PicturesAlbum picturesAlbum;

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

        picturesAlbum = PicturesAlbum.getInstance();
        picturesAlbum.setImages();

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

        setCircleOfAnswer();
        boolean hasFruit = GameManager.hasEatenDevilFruit(character, characterToFind);
        BountyType bounty = GameManager.lookingForBounty(character, characterToFind);
        ChapterType chapter = GameManager.getAppearanceDiff(character, characterToFind);
        boolean type = GameManager.getType(character, characterToFind);
        boolean alive = GameManager.isAlive(character, characterToFind);
        AgeType age = GameManager.getAge(character, characterToFind);
        boolean crew = GameManager.getCrew(character, characterToFind);

        switch (NUMBER_GUESSED)
        {
            case 1:
                textViewCharacterName0.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty0);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter0);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_1), findViewById(R.id.age_text_1));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
                break;
            case 2:
                textViewCharacterName1.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty1);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter1);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_2), findViewById(R.id.age_text_2));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
                break;
            case 3:
                textViewCharacterName2.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty2);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter2);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_3), findViewById(R.id.age_text_3));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
                break;
            case 4:
                textViewCharacterName3.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty3);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter3);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_4), findViewById(R.id.age_text_4));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
                break;
            case 5:
                textViewCharacterName4.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty4);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter4);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_5), findViewById(R.id.age_text_5));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
                break;
            case 6:
                textViewCharacterName5.setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.age_wr_circle_1), textViewBounty5);
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.age_wr_circle_1), textViewChapter5);
                AnimationManager.updateType(type, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.age_wr_circle_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_6), findViewById(R.id.age_text_6));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.age_wr_circle_1));
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

    private void setCircleOfAnswer()
    {
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

        textViewAge0 = findViewById(R.id.age_text_1);
        textViewAge1 = findViewById(R.id.age_text_2);
        textViewAge2 = findViewById(R.id.age_text_3);
        textViewAge3 = findViewById(R.id.age_text_4);
        textViewAge4 = findViewById(R.id.age_text_5);
        textViewAge5 = findViewById(R.id.age_text_6);

        textViewCrew0 = findViewById(R.id.textviewCrew1);
        textViewCrew1 = findViewById(R.id.textviewCrew2);
        textViewCrew2 = findViewById(R.id.textviewCrew3);
        textViewCrew3 = findViewById(R.id.textviewCrew4);
        textViewCrew4 = findViewById(R.id.textviewCrew5);
        textViewCrew5 = findViewById(R.id.textviewCrew6);
    }
}