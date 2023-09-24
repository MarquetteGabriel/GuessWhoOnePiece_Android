/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.gmarquette.guesswho.InterfaceManagement.DataViewModel;
import fr.gmarquette.guesswho.R;

public class ListOfCharactersFragment extends Fragment {
    /*******************************\
     |*  Déclaration des attributs  *|
     \*******************************/
    private static ArrayList<String> list;
    private DataViewModel itemViewModel;

    private RecyclerView recyclerView;


    public ListOfCharactersFragment() {
        // Required empty public constructor
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
        recyclerView = view.findViewById(R.id.listCharacter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        itemViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        ListFragmentAdapter adapter = new ListFragmentAdapter(getContext(), itemViewModel.getListCharacter().getValue(), itemViewModel.getListLevel().getValue(), itemViewModel.getListPictures().getValue());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}