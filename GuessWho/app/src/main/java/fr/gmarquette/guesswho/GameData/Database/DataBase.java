/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.gmarquette.guesswho.GameData.Characters.InitialiseDatabase;

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


