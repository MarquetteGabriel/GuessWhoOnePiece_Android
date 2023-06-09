package fr.gmarquette.guesswho.datas;

import android.content.Context;

import androidx.room.Room;

public class DataBaseSingleton {
    private DataBaseSingleton() {}
    private DataBase dataBase;

    private static class SingletonHolder
    {
        private static final DataBaseSingleton instance = new DataBaseSingleton();
    }


    public static DataBaseSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public DataBase getDataBase(Context context)
    {
        if(this.dataBase == null)
        {
            this.dataBase = Room.databaseBuilder(context, DataBase.class, "DataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return this.dataBase;
    }
}
