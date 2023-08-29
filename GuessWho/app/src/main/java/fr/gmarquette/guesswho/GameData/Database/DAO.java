/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCharacter(Characters characters);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addElements(List<Characters> charactersList);

    @Query("SELECT * FROM Characters WHERE name LIKE:name")
    Characters getCharacterFromName(String name);

    @Query("SELECT * FROM Characters WHERE id LIKE:id")
    Characters getCharacterFromId(int id);

    @Query("SELECT name FROM Characters WHERE level LIKE:level")
    List<String> getNamesByDifficulty(int level);

    @Query("SELECT COUNT(*) FROM Characters")
    int getCount();

    @Query("SELECT id FROM Characters WHERE name LIKE:name")
    int getIdFromCharacterName(String name);

    @Query("DELETE FROM Characters")
    void deleteAll();
}
