package fr.gmarquette.guesswho.datas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Characters.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract DAO dao();
    private static DataBase instance;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    List<String> list = new ArrayList<>();

    public void CreateDatabase()
    {
        InitialiseDatabase initialiseDatabase = new InitialiseDatabase();

        Runnable runnable = () -> {
            this.clearAllTables();
            this.dao().addElements(initialiseDatabase.getDatabaseValues());
            list = this.dao().getNames();
        };
        executor.execute(runnable);

        initialiseDatabase.ClearList();
    }

    public static DataBase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, DataBase.class, "DataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static String[] getNames()
    {
        return instance.list.toArray(new String[0]);
    }
}
