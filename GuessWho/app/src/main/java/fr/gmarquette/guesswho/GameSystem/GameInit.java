/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem;

import android.content.Context;

import java.util.ArrayList;
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
        int minId = Collections.min(integerList);
        int maxId = Collections.max(integerList);
        Random rand = new Random();
        return rand.nextInt(maxId - minId + 1) + minId;
    }

    public Characters getCharacterToFound(List<String> characterNameList) throws InterruptedException {
        List<Integer> integerList = new ArrayList<>();
        try
        {
            for(String characterName : characterNameList)
            {
                integerList.add(callDAOAsync.getIdFromCharacterNameAsync(characterName).get());
            }
            int id = this.RandomId(integerList);
            return callDAOAsync.getCharacterFromIdAsync(id).get();
        } catch (ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }
}
