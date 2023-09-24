/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.gmarquette.guesswho.R;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {

    Context context;
    List<String> characterNameList, characterLevelList;
    List<String> characterPictureList;

    public ListFragmentAdapter(Context context, List<String> characterNameList, List<String> characterLevelList, List<String> characterPictureList)
    {
        this.context = context;
        this.characterNameList = characterNameList;
        this.characterLevelList = characterLevelList;
        this.characterPictureList = characterPictureList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String name = characterNameList.get(position);
        String level = characterLevelList.get(position);
        Picasso.get().load(characterPictureList.get(position)).into(holder.characterPicture);
        holder.characterName.setText(name);
        holder.characterDifficulty.setText(level);
    }

    @Override
    public int getItemCount(){
        return characterNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView characterName, characterDifficulty;
        ImageView characterPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            characterPicture = itemView.findViewById(R.id.characterPicture);
            characterName = itemView.findViewById(R.id.characterName);
            characterDifficulty = itemView.findViewById(R.id.Level);
        }
    }
}