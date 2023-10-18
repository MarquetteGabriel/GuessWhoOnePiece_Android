/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCharacter(Characters characters);

    @Delete
    void deleteCharacter(Characters characters);
    @Update
    void updateCharacter(Characters characters);

    @Query("SELECT * FROM Characters WHERE name LIKE:name")
    Characters getCharacterFromName(String name);

    @Query("SELECT name FROM Characters WHERE level LIKE:level")
    List<String> getNamesByDifficulty(int level);

    @Query("SELECT name FROM Characters")
    List<String> getAllNames();
}
