/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Characters.InitialiseDatabase;
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.R;

public class LoadingScreenFragment extends Fragment {


    private static final int LOADING_TIME = 2000;
    private CallDAOAsync callDAOAsync;

    public LoadingScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loading_screen, container, false);
        callDAOAsync = new CallDAOAsync(requireContext().getApplicationContext());
        DataBase.getInstance(requireContext().getApplicationContext());
        possibleAddElements();

        new Handler().postDelayed(() -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView5);
            navController.navigate(R.id.gameSelectionScreenFragment);
        }, LOADING_TIME);

        return view;
    }

    private void possibleAddElements()
    {
        try {
            if(callDAOAsync.getCountAsync().get() == 0)
            {
                callDAOAsync.deleteAllAsync();
                callDAOAsync.getAddElementsAsync(InitialiseDatabase.getDatabaseValues());
            }
            else if(callDAOAsync.getCountAsync().get() < InitialiseDatabase.getDatabaseValues().size())
            {
                callDAOAsync.deleteAllAsync();
                callDAOAsync.getAddElementsAsync(InitialiseDatabase.getDatabaseValues());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}