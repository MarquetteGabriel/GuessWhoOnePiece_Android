/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import fr.gmarquette.guesswho.GameData.Database.DataBase;
import fr.gmarquette.guesswho.GameData.MultiGenerateDatas;
import fr.gmarquette.guesswho.R;

public class MainActivity extends AppCompatActivity {


    NavController navController;
    MultiGenerateDatas multiGenerateDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase.getInstance(this);
        if(NetworkUtils.isNetworkAvailable(this))
        {
            multiGenerateDatas = MultiGenerateDatas.getInstance();
            multiGenerateDatas.getDatasFromOutside(this);
            multiGenerateDatas.getDatas_Thread.start();
        }
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView5);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
    }
}