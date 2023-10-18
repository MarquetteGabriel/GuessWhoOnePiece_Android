/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.gmarquette.guesswho.GameData.ImportDataManager;
import fr.gmarquette.guesswho.R;

public class SettingsFragment extends Fragment {

    private String ratio;
    public SettingsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.wins);
        TextView textView1 = view.findViewById(R.id.loses);
        TextView textView2 = view.findViewById(R.id.textRatio);
        Button button = view.findViewById(R.id.importData);

        button.setOnClickListener(view1 -> ImportDataManager.getInstance().importManager(requireContext()));

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("GuessWhoApp", Context.MODE_PRIVATE);
        int wins = sharedPreferences.getInt("wins", 0);
        int loses = sharedPreferences.getInt("loses", 0);

        if(loses == 0 && wins == 0)
        {
            ratio = "0%";
        }
        else
        {
             ratio = (wins * 100)/(loses + wins) + "%";
        }

        textView.setText(String.valueOf(wins));
        textView1.setText(String.valueOf(loses));
        textView2.setText(ratio);
    }
}