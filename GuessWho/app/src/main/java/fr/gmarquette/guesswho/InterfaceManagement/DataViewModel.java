/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DataViewModel extends ViewModel {

    private MutableLiveData<List<String>> listCharacter = new MutableLiveData<>();
    private MutableLiveData<List<String>> listLevel = new MutableLiveData<>();
    private MutableLiveData<List<String>> listPictures = new MutableLiveData<>();

    public void setListCharacter(List<String> listCharacter)
    {
        this.listCharacter.setValue(listCharacter);
    }

    public LiveData<List<String>> getListCharacter()
    {
        return listCharacter;
    }

    public LiveData<List<String>> getListLevel() {
        return listLevel;
    }

    public void setListLevel(List<String> listLevel) {
        this.listLevel .setValue(listLevel);
    }

    public LiveData<List<String>> getListPictures() {
        return listPictures;
    }

    public void setListPictures(List<String> listPictures) {
        this.listPictures.setValue(listPictures);
    }
}
