/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import fr.gmarquette.guesswho.GameData.MultiGenerateDatas;
import fr.gmarquette.guesswho.R;

public class LoadingScreenFragment extends Fragment {

    private float avancement;
    private View view;
    private static final int MAX_PROGRESS_BAR = 100;
    private ProgressBar progressBar;
    private TextView textView;
    private MultiGenerateDatas multiGenerateDatas;

    public LoadingScreenFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_loading_screen, container, false);


        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.textProgressBar);
        textView.setText("0 %");

        if(!NetworkUtils.isNetworkAvailable(requireContext().getApplicationContext()))
        {
            progressBar.setProgress(MAX_PROGRESS_BAR);
            textView.setText(R.string.MaxProgressBar);
        }

        multiGenerateDatas = MultiGenerateDatas.getInstance();
        setLoadingBar();

        return view;
    }

    private void setLoadingBar()
    {
        new Thread(() -> {
            avancement = 0;

            if(progressBar.getProgress() != MAX_PROGRESS_BAR)
            {
                while(multiGenerateDatas.getNameList() == null || multiGenerateDatas.getNameList().size() == 0)
                {

                }
            }

            int max = multiGenerateDatas.getNameList().size();
            while (avancement < MAX_PROGRESS_BAR) {
                float avancementCharacter = ((((multiGenerateDatas.getCountPercentage()/(float) max)*100)* 100)/70);
                float avancementLevels = ((((multiGenerateDatas.getCountLevels()/(float) max) *100)* 100)/30);
                avancement = avancementCharacter + avancementLevels;

                requireActivity().runOnUiThread(() -> {
                    progressBar.setProgress((int) avancement);
                    progressBar.setMax(MAX_PROGRESS_BAR);
                    String text = (int) avancement + " %";
                    textView.setText(text);
                });
            }
            requireActivity().runOnUiThread(() -> Navigation.findNavController(view).navigate(R.id.gameSelectionScreenFragment));

        }).start();
    }
}