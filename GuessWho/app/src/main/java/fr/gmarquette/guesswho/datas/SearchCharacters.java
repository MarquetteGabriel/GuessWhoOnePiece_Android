package fr.gmarquette.guesswho.datas;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fr.gmarquette.guesswho.database.Characters;

public class SearchCharacters extends Thread
{
    private final ExecutorService executorService;
    private final Context context;

    public SearchCharacters(Context context)
    {
        this.context = context;
        this.executorService = Executors.newFixedThreadPool(15);
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
