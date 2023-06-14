/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameScreenViewModel extends ViewModel
{
    MutableLiveData<String> life = new MutableLiveData<>();

    public MutableLiveData<String> getLife() {
        return life;
    }

    public void setLife(MutableLiveData<String> life) {
        this.life = life;
    }
}
