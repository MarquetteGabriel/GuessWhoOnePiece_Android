/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Map;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class GameScreenViewModel extends ViewModel {

    private MutableLiveData<List<String>> listCharacter = new MutableLiveData<>();
    private MutableLiveData<int[]> backgroundPictures = new MutableLiveData<>();
    private MutableLiveData<int[]> answerPictures = new MutableLiveData<>();
    private MutableLiveData<String[]> answerText = new MutableLiveData<>();
    private MutableLiveData<Characters> characterToFind = new MutableLiveData<>();
    private MutableLiveData<Map<Integer, String>> character = new MutableLiveData<>();

    public LiveData<List<String>> getListCharacter() {
        return listCharacter;
    }

    public void setListCharacter(List<String> listCharacter) {
        this.listCharacter.setValue(listCharacter);
    }

    public LiveData<int[]> getBackgroundPictures() {
        return backgroundPictures;
    }

    public void setBackgroundPictures(int[] backgroundPictures) {
        this.backgroundPictures.setValue(backgroundPictures);
    }

    public LiveData<int[]> getAnswerPictures() {
        return answerPictures;
    }

    public void setAnswerPictures(int[] answerPictures) {
        this.answerPictures.setValue(answerPictures);
    }

    public LiveData<String[]> getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String[] answerText) {
        this.answerText.setValue(answerText);
    }

    public LiveData<Characters> getCharacterToFind() {
        return characterToFind;
    }

    public void setCharacterToFind(Characters characterToFind) {
        this.characterToFind.setValue(characterToFind);
    }

    public LiveData<Map<Integer, String>> getCharacter() {
        return character;
    }

    public void setCharacter(Map<Integer, String> character) {
        this.character.setValue(character);
    }
}
