/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.ProfileMenu.ListOfCharacters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import fr.gmarquette.guesswho.InterfaceManagement.GameSelectionScreen.LevelDifficulty;
import fr.gmarquette.guesswho.InterfaceManagement.MainActivityViewModel;
import fr.gmarquette.guesswho.R;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder>{

    private final ListOfCharacterInterface listOfCharacterInterface;
    Context context;
    MainActivityViewModel mainActivityViewModel;
    List<String> characterNameList, characterPictureList;
    List<Integer> characterLevelList;

    public ListFragmentAdapter(Context context, List<String> characterNameList,
                               List<Integer> characterLevelList, List<String> characterPictureList,
                               ListOfCharacterInterface listOfCharacterInterface,
                               MainActivityViewModel mainActivityViewModel)
    {
        this.context = context;
        this.characterNameList = characterNameList;
        this.characterLevelList = characterLevelList;
        this.characterPictureList = characterPictureList;
        this.listOfCharacterInterface = listOfCharacterInterface;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, listOfCharacterInterface, mainActivityViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String name = characterNameList.get(position);
        int level = characterLevelList.get(position);
        resizePicture(holder, position);

        holder.characterName.setText(name);
        holder.characterDifficulty.setText(String.valueOf(LevelDifficulty.getLevelDifficultyByValue(level)));
    }

    @Override
    public int getItemCount(){
        return characterNameList.size();
    }

    public void filterList(List<String> filteredListName, List<String> filteredListPicture, List<Integer> filteredListLevel) {
        characterNameList = filteredListName;
        characterLevelList = filteredListLevel;
        characterPictureList = filteredListPicture;
        notifyDataSetChanged();
    }


    private void resizePicture(ViewHolder holder, int position)
    {
        int targetHeightDP = 100;
        int targetHeightPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, targetHeightDP, context.getResources().getDisplayMetrics());
        Picasso.get().load(characterPictureList.get(position)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int originalWidth = bitmap.getWidth();
                int originalHeight = bitmap.getHeight();

                int cropHeight = originalHeight - targetHeightPX;

                if (cropHeight > 0) {
                    Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, originalWidth, targetHeightPX);
                    holder.characterPicture.setImageBitmap(croppedBitmap);
                } else {
                    holder.characterPicture.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {}

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView characterName, characterDifficulty;
        ImageView characterPicture;

        public ViewHolder(@NonNull View itemView, ListOfCharacterInterface listOfCharacterInterface, MainActivityViewModel viewModel) {
            super(itemView);
            characterPicture = itemView.findViewById(R.id.characterPicture);
            characterName = itemView.findViewById(R.id.characterName);
            characterDifficulty = itemView.findViewById(R.id.Level);

            itemView.setOnClickListener(view -> {
                if(listOfCharacterInterface != null)
                {
                    int position = viewModel.getCharacterNameList().getValue().indexOf(characterName.getText());
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listOfCharacterInterface.onItemClick(position);
                    }
                }
            });
        }
    }
}