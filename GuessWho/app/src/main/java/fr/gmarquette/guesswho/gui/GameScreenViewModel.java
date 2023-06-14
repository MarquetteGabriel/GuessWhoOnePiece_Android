package fr.gmarquette.guesswho.gui;

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
