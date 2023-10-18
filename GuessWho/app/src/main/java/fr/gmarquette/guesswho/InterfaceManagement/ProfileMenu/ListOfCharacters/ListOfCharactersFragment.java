/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu.ListOfCharacters;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.InterfaceManagement.MainActivityViewModel;
import fr.gmarquette.guesswho.R;

public class ListOfCharactersFragment extends Fragment implements ListOfCharacterInterface{

    private MainActivityViewModel itemViewModel;
    private ListFragmentAdapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ListOfCharactersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_characters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.listCharacter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        EditText editText = view.findViewById(R.id.filterCharacter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        itemViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        adapter = new ListFragmentAdapter(getContext(),
                itemViewModel.getCharacterNameList().getValue(),
                itemViewModel.getCharacterLevelList().getValue(),
                itemViewModel.getCharacterPicturesList().getValue(), this, itemViewModel);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int positon) {

        executorService.submit(() -> {
            String characters = itemViewModel.getCharacterNameList().getValue().get(positon);
            Characters character = DataBase.getInstance(requireContext()).dao().getCharacterFromName(characters);
            itemViewModel.setCharacterInfo(character);

            requireActivity().runOnUiThread(() -> {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5);
                navController.navigate(R.id.characterDatasFragment);
            });
        });
    }

    private void filter(String text)
    {
        List<String> filteredListName = new ArrayList<>();
        List<String> filteredListPicture = new ArrayList<>();
        List<Integer> filteredListLevel = new ArrayList<>();
        for (String character : itemViewModel.getCharacterNameList().getValue())
        {
            if(character.toLowerCase().contains(text.toLowerCase()))
            {
                filteredListName.add(character);
                int position = itemViewModel.getCharacterNameList().getValue().indexOf(character);
                filteredListLevel.add(itemViewModel.getCharacterLevelList().getValue().get(position));
                filteredListPicture.add(itemViewModel.getCharacterPicturesList().getValue().get(position));
            }
        }

        adapter.filterList(filteredListName, filteredListPicture, filteredListLevel);
    }
}