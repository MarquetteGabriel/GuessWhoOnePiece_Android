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
import androidx.navigation.Navigation;

import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Characters.InitialiseDatabase;
import fr.gmarquette.guesswho.GameData.Characters.MultiGenerateDatas;
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.R;

public class LoadingScreenFragment extends Fragment {

    private float avancement;
    private View view;
    private static final int LOADING_TIME = 2000;
    private CallDAOAsync callDAOAsync;
    private final MultiGenerateDatas multiGenerateDatas = new MultiGenerateDatas();

    public LoadingScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_loading_screen, container, false);
        avancement = 0;

        setLoadingBar();




        callDAOAsync = new CallDAOAsync(requireContext().getApplicationContext());
        DataBase.getInstance(requireContext().getApplicationContext());
        //possibleAddElements();

        new Handler().postDelayed(() -> Navigation.findNavController(view).navigate(R.id.gameSelectionScreenFragment), LOADING_TIME);

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

    private void setLoadingBar()
    {
        new Thread(() -> {
            while (avancement < 100) {
                avancement = getAvancement();
                // Mettez à jour la barre de chargement avec le pourcentage affiché, arrondi.
            }
            goToNextFragment();
        }).start();
    }


    private float getAvancement()
    {
        if(multiGenerateDatas.getNameList() != null)
        {
            int max = multiGenerateDatas.getNameList().size();
            int value = multiGenerateDatas.getCountPercentage();

            return (value/max)*100;
        }
        else
        {
            return 0;
        }
    }

    private void goToNextFragment()
    {
        Navigation.findNavController(view).navigate(R.id.gameSelectionScreenFragment);
    }
}