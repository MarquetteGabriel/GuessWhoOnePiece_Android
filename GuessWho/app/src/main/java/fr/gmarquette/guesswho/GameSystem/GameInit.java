/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import fr.gmarquette.guesswho.GameData.Database.CallDAOAsync;
import fr.gmarquette.guesswho.GameData.Database.Characters;

public class GameInit extends Thread
{
    CallDAOAsync callDAOAsync;

    public GameInit(Context context) {
        callDAOAsync = new CallDAOAsync(context);
    }

    private int RandomId(List<Integer> integerList)
    {
        try {
            int minId = Collections.min(integerList);
            int maxId = Collections.max(integerList);
            Random rand = new Random();
            return rand.nextInt(maxId - minId + 1) + minId;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public Characters getCharacterToFound(List<String> characterNameList) throws InterruptedException
    {
        Random rand = new Random();
        int id2 = rand.nextInt(characterNameList.size() + 1);
        try {
            return callDAOAsync.getCharacterFromNameAsync(characterNameList.get(id2)).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
