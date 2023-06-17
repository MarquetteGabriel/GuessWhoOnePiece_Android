/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameData.Database;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallDAOAsync extends Thread {
    private final ExecutorService executorService;
    private final DataBase dataBase;
    private static final Integer NUMBER_THREAD = 15;

    public CallDAOAsync(Context context) {
        this.dataBase = DataBase.getInstance(context.getApplicationContext());
        this.executorService = Executors.newFixedThreadPool(NUMBER_THREAD);
    }

    public void getAddElementsAsync(List<Characters> charactersList) {
        executorService.execute(() -> this.dataBase.dao().addElements(charactersList));
    }

    public void deleteAllAsync() {
        executorService.execute(() -> this.dataBase.dao().deleteAll());
    }

    public Future<Characters> getCharacterAsync(String name) {
        return executorService.submit(() -> this.dataBase.dao().getCharacterFromName(name));
    }

    public Future<Characters> getCharacterFromIdAsync(int id) {
        return executorService.submit(() -> this.dataBase.dao().getCharacterFromId(id));
    }

    public Future<List<String>> getNamesByDifficultyAsync(int level) {
        return executorService.submit(() -> this.dataBase.dao().getNamesByDifficulty(level));
    }

    public Future<Integer> getCountAsync() {
        return executorService.submit(() -> this.dataBase.dao().getCount());
    }

    public Future<Integer> getIdFromCharacterNameAsync(String name) {
        return executorService.submit(() -> this.dataBase.dao().getIdFromCharacterName(name));
    }
}
