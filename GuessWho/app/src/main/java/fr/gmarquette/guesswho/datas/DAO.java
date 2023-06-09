package fr.gmarquette.guesswho.datas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addElements(List<Characters> charactersList);

    @Query("SELECT name FROM Characters")
    List<String> getNames();

    @Query("SELECT * FROM Characters WHERE name LIKE:name")
    Characters getCharacterFromName(String name);
}
