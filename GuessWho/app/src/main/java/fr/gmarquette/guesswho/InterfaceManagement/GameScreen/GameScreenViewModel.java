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

import java.util.List;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class GameScreenViewModel extends ViewModel {

    private MutableLiveData<List<String>> listCharacter = new MutableLiveData<>();
    private MutableLiveData<List<int[]>> backgroundPictures = new MutableLiveData<>();
    private MutableLiveData<List<int[]>> answerPictures = new MutableLiveData<>();
    private MutableLiveData<List<String[]>> answerText = new MutableLiveData<>();
    private Characters characterToFind;
    private List<String> character;

    public MutableLiveData<List<String>> getListCharacter() {
        return listCharacter;
    }

    public void setListCharacter(MutableLiveData<List<String>> listCharacter) {
        this.listCharacter = listCharacter;
    }

    public MutableLiveData<List<int[]>> getBackgroundPictures() {
        return backgroundPictures;
    }

    public void setBackgroundPictures(MutableLiveData<List<int[]>> backgroundPictures) {
        this.backgroundPictures = backgroundPictures;
    }

    public MutableLiveData<List<int[]>> getAnswerPictures() {
        return answerPictures;
    }

    public void setAnswerPictures(MutableLiveData<List<int[]>> answerPictures) {
        this.answerPictures = answerPictures;
    }

    public MutableLiveData<List<String[]>> getAnswerText() {
        return answerText;
    }

    public void setAnswerText(MutableLiveData<List<String[]>> answerText) {
        this.answerText = answerText;
    }

    public Characters getCharacterToFind() {
        return characterToFind;
    }

    public void setCharacterToFind(Characters characterToFind) {
        this.characterToFind = characterToFind;
    }

    public List<String> getCharacter() {
        return character;
    }

    public void setCharacter(List<String> character) {
        this.character = character;
    }
}
