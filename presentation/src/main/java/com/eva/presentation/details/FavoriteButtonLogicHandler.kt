package com.eva.presentation.details

import android.view.View.OnClickListener
import android.widget.ImageView
import com.eva.presentation.R
import com.eva.presentation.utils.getDrawableCompat

internal class FavoriteButtonLogicHandler(
    private val imageView: ImageView
) {
    private val filledIcon = imageView.context.getDrawableCompat(R.drawable.star_filled)
    private val hollowIcon = imageView.context.getDrawableCompat(R.drawable.star_hollow)

    fun setFavorite(isFavorite: Boolean) {
        when(isFavorite) {
            true -> imageView.setImageDrawable(filledIcon)
            false -> imageView.setImageDrawable(hollowIcon)
        }
    }

    fun addOnClickListener(onClickListener: OnClickListener) {
        imageView.setOnClickListener(onClickListener)
    }
}