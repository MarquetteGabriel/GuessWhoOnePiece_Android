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
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.R;

public class LoadingScreenFragment extends Fragment {

    private float avancement;
    private View view;
    private static final int MAX_PROGRESS_BAR = 100;
    private ProgressBar progressBar;
    private TextView textView;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ImportDataManager importDataManager;
    private MainActivityViewModel mainActivityViewModel;

    public LoadingScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_loading_screen, container, false);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.textProgressBar);
        textView.setText("0 %");

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("GuessWhoApp", Context.MODE_PRIVATE);
        boolean isUpdated = sharedPreferences.getBoolean("isUpdated", false);

        if(isUpdated)
        {
            progressBar.setProgress(MAX_PROGRESS_BAR);
            textView.setText(R.string.MaxProgressBar);
        }
        else
        {
            avancement = 0;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isUpdated", true);
            editor.apply();
        }

        importDataManager = ImportDataManager.getInstance();
        setLoadingBar();

        return view;
    }

    private void setLoadingBar()
    {
        executorService.submit(() ->
        {
            if(progressBar.getProgress() != MAX_PROGRESS_BAR)
            {
                while(importDataManager.getNameList() == null || importDataManager.getNameList().size() == 0)
                {

                }

                int max = importDataManager.getNameList().size();
                while (avancement < MAX_PROGRESS_BAR)
                {
                    avancement = importDataManager.getAvancement(max);

                    requireActivity().runOnUiThread(() -> {
                        progressBar.setProgress((int) avancement);
                        progressBar.setMax(MAX_PROGRESS_BAR);
                        String text = (int) avancement + " %";
                        textView.setText(text);
                    });
                }
            }
            mainActivityViewModel.setCharacterNameList(DataBase.getInstance(requireContext()).dao().getAllNames());

            requireActivity().runOnUiThread(() -> Navigation.findNavController(view).navigate(R.id.gameSelectionScreenFragment));

        });
    }
}