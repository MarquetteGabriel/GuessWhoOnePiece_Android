package fr.gmarquette.guesswho.datas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.gmarquette.guesswho.database.Characters;
import fr.gmarquette.guesswho.database.InitialiseDatabase;

@Database(entities = {Characters.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    List<String> list = new ArrayList<>();

    public void CreateDatabase()
    {
        InitialiseDatabase initialiseDatabase = new InitialiseDatabase();
        Runnable runnable = () -> {
            this.clearAllTables();
            this.dao().addElements(initialiseDatabase.getDatabaseValues());
            this.list = this.dao().getNames();
        };
        executor.execute(runnable);

        initialiseDatabase.ClearList();
    }

    public abstract DAO dao();
}


