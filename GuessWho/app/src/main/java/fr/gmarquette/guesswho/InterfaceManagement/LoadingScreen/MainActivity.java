/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.LoadingScreen;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.gmarquette.guesswho.R;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    private Animation fromBottom;
    private Animation toBottom;
    private Animation openMenu;
    private Animation closeMenu;
    private FloatingActionButton fab_menu;
    private FloatingActionButton fab_list;
    private FloatingActionButton fab_settings;
    private boolean clicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_menu = findViewById(R.id.menubtn);
        fab_list = findViewById(R.id.listbtn);
        fab_settings = findViewById(R.id.settingsbtn);

        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_botton_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        openMenu = AnimationUtils.loadAnimation(this, R.anim.rotate_open);
        closeMenu = AnimationUtils.loadAnimation(this, R.anim.rotate_close);

        fab_menu.setOnClickListener(view -> onMenuButtonClicked());
        fab_list.setOnClickListener(view -> onListButtonClicked());
        fab_settings.setOnClickListener(view -> onSettingsClicked());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView5);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
    }

    private void onSettingsClicked() {
        navController.navigate(R.id.settingsFragment);
    }

    private void onListButtonClicked()
    {
        navController.navigate(R.id.listOfCharactersFragment);
    }

    private void onMenuButtonClicked()
    {
        setVisibility(clicked);
        setAnimation(clicked);
        setClickable(clicked);
        clicked = !clicked;
    }

    private void setAnimation(boolean clicked)
    {
        if(!clicked)
        {
            fab_menu.setAnimation(openMenu);
            fab_list.startAnimation(fromBottom);
            fab_settings.startAnimation(fromBottom);
        }
        else
        {
            fab_menu.startAnimation(closeMenu);
            fab_list.startAnimation(toBottom);
            fab_settings.startAnimation(toBottom);
        }
    }

    private void setVisibility(boolean clicked)
    {
        if(!clicked)
        {
            fab_list.setVisibility(View.VISIBLE);
            fab_settings.setVisibility(View.VISIBLE);
        }
        else
        {
            fab_list.setVisibility(View.INVISIBLE);
            fab_settings.setVisibility(View.INVISIBLE);
        }
    }

    private void setClickable(boolean clicked)
    {
        if(clicked)
        {
            fab_list.setClickable(false);
            fab_settings.setClickable(false);
        }
        else
        {
            fab_list.setClickable(true);
            fab_settings.setClickable(true);
        }
    }
}