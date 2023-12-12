/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fr.gmarquette.guesswho.R;

public class GridAdapter extends BaseAdapter
{
    Context context;
    String[] answerName;
    int[] wr_circle, answer_circle;

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

        ImageView wr_ImageView = convertView.findViewById(R.id.wr_circle);
        ImageView answer_ImageView = convertView.findViewById(R.id.answer_circle);
        TextView text_TextView = convertView.findViewById(R.id.text_answer);

        wr_ImageView.setImageResource(wr_circle[position]);
        answer_ImageView.setImageResource(answer_circle[position]);
        text_TextView.setText(answerName[position]);

        return convertView;
    }
}

