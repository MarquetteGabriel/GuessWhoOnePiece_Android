package fr.gmarquette.guesswho.datas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "characters")
public class Characters
{
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "devilFruit")
    private boolean devilFruit;

    @ColumnInfo(name = "bounty")
    private String bounty;

    @ColumnInfo(name = "firstAppearance")
    private int firstAppearance;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "alive")
    private boolean alive;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "crew")
    private String crew;

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
}
