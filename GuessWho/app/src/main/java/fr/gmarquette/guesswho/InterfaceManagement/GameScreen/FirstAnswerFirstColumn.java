/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.gmarquette.guesswho.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstAnswerFirstColumn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstAnswerFirstColumn extends Fragment {

    private View view;

    public FirstAnswerFirstColumn() {
        // Required empty public constructor
    }

    public static FirstAnswerFirstColumn newInstance() {
        FirstAnswerFirstColumn fragment = new FirstAnswerFirstColumn();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_first_answer_first_column, container, false);
       return view;
    }

    public void updateText(String newText) {
        TextView textView = view.findViewById(R.id.alive7);
        if (textView != null) {
            textView.setText(newText);
        }
    }
}