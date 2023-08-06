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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
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

    private ArrayList<String> suggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        PicturesAlbum.getInstance().setImages();

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
                ((TextView)findViewById(R.id.guess_1)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_1), findViewById(R.id.fruit_answer_1));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_1), findViewById(R.id.bounty_answer_1), findViewById(R.id.bounty_text_1));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_1), findViewById(R.id.chapter_answer_1), findViewById(R.id.chapter_text_1));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_1), findViewById(R.id.type_answer_1));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_1), findViewById(R.id.alive_answer_1));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_1),  findViewById(R.id.age_answer_1), findViewById(R.id.age_text_1));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_1), findViewById(R.id.crew_answer_1));
                break;
            case 2:
                ((TextView)findViewById(R.id.guess_2)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_2), findViewById(R.id.fruit_answer_2));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_2), findViewById(R.id.bounty_answer_2), findViewById(R.id.bounty_text_2));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_2), findViewById(R.id.chapter_answer_2), findViewById(R.id.chapter_text_2));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_2), findViewById(R.id.type_answer_2));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_2), findViewById(R.id.alive_answer_2));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_2),  findViewById(R.id.age_answer_2), findViewById(R.id.age_text_2));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_2), findViewById(R.id.crew_answer_2));
                break;
            case 3:
                ((TextView)findViewById(R.id.guess_3)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_3), findViewById(R.id.fruit_answer_3));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_3), findViewById(R.id.bounty_answer_3), findViewById(R.id.bounty_text_3));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_3), findViewById(R.id.chapter_answer_3), findViewById(R.id.chapter_text_3));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_3), findViewById(R.id.type_answer_3));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_3), findViewById(R.id.alive_answer_3));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_3),  findViewById(R.id.age_answer_3), findViewById(R.id.age_text_3));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_3), findViewById(R.id.crew_answer_3));
                break;
            case 4:
                ((TextView)findViewById(R.id.guess_4)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_4), findViewById(R.id.fruit_answer_4));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_4), findViewById(R.id.bounty_answer_4), findViewById(R.id.bounty_text_4));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_4), findViewById(R.id.chapter_answer_4), findViewById(R.id.chapter_text_4));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_4), findViewById(R.id.type_answer_4));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_4), findViewById(R.id.alive_answer_4));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_4),  findViewById(R.id.age_answer_4), findViewById(R.id.age_text_4));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_4), findViewById(R.id.crew_answer_4));
                break;
            case 5:
                ((TextView)findViewById(R.id.guess_5)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_5), findViewById(R.id.fruit_answer_5));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_5), findViewById(R.id.bounty_answer_5), findViewById(R.id.bounty_text_5));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_5), findViewById(R.id.chapter_answer_5), findViewById(R.id.chapter_text_5));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_5), findViewById(R.id.type_answer_5));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_5), findViewById(R.id.alive_answer_5));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_5),  findViewById(R.id.age_answer_5), findViewById(R.id.age_text_5));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_5), findViewById(R.id.crew_answer_5));
                break;
            case 6:
                ((TextView)findViewById(R.id.guess_6)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, findViewById(R.id.fruit_wr_circle_6), findViewById(R.id.fruit_answer_6));
                AnimationManager.updateBounty(bounty, character, findViewById(R.id.bounty_wr_circle_6), findViewById(R.id.bounty_answer_6), findViewById(R.id.bounty_text_6));
                AnimationManager.updateChapter(chapter, character, findViewById(R.id.chapter_wr_circle_6), findViewById(R.id.chapter_answer_6), findViewById(R.id.chapter_text_6));
                AnimationManager.updateType(type, character, findViewById(R.id.type_wr_circle_6), findViewById(R.id.type_answer_6));
                AnimationManager.updateAlive(alive, character, findViewById(R.id.alive_wr_circle_6), findViewById(R.id.alive_answer_6));
                AnimationManager.updateAge(age, character, findViewById(R.id.age_wr_circle_6),  findViewById(R.id.age_answer_6), findViewById(R.id.age_text_6));
                AnimationManager.updateCrew(crew, character, findViewById(R.id.crew_wr_circle_6), findViewById(R.id.crew_answer_6));
                break;
        }

        return GameManager.isSameName(character, characterToFind);
    }

    public void restart()
    {
        cleanPictures();
        try {
            characterToFind = gameInit.getCharacterToFound(suggestions);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        NUMBER_GUESSED = 0;
    }

    private void clearText()
    {
        ((TextView) findViewById(R.id.guess_1)).setText("");
        ((TextView) findViewById(R.id.guess_2)).setText("");
        ((TextView) findViewById(R.id.guess_3)).setText("");
        ((TextView) findViewById(R.id.guess_4)).setText("");
        ((TextView) findViewById(R.id.guess_5)).setText("");
        ((TextView) findViewById(R.id.guess_6)).setText("");

        ((TextView) findViewById(R.id.bounty_text_1)).setText("");
        ((TextView) findViewById(R.id.bounty_text_2)).setText("");
        ((TextView) findViewById(R.id.bounty_text_3)).setText("");
        ((TextView) findViewById(R.id.bounty_text_4)).setText("");
        ((TextView) findViewById(R.id.bounty_text_5)).setText("");
        ((TextView) findViewById(R.id.bounty_text_6)).setText("");

        ((TextView) findViewById(R.id.chapter_text_1)).setText("");
        ((TextView) findViewById(R.id.chapter_text_2)).setText("");
        ((TextView) findViewById(R.id.chapter_text_3)).setText("");
        ((TextView) findViewById(R.id.chapter_text_4)).setText("");
        ((TextView) findViewById(R.id.chapter_text_5)).setText("");
        ((TextView) findViewById(R.id.chapter_text_6)).setText("");

        ((TextView) findViewById(R.id.age_text_1)).setText("");
        ((TextView) findViewById(R.id.age_text_2)).setText("");
        ((TextView) findViewById(R.id.age_text_3)).setText("");
        ((TextView) findViewById(R.id.age_text_4)).setText("");
        ((TextView) findViewById(R.id.age_text_5)).setText("");
        ((TextView) findViewById(R.id.age_text_6)).setText("");
    }

    private void cleanPictures()
    {
        cleanBackgroundPictures();
        cleanAnswerPictures();
        clearText();
    }
    
    private void cleanBackgroundPictures()
    {
        ((ImageView) findViewById(R.id.age_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.age_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.age_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.age_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.age_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.age_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.alive_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.alive_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.alive_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.alive_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.alive_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.alive_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.bounty_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.bounty_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.bounty_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.bounty_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.bounty_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.bounty_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.chapter_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.chapter_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.chapter_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.chapter_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.chapter_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.chapter_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.crew_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.crew_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.crew_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.crew_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.crew_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.crew_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.fruit_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.fruit_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.fruit_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.fruit_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.fruit_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.fruit_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) findViewById(R.id.type_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.type_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.type_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.type_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.type_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) findViewById(R.id.type_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

    }
    
    private void cleanAnswerPictures()
    {
        ((ImageView) findViewById(R.id.age_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.age_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.age_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.age_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.age_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.age_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.alive_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.alive_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.alive_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.alive_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.alive_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.alive_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.bounty_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.bounty_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.bounty_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.bounty_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.bounty_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.bounty_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.chapter_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.chapter_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.chapter_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.chapter_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.chapter_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.chapter_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.crew_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.crew_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.crew_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.crew_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.crew_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.crew_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.fruit_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.fruit_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.fruit_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.fruit_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.fruit_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.fruit_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) findViewById(R.id.type_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.type_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.type_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.type_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.type_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) findViewById(R.id.type_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

    }

    private void displayWinDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        TextView answer = dialog.findViewById(R.id.answer);
        answer.setText(characterToFind.getName());

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

        TextView answer = dialog.findViewById(R.id.answer);
        answer.setText(characterToFind.getName());

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
}