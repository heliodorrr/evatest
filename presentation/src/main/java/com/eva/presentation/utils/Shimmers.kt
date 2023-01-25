package com.eva.presentation.utils

import android.graphics.Color
import com.facebook.shimmer.Shimmer

object Shimmers {
    val DefaultShimmer: Shimmer = Shimmer.ColorHighlightBuilder()
        .setHighlightAlpha(1f)
        .setBaseAlpha(0.4f)
        .setBaseColor(Color.GRAY)
        .setWidthRatio(2f)
        .setDuration(600L)
        .build()
}
