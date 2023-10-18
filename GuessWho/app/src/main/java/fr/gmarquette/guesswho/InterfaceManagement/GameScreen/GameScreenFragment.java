/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.AgeType;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyType;
import fr.gmarquette.guesswho.GameSystem.ChapterType;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.GameSystem.GameManager;
import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.GameSelectionScreenFragment;
import fr.gmarquette.guesswho.R;

public class GameScreenFragment extends Fragment {

    public static int NUMBER_GUESSED = 0;
    Characters characterToFind;
    private CallDAOAsync callDAOAsync;
    private GameInit gameInit;

    private ArrayList<String> suggestions;
    View viewFragment;

    public GameScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewFragment = inflater.inflate(R.layout.fragment_game_screen, container, false);

        suggestions = GameSelectionScreenFragment.getListSuggestions();

        DataBase.getInstance(requireContext().getApplicationContext());
        callDAOAsync = new CallDAOAsync(requireContext().getApplicationContext());
        gameInit = new GameInit(requireContext().getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        AutoCompleteTextView autoCompleteTextView = viewFragment.findViewById(R.id.EnterTextAutoCompleted);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedValue = autoCompleteTextView.getAdapter().getItem(position).toString();
            autoCompleteTextView.setText(selectedValue);
            getAnswer(selectedValue);
            autoCompleteTextView.setText("");
        });

        PicturesAlbum.getInstance().setImages();

        restart();
        
        return viewFragment;
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
            character = callDAOAsync.getCharacterFromNameAsync(selectedValue).get();
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
            {
                ((TextView) viewFragment.findViewById(R.id.guess_1)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_1), viewFragment.findViewById(R.id.fruit_answer_1));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_1), viewFragment.findViewById(R.id.bounty_answer_1), viewFragment.findViewById(R.id.bounty_text_1));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_1), viewFragment.findViewById(R.id.chapter_answer_1), viewFragment.findViewById(R.id.chapter_text_1));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_1), viewFragment.findViewById(R.id.type_answer_1));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_1), viewFragment.findViewById(R.id.alive_answer_1));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_1), viewFragment.findViewById(R.id.age_answer_1), viewFragment.findViewById(R.id.age_text_1));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_1), viewFragment.findViewById(R.id.crew_answer_1));
                break;
            }
            case 2:
            {
                ((TextView) viewFragment.findViewById(R.id.guess_2)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_2), viewFragment.findViewById(R.id.fruit_answer_2));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_2), viewFragment.findViewById(R.id.bounty_answer_2), viewFragment.findViewById(R.id.bounty_text_2));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_2), viewFragment.findViewById(R.id.chapter_answer_2), viewFragment.findViewById(R.id.chapter_text_2));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_2), viewFragment.findViewById(R.id.type_answer_2));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_2), viewFragment.findViewById(R.id.alive_answer_2));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_2), viewFragment.findViewById(R.id.age_answer_2), viewFragment.findViewById(R.id.age_text_2));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_2), viewFragment.findViewById(R.id.crew_answer_2));
                break;
            }
            case 3:
            {
                ((TextView) viewFragment.findViewById(R.id.guess_3)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_3), viewFragment.findViewById(R.id.fruit_answer_3));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_3), viewFragment.findViewById(R.id.bounty_answer_3), viewFragment.findViewById(R.id.bounty_text_3));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_3), viewFragment.findViewById(R.id.chapter_answer_3), viewFragment.findViewById(R.id.chapter_text_3));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_3), viewFragment.findViewById(R.id.type_answer_3));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_3), viewFragment.findViewById(R.id.alive_answer_3));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_3), viewFragment.findViewById(R.id.age_answer_3), viewFragment.findViewById(R.id.age_text_3));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_3), viewFragment.findViewById(R.id.crew_answer_3));
                break;
            }
            case 4:
            {
                ((TextView) viewFragment.findViewById(R.id.guess_4)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_4), viewFragment.findViewById(R.id.fruit_answer_4));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_4), viewFragment.findViewById(R.id.bounty_answer_4), viewFragment.findViewById(R.id.bounty_text_4));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_4), viewFragment.findViewById(R.id.chapter_answer_4), viewFragment.findViewById(R.id.chapter_text_4));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_4), viewFragment.findViewById(R.id.type_answer_4));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_4), viewFragment.findViewById(R.id.alive_answer_4));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_4), viewFragment.findViewById(R.id.age_answer_4), viewFragment.findViewById(R.id.age_text_4));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_4), viewFragment.findViewById(R.id.crew_answer_4));
                break;
            }
            case 5:
            {
                ((TextView) viewFragment.findViewById(R.id.guess_5)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_5), viewFragment.findViewById(R.id.fruit_answer_5));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_5), viewFragment.findViewById(R.id.bounty_answer_5), viewFragment.findViewById(R.id.bounty_text_5));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_5), viewFragment.findViewById(R.id.chapter_answer_5), viewFragment.findViewById(R.id.chapter_text_5));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_5), viewFragment.findViewById(R.id.type_answer_5));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_5), viewFragment.findViewById(R.id.alive_answer_5));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_5), viewFragment.findViewById(R.id.age_answer_5), viewFragment.findViewById(R.id.age_text_5));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_5), viewFragment.findViewById(R.id.crew_answer_5));
                break;
            }
            case 6:
            {
                ((TextView)viewFragment.findViewById(R.id.guess_6)).setText(selectedValue);
                AnimationManager.updateFruit(hasFruit, character, viewFragment.findViewById(R.id.fruit_wr_circle_6), viewFragment.findViewById(R.id.fruit_answer_6));
                AnimationManager.updateBounty(bounty, character, viewFragment.findViewById(R.id.bounty_wr_circle_6), viewFragment.findViewById(R.id.bounty_answer_6), viewFragment.findViewById(R.id.bounty_text_6));
                AnimationManager.updateChapter(chapter, character, viewFragment.findViewById(R.id.chapter_wr_circle_6), viewFragment.findViewById(R.id.chapter_answer_6), viewFragment.findViewById(R.id.chapter_text_6));
                AnimationManager.updateType(type, character, viewFragment.findViewById(R.id.type_wr_circle_6), viewFragment.findViewById(R.id.type_answer_6));
                AnimationManager.updateAlive(alive, character, viewFragment.findViewById(R.id.alive_wr_circle_6), viewFragment.findViewById(R.id.alive_answer_6));
                AnimationManager.updateAge(age, character, viewFragment.findViewById(R.id.age_wr_circle_6),  viewFragment.findViewById(R.id.age_answer_6), viewFragment.findViewById(R.id.age_text_6));
                AnimationManager.updateCrew(crew, character, viewFragment.findViewById(R.id.crew_wr_circle_6), viewFragment.findViewById(R.id.crew_answer_6));
                break;
            }
        }


        return GameManager.isSameName(character, characterToFind);
    }

    public void restart()
    {
        CleanGameScreen.cleanPictures(viewFragment);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() ->
        {
            try {
                characterToFind = gameInit.getCharacterToFound(suggestions);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        NUMBER_GUESSED = 0;
    }

    private void displayWinDialog()
    {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("GuessWhoApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("wins", sharedPreferences.getInt("wins", 0) + 1);
        editor.apply();

        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        TextView answer = dialog.findViewById(R.id.answer);
        ImageView pictureCharacter = dialog.findViewById(R.id.characterPicture);
        answer.setText(characterToFind.getName());
        Picasso.get().load(characterToFind.getPicture()).into(pictureCharacter);

        yesButton.setOnClickListener(view -> {
            restart();
            dialog.dismiss();
        });

        noButton.setOnClickListener(view -> {
            dialog.dismiss();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5);
            navController.navigate(R.id.gameSelectionScreenFragment);
        });

        dialog.show();
    }

    private void displayLooseDialog()
    {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("GuessWhoApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loses", sharedPreferences.getInt("loses", 0) + 1);
        editor.apply();


        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        TextView answer = dialog.findViewById(R.id.answer);
        ImageView pictureCharacter = dialog.findViewById(R.id.characterPictureFinal);
        answer.setText(characterToFind.getName());
        Picasso.get().load(characterToFind.getPicture()).into(pictureCharacter);

        yesButton.setOnClickListener(view -> {
            restart();
            dialog.dismiss();
        });

        noButton.setOnClickListener(view -> {
            dialog.dismiss();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5);
            navController.navigate(R.id.gameSelectionScreenFragment);
        });

        dialog.show();
    }
}