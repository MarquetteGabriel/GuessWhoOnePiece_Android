/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallDAOAsync extends Thread
{
    private final ExecutorService executorService;
    private final Context context;

    private static final Integer NUMBER_THREAD = 15;

    public CallDAOAsync(Context context)
    {
        this.context = context;
        this.executorService = Executors.newFixedThreadPool(NUMBER_THREAD);
    }

    public Future<List<String>> getNamesAsync()
    {
        return executorService.submit(() -> DataBaseSingleton.getInstance().getDataBase(context).dao().getNames());
    }

    public Future<Characters> getCharacterAsync(String name)
    {
        return executorService.submit(() -> DataBaseSingleton.getInstance().getDataBase(context).dao().getCharacterFromName(name));
    }

    public Future<List<Integer>> getIdAsync()
    {
        return executorService.submit(() -> DataBaseSingleton.getInstance().getDataBase(context).dao().getID());
    }

    public Future<Characters> getCharacterFromIdAsync(int id)
    {
        return executorService.submit(() -> DataBaseSingleton.getInstance().getDataBase(context).dao().getCharacterFromId(id));
    }

}
