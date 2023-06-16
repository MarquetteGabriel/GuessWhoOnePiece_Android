/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameScreenActivityViewModel extends ViewModel
{
    MutableLiveData<String> life = new MutableLiveData<>();

    public MutableLiveData<String> getLife() {
        return life;
    }

    public void setLife(MutableLiveData<String> life) {
        this.life = life;
    }
}
