package fr.gmarquette.guesswho.datas;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class Database extends RoomDatabase {
    public abstract DAO dao();
    private static Database instance;

    public void CreateDatabase()
    {
        InitialiseDatabase initialiseDatabase = new InitialiseDatabase();
        this.dao().addElements(initialiseDatabase.getDatabaseValues());
        initialiseDatabase.ClearList();

    }

    public static Database getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, Database.class, "DataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
