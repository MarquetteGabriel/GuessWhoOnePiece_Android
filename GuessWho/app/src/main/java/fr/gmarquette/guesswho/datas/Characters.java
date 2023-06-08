package fr.gmarquette.guesswho.datas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Characters
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "devilFruit")
    public boolean devilFruit;

    @ColumnInfo(name = "bounty")
    public String bounty;

    @ColumnInfo(name = "firstAppearance")
    public int firstAppearance;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "alive")
    public boolean alive;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "crew")
    public String crew;

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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDevilFruit() {
        return devilFruit;
    }

    public void setDevilFruit(boolean devilFruit) {
        this.devilFruit = devilFruit;
    }

    public String getBounty() {
        return bounty;
    }

    public void setBounty(String bounty) {
        this.bounty = bounty;
    }

    public int getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(int firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }
}
