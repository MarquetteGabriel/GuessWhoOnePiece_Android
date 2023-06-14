package fr.gmarquette.guesswho.game;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.database.Characters;
import fr.gmarquette.guesswho.datas.SearchCharacters;

public class GameInit extends Thread
{
    SearchCharacters searchCharacters;
    Context context;

    public GameInit(Context context) {
        this.context = context;
        searchCharacters = new SearchCharacters(context);
    }

    public int RandomId() {
        List<Integer> list;
        try {
            list = searchCharacters.getIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (list.isEmpty());
        int minId = Collections.min(list);
        int maxId = Collections.max(list);
        Random rand = new Random();
        return rand.nextInt(maxId - minId + 1) + minId;
    }

    public Characters getCharacterToFound()
    {
        int id = this.RandomId();
        try {
            return searchCharacters.getCharacterFromIdAsync(id).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
