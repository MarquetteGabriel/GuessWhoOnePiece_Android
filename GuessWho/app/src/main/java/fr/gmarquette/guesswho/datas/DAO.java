package fr.gmarquette.guesswho.datas;

import androidx.room.Dao;

import java.util.List;

@Dao
public interface DAO extends Runnable{

    void addElements(List<Characters> charactersList);
}
