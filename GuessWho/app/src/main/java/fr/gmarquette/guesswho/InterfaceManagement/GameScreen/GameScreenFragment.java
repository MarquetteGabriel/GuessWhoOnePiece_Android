/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameSystem.GameInit;
import fr.gmarquette.guesswho.GameSystem.GameManager;
import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.GameSelectionScreenFragment;
import fr.gmarquette.guesswho.R;
import fr.gmarquette.guesswho.databinding.FragmentGameScreenBinding;

public class GameScreenFragment extends Fragment {

    public static int NUMBER_GUESSED = 1;
    private CallDAOAsync callDAOAsync;
    private GameInit gameInit;
    private int[] old_backgroundPictures, old_answerPictures;

    private ArrayList<String> suggestions;
    View viewFragment;
    private GridView gridView;
    private GameScreenViewModel viewModel;

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

        FragmentGameScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(GameScreenViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);


        old_backgroundPictures = viewModel.getBackgroundPictures().getValue();
        gridView = viewFragment.findViewById(R.id.grid_view);
        GridAdapter gridAdapter = new GridAdapter(requireContext().getApplicationContext(), viewModel.getAnswerText().getValue(), viewModel.getBackgroundPictures().getValue(), viewModel.getAnswerPictures().getValue());
        gridView.setAdapter(gridAdapter);

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

            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);

            getAnswer(selectedValue);

            autoCompleteTextView.setText("");
        });

        PicturesAlbum.getInstance().setImages();
        restart();

        viewModel.getBackgroundPictures().observe(getViewLifecycleOwner(), new Observer<int[]>() {
            @Override
            public void onChanged(int[] ints) {
                for(int i = 0; i < ints.length; i++)
                {
                    if(ints[i] != old_backgroundPictures[i])
                    {
                        int newPicureBackgroundId = ints[i];
                        ImageView imageViewBackground = gridView.getChildAt(i).findViewById(R.id.wr_circle);
                        imageViewBackground.animate().alpha(0f).setDuration(500)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        imageViewBackground.setImageResource(newPicureBackgroundId);
                                        imageViewBackground.animate().alpha(1f).setDuration(500).start();
                                    }
                                }).start();

                        old_backgroundPictures[i] = ints[i];
                    }
                }
            }
        });
        
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

        Answering answer = AnimationManager.updateFruit(GameManager.hasEatenDevilFruit(character, viewModel.getCharacterToFind().getValue()), character);
        int[] guessAnswer = {R.id.guess_1, R.id.guess_2, R.id.guess_3, R.id.guess_4, R.id.guess_5, R.id.guess_6};
        getAnswerPrinted(NUMBER_GUESSED -1, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[NUMBER_GUESSED-1] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[NUMBER_GUESSED - 1] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[NUMBER_GUESSED - 1] = answer.getAnswer();

        answer = AnimationManager.updateBounty(GameManager.lookingForBounty(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1) + 6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[NUMBER_GUESSED-1 + 6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[NUMBER_GUESSED - 1 + 6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[NUMBER_GUESSED - 1 + 6] = answer.getAnswer();

        answer = AnimationManager.updateChapter(GameManager.getAppearanceDiff(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1) + 2*6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[NUMBER_GUESSED -1 + 2*6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[NUMBER_GUESSED -1 + 2*6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[NUMBER_GUESSED -1 + 2*6] = answer.getAnswer();

        answer = AnimationManager.updateType(GameManager.getType(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1)+3*6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[(NUMBER_GUESSED -1)+3*6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[(NUMBER_GUESSED -1)+3*6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[(NUMBER_GUESSED -1)+3*6] = answer.getAnswer();

        answer = AnimationManager.updateAlive(GameManager.isAlive(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1)+4*6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[(NUMBER_GUESSED -1)+4*6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[(NUMBER_GUESSED -1)+4*6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[(NUMBER_GUESSED -1)+4*6] = answer.getAnswer();

        answer = AnimationManager.updateAge(GameManager.getAge(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1)+5*6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[(NUMBER_GUESSED -1)+5*6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[(NUMBER_GUESSED -1)+5*6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[(NUMBER_GUESSED -1)+5*6] = answer.getAnswer();

        answer = AnimationManager.updateCrew(GameManager.getCrew(character, viewModel.getCharacterToFind().getValue()), character);
        getAnswerPrinted((NUMBER_GUESSED -1)+6*6, answer.getImageBackground(), answer.getImageAnswer(), answer.getAnswer());

        viewModel.getAnswerPictures().getValue()[(NUMBER_GUESSED -1)+6*6] = answer.getImageAnswer();
        viewModel.getBackgroundPictures().getValue()[(NUMBER_GUESSED -1)+6*6] = answer.getImageBackground();
        viewModel.getAnswerText().getValue()[(NUMBER_GUESSED -1)+6*6] = answer.getAnswer();

        ((TextView) viewFragment.findViewById(guessAnswer[NUMBER_GUESSED - 1])).setText(selectedValue);
        viewModel.getCharacter().getValue().put(guessAnswer[NUMBER_GUESSED - 1], selectedValue);

        return GameManager.isSameName(character, viewModel.getCharacterToFind().getValue());
    }


    public void restart()
    {
        //cleanPicture();
        try {
            viewModel.setCharacterToFind(gameInit.getCharacterToFound(suggestions));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        NUMBER_GUESSED = 0;
    }

    private void cleanPicture()
    {
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                for(int i = 0; i < gridView.getCount(); i++)
                {
                    ImageView imageViewBackground = gridView.getChildAt(i).findViewById(R.id.wr_circle);
                    imageViewBackground.setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
                    ImageView imageViewAnswer = gridView.getChildAt(i).findViewById(R.id.answer_circle);
                    imageViewAnswer.setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
                    TextView textView = gridView.getChildAt(i).findViewById(R.id.text_answer);
                    textView.setText("");
                }

                gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

    }

    private void displayWinDialog()
    {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        TextView answer = dialog.findViewById(R.id.answer);
        answer.setText(viewModel.getCharacterToFind().getValue().getName());

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
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.win_popup);

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);

        TextView answer = dialog.findViewById(R.id.answer);
        answer.setText(viewModel.getCharacterToFind().getValue().getName());

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


    public void crossFading(final ImageView imageViewBackground, final ImageView imageViewAnswer, final TextView textView, final int imageBackgroundId, final int imageAnswerId, final String answer) {
        if(imageViewBackground != null && imageBackgroundId != 0)
        {
            imageViewBackground.animate().alpha(0f).setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            imageViewBackground.setImageResource(imageBackgroundId);
                            imageViewBackground.animate().alpha(1f).setDuration(500).start();
                        }
                    }).start();
        }

        if(imageViewAnswer != null && imageAnswerId != 0)
        {
            imageViewAnswer.animate().alpha(0f).setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            imageViewAnswer.setImageResource(imageAnswerId);
                            imageViewAnswer.animate().alpha(1f).setDuration(500).start();
                        }
                    }).start();
        }

        if(textView != null && answer != null)
        {
            textView.animate().alpha(0f).setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            textView.setText(answer);
                            textView.animate().alpha(1f).setDuration(500).start();
                        }
                    }).start();
        }

    }

    public void getAnswerPrinted(int position, int imageBackgroundId, int answerImageId, String answer) {
        ImageView imageBackground = gridView.getChildAt(position).findViewById(R.id.wr_circle);
        ImageView imageAnswer = gridView.getChildAt(position).findViewById(R.id.answer_circle);
        TextView textAnswer = gridView.getChildAt(position).findViewById(R.id.text_answer);
        //crossFading(imageBackground, imageAnswer, textAnswer, imageBackgroundId, answerImageId, answer);
    }
}