package fr.gmarquette.guesswho.datas;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


