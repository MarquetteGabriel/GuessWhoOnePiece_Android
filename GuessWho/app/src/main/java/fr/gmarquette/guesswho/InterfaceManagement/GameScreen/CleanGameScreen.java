/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.gmarquette.guesswho.R;

public class CleanGameScreen 
{

    public static void cleanPictures(View view)
    {
        cleanBackgroundPictures(view);
        cleanAnswerPictures(view);
        clearText(view);
    }

    private static void cleanBackgroundPictures(View view)
    {
        ((ImageView) view.findViewById(R.id.age_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.age_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.age_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.age_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.age_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.age_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.alive_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.bounty_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.chapter_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.crew_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.fruit_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

        ((ImageView) view.findViewById(R.id.type_wr_circle_1)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.type_wr_circle_2)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.type_wr_circle_3)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.type_wr_circle_4)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.type_wr_circle_5)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);
        ((ImageView) view.findViewById(R.id.type_wr_circle_6)).setImageResource(PicturesAlbum.getInstance().WRONG_ANSWER);

    }

    private static void cleanAnswerPictures(View view)
    {
        ((ImageView) view.findViewById(R.id.age_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.age_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.age_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.age_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.age_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.age_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.alive_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.alive_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.bounty_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.bounty_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.chapter_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.chapter_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.crew_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.crew_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.fruit_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.fruit_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

        ((ImageView) view.findViewById(R.id.type_answer_1)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.type_answer_2)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.type_answer_3)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.type_answer_4)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.type_answer_5)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);
        ((ImageView) view.findViewById(R.id.type_answer_6)).setImageResource(PicturesAlbum.getInstance().EMPTY_ANSWER);

    }

    private static void clearText(View view)
    {
        ((TextView) view.findViewById(R.id.guess_1)).setText(R.string.guess_1_txt);
        ((TextView) view.findViewById(R.id.guess_2)).setText(R.string.guess_2_txt);
        ((TextView) view.findViewById(R.id.guess_3)).setText(R.string.guess_3_txt);
        ((TextView) view.findViewById(R.id.guess_4)).setText(R.string.guess_4_txt);
        ((TextView) view.findViewById(R.id.guess_5)).setText(R.string.guess_5_txt);
        ((TextView) view.findViewById(R.id.guess_6)).setText(R.string.guess_6_txt);

        ((TextView) view.findViewById(R.id.bounty_text_1)).setText("");
        ((TextView) view.findViewById(R.id.bounty_text_2)).setText("");
        ((TextView) view.findViewById(R.id.bounty_text_3)).setText("");
        ((TextView) view.findViewById(R.id.bounty_text_4)).setText("");
        ((TextView) view.findViewById(R.id.bounty_text_5)).setText("");
        ((TextView) view.findViewById(R.id.bounty_text_6)).setText("");

        ((TextView) view.findViewById(R.id.chapter_text_1)).setText("");
        ((TextView) view.findViewById(R.id.chapter_text_2)).setText("");
        ((TextView) view.findViewById(R.id.chapter_text_3)).setText("");
        ((TextView) view.findViewById(R.id.chapter_text_4)).setText("");
        ((TextView) view.findViewById(R.id.chapter_text_5)).setText("");
        ((TextView) view.findViewById(R.id.chapter_text_6)).setText("");

        ((TextView) view.findViewById(R.id.age_text_1)).setText("");
        ((TextView) view.findViewById(R.id.age_text_2)).setText("");
        ((TextView) view.findViewById(R.id.age_text_3)).setText("");
        ((TextView) view.findViewById(R.id.age_text_4)).setText("");
        ((TextView) view.findViewById(R.id.age_text_5)).setText("");
        ((TextView) view.findViewById(R.id.age_text_6)).setText("");
    }
}
