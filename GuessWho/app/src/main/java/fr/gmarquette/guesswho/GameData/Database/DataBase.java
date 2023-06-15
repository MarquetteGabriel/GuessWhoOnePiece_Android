/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.Serializable;

@Database(entities = {Characters.class}, version = 1)
public abstract class DataBase extends RoomDatabase implements Serializable {

    private static DataBase database;
    private static final String DATABASE_NAME = "database";

    public synchronized static DataBase getInstance(Context context)
    {
        if(database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract DAO dao();
}


