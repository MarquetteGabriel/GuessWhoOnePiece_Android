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

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<String>> characterNameList = new MutableLiveData<>();

    public LiveData<List<String>> getCharacterNameList() {
        return characterNameList;
    }

    public void setCharacterNameList(List<String> characterNameList) {
        this.characterNameList.postValue(characterNameList);
    }
}
