/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Characters
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") private int id;
    @ColumnInfo(name = "name") private String name;
    @ColumnInfo(name = "devilFruit") private final boolean devilFruit;
    @ColumnInfo(name = "bounty") private final String bounty;
    @ColumnInfo(name = "firstAppearance") private final int firstAppearance;
    @ColumnInfo(name = "type") private String type;
    @ColumnInfo(name = "alive") private final boolean alive;
    @ColumnInfo(name = "age") private int age;
    @ColumnInfo(name = "crew") private final String crew;
    @ColumnInfo(name = "picture") private final String picture;
    @ColumnInfo(name = "level") private int level;

    public Characters(String name, boolean devilFruit, String bounty, int firstAppearance, String type, boolean alive, int age, String crew, String picture, int level) {
        this.name = name;
        this.devilFruit = devilFruit;
        this.bounty = bounty;
        this.firstAppearance = firstAppearance;
        this.type = type;
        this.alive = alive;
        this.age = age;
        this.crew = crew;
        this.picture = picture;
        this.level = level;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public boolean hasDevilFruit() {
        return devilFruit;
    }
    public String getBounty() {
        return bounty;
    }
    public int getFirstAppearance() {
        return firstAppearance;
    }
    public String getType() {
        return type;
    }
    public boolean isAlive() {
        return alive;
    }
    public int getAge() {
        return age;
    }
    public String getCrew() {
        return crew;
    }
    public String getPicture()
    {
        return picture;
    }
    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
