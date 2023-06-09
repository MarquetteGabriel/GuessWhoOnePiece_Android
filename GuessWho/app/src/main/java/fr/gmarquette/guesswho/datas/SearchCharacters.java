package fr.gmarquette.guesswho.datas;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchCharacters extends Thread
{
    private ExecutorService executorService;
    private Context context;
    private List<String> list;

    public SearchCharacters(Context context)
    {
        this.context = context;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void addElements(List<Characters> charactersList)
    {

    }

    public List<String> getNames()
    {
        Runnable runnable = () -> {
            this.list = DataBase.getInstance(context).dao().getNames();
        };
        executorService.execute(runnable);
        return this.list;

    }

}
