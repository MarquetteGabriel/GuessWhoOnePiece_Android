/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.GameSystem;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;

public class GameInit extends Thread
{
    CallDAOAsync callDAOAsync;
    Context context;

    public GameInit(Context context) {
        this.context = context;
        callDAOAsync = new CallDAOAsync(context);
    }

    public int RandomId() {
        List<Integer> list;
        try {
            list = callDAOAsync.getIdAsync().get();
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
            return callDAOAsync.getCharacterFromIdAsync(id).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
