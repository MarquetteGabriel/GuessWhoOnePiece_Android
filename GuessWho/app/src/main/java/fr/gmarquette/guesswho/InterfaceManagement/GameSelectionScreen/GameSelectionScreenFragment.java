/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.R;

public class GameSelectionScreenFragment extends Fragment{

    private CallDAOAsync callDAOAsync;
    private static final List<String> SUGGESTIONS = new ArrayList<>();
    public static final ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_game_selection_screen, container, false);

        callDAOAsync = new CallDAOAsync(requireContext().getApplicationContext());

        SUGGESTIONS.clear();
        arrayList.clear();


        Button button = viewFragment.findViewById(R.id.playButton);
        RadioButton easyRadioButton = viewFragment.findViewById(R.id.easyRadioButton);
        RadioButton hardRadioButton = viewFragment.findViewById(R.id.hardRadioButton);

        easyRadioButton.setOnClickListener(view -> {
            easyRadioButton.setChecked(true);
            hardRadioButton.setChecked(false);
        });

        hardRadioButton.setOnClickListener(view -> {
            easyRadioButton.setChecked(false);
            hardRadioButton.setChecked(true);
        });

        button.setOnClickListener(view -> {

            if (easyRadioButton.isChecked())
            {
                getSuggestions(LevelDifficulty.EASY.ordinal());
            }
            else if (hardRadioButton.isChecked())
            {
                getSuggestions(LevelDifficulty.EASY.ordinal());
                getSuggestions(LevelDifficulty.HARD.ordinal());
            }

            arrayList.clear();
            arrayList.addAll(SUGGESTIONS);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5);
            navController.navigate(R.id.gameScreenFragment);
        });

        return viewFragment;
    }

    private void getSuggestions(int level)
    {
        try {
            SUGGESTIONS.addAll(callDAOAsync.getNamesByDifficultyAsync(level).get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
/*

    public void onClickSwitchRadioButtons(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.easyRadioButton:
                if (checked) {
                    RadioButton hardRadioButton = view.findViewById(R.id.hardRadioButton);
                    hardRadioButton.setChecked(false);
                }
                break;
            case R.id.hardRadioButton:
                if (checked) {
                    RadioButton easyRadioButton = view.findViewById(R.id.easyRadioButton);
                    easyRadioButton.setChecked(false);
                }
                break;
        }
    }*/
}