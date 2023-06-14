package fr.gmarquette.guesswho.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Characters
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") private int id;
    @ColumnInfo(name = "name") private final String name;
    @ColumnInfo(name = "devilFruit") private final boolean devilFruit;
    @ColumnInfo(name = "bounty") private final String bounty;
    @ColumnInfo(name = "firstAppearance") private final int firstAppearance;
    @ColumnInfo(name = "type") private final String type;
    @ColumnInfo(name = "alive") private final boolean alive;
    @ColumnInfo(name = "age") private final int age;
    @ColumnInfo(name = "crew") private final String crew;

    public Characters(String name, boolean devilFruit, String bounty, int firstAppearance, String type, boolean alive, int age, String crew) {
        this.name = name;
        this.devilFruit = devilFruit;
        this.bounty = bounty;
        this.firstAppearance = firstAppearance;
        this.type = type;
        this.alive = alive;
        this.age = age;
        this.crew = crew;
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
}
