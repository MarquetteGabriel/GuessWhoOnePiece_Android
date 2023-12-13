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

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<List<String>> characterNameList = new MutableLiveData<>();
    private final MutableLiveData<List<String>> characterPicturesList = new MutableLiveData<>();
    private final MutableLiveData<List<Integer>> characterLevelList = new MutableLiveData<>();

    private final MutableLiveData<Characters> characterInfo = new MutableLiveData<>();
    private final MutableLiveData<List<String>> charactersSuggestions = new MutableLiveData<>();

    public LiveData<List<String>> getCharacterNameList() {
        return characterNameList;
    }

    public void setCharacterNameList(List<String> characterNameList) {
        this.characterNameList.postValue(characterNameList);
    }

    public LiveData<List<String>> getCharacterPicturesList() {
        return characterPicturesList;
    }

    public void setCharacterPicturesList(List<String> characterPicturesList) {
        this.characterPicturesList.postValue(characterPicturesList);
    }

    public LiveData<List<Integer>> getCharacterLevelList() {
        return characterLevelList;
    }

    public void setCharacterLevelList(List<Integer> characterLevelList) {
        this.characterLevelList.postValue(characterLevelList);
    }

    public LiveData<Characters> getCharacterInfo()
    {
        return characterInfo;
    }

    public void setCharacterInfo(Characters characterInfo)
    {
        this.characterInfo.postValue(characterInfo);
    }

    public void setSuggestions(List<String> characters)
    {
        this.charactersSuggestions.setValue(characters);
    }

    public LiveData<List<String>> getSuggestions()
    {
        return this.charactersSuggestions;
    }
}
