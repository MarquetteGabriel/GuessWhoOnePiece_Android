package fr.gmarquette.guesswho.datas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.gmarquette.guesswho.database.Characters;

@Dao
public interface DAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addElements(List<Characters> charactersList);

    @Query("SELECT name FROM Characters")
    List<String> getNames();

    @Query("SELECT id FROM Characters")
    List<Integer> getID();

    @Query("SELECT * FROM Characters WHERE name LIKE:name")
    Characters getCharacterFromName(String name);

    @Query("SELECT * FROM Characters WHERE id LIKE:id")
    Characters getCharacterFromId(int id);
}
