/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

public class Answering
{
    private int imageBackground;
    private int imageAnswer;
    private String answer;

    public Answering(int imageBackground, int imageAnswer) {
        this.imageBackground = imageBackground;
        this.imageAnswer = imageAnswer;
        this.answer = null;
    }

    public Answering(int imageBackground, int imageAnswer, String answer) {
        this.imageBackground = imageBackground;
        this.imageAnswer = imageAnswer;
        this.answer = answer;
    }

    public int getImageBackground() {
        return imageBackground;
    }

    public int getImageAnswer() {
        return imageAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}
