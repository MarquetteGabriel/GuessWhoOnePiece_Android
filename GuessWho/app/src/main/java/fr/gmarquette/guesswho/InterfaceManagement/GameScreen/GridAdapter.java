/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fr.gmarquette.guesswho.R;

public class GridAdapter extends BaseAdapter
{
    Context context;
    String[] answerName;
    int[] wr_circle, answer_circle;
    Animation fadein, fadeout;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] answerName, int[] wr_circle, int[] answer_circle) {
        this.context = context;
        this.answerName = answerName;
        this.wr_circle = wr_circle;
        this.answer_circle = answer_circle;
    }

    @Override
    public int getCount() {
        return answerName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }
        fadein = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fadeout = AnimationUtils.loadAnimation(context, R.anim.fade_out);


        ImageView wr_ImageView = convertView.findViewById(R.id.wr_circle);
        ImageView answer_ImageView = convertView.findViewById(R.id.answer_circle);
        TextView text_TextView = convertView.findViewById(R.id.text_answer);


        answer_ImageView.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        answer_ImageView.setImageResource(answer_circle[position]);
                        answer_ImageView.animate().alpha(1f).setDuration(500).start();
                    }
                }).start();

        wr_ImageView.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        wr_ImageView.setImageResource(wr_circle[position]);
                        wr_ImageView.animate().alpha(1f).setDuration(500).start();
                    }
                }).start();

        text_TextView.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        text_TextView.setText(answerName[position]);
                        text_TextView.animate().alpha(1f).setDuration(500).start();
                    }
                }).start();


        //crossFadingAdapter(wr_ImageView, answer_ImageView, text_TextView, wr_circle[position], answer_circle[position], answerName[position]);

        /*
                wr_ImageView.setImageResource(wr_circle[position]);
        answer_ImageView.setImageResource(answer_circle[position]);
        text_TextView.setText(answerName[position]);
         */


        return convertView;
    }

    void crossFadingAdapter(ImageView imageViewBackground, final ImageView imageViewAnswer, final TextView textView, final int imageBackgroundId, final int imageAnswerId, final String answer) {
        if(imageViewBackground != null && imageBackgroundId != 0)
        {
            imageViewBackground.startAnimation(fadein);
            imageViewBackground.setImageResource(imageBackgroundId);
            imageViewBackground.startAnimation(fadeout);
        }

        if(imageViewAnswer != null && imageAnswerId != 0)
        {
            imageViewAnswer.startAnimation(fadein);
            imageViewAnswer.setImageResource(imageAnswerId);
            imageViewAnswer.startAnimation(fadeout);
        }

        if(textView != null && answer != null)
        {
            textView.startAnimation(fadein);
            textView.setText(answer);
            textView.startAnimation(fadeout);
        }

    }

    void setText(int position, String text)
    {
        answerName[position] = text;
    }

    public void setWr_circle(int position, int id) {
        wr_circle[position] = id;
    }

    public void setAnswer_circle(int position, int id) {
        answer_circle[position] = id;
    }

}

