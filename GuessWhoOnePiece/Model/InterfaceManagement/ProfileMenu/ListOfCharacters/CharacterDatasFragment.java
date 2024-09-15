/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu.ListOfCharacters;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.LevelDifficulty;
import fr.gmarquette.guesswho.InterfaceManagement.MainActivityViewModel;
import fr.gmarquette.guesswho.R;

public class CharacterDatasFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Dialog dialog = (Dialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(requireContext(), R.layout.fragment_character_datas, null);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_character_datas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivityViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        TextView textViewName = view.findViewById(R.id.name);
        TextView textViewAge = view.findViewById(R.id.textAge);
        TextView textViewAlive = view.findViewById(R.id.textAlive);
        TextView textViewBounty = view.findViewById(R.id.textBounty);
        TextView textViewChapter = view.findViewById(R.id.textChapter);
        TextView textViewFruit = view.findViewById(R.id.textFruit);
        TextView textViewLevel = view.findViewById(R.id.textLevel);
        TextView textViewCrew = view.findViewById(R.id.textCrew);
        TextView textViewType = view.findViewById(R.id.textType);
        TextView textViewLink = view.findViewById(R.id.links);
        ImageView imageView = view.findViewById(R.id.picture);

        if(itemViewModel.getCharacterInfo().getValue() != null)
        {
            textViewName.setText(itemViewModel.getCharacterInfo().getValue().getName());
            textViewAlive.setText(itemViewModel.getCharacterInfo().getValue().isAlive() ? "En vie" : "Décédé");

            if(itemViewModel.getCharacterInfo().getValue().isAlive())
            {
                String textAge = itemViewModel.getCharacterInfo().getValue().getAge() + " ans";
                textViewAge.setText(textAge);
            }
            else
            {
                String textAge = itemViewModel.getCharacterInfo().getValue().getAge() + " ans (lors du décès)";
                textViewAge.setText(textAge);
            }

            textViewType.setText(itemViewModel.getCharacterInfo().getValue().getType());
            textViewCrew.setText(itemViewModel.getCharacterInfo().getValue().getCrew());
            textViewLevel.setText(String.valueOf(LevelDifficulty.getLevelDifficultyByValue(itemViewModel.getCharacterInfo().getValue().getLevel())));
            textViewFruit.setText(itemViewModel.getCharacterInfo().getValue().hasDevilFruit() ? "Oui" : "Non");
            textViewChapter.setText(String.valueOf(itemViewModel.getCharacterInfo().getValue().getFirstAppearance()));
            textViewBounty.setText(itemViewModel.getCharacterInfo().getValue().getBounty());
            Picasso.get().load(itemViewModel.getCharacterInfo().getValue().getPicture()).into(imageView);

            String url_character = itemViewModel.getCharacterInfo().getValue().getName().replace(" ", "_").trim();
            Pattern pattern = Pattern.compile("(.+)_\\(.*\\)");
            Matcher matcher = pattern.matcher(url_character);
            if (matcher.matches()) {
                url_character = matcher.group(1);
            }
            String url_fandom = "https://onepiece.fandom.com/fr/wiki/" + url_character;
            String linkText = "Plus d'informations : \n " + url_fandom;
            textViewLink.setText(linkText);
        }
    }
}