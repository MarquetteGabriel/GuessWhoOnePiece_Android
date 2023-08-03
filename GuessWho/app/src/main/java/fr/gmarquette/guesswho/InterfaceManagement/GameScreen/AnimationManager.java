/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.InterfaceManagement.GameScreen;

import android.widget.ImageView;

public class AnimationManager
{
    public static void switchImage(ImageView oldImageView, ImageView newImageView)
    {
        oldImageView.animate().alpha(0f).setDuration(1000);
        newImageView.animate().alpha(1f).setDuration(1000);
    }
}
