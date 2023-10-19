/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu;

import android.app.Dialog;
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
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import fr.gmarquette.guesswho.GameData.ImportDataManager;
import fr.gmarquette.guesswho.R;

public class SettingsFragment extends BottomSheetDialogFragment {

    private String ratio;
    public SettingsFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(requireContext(), R.layout.fragment_settings, null);
        dialog.setContentView(view);

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setDraggable(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        return dialog;
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

        button.setOnClickListener(buttonView -> {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("GuessWhoApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isUpdated", false);
            editor.apply();

            ImportDataManager.getInstance().importManager(requireContext());

            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5).navigate(R.id.action_settingsFragment_to_loadingScreenFragment);
        });

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