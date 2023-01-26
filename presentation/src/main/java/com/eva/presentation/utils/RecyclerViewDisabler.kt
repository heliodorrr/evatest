package com.eva.presentation.utils

import android.content.Context
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutManagerWithDisabler(
    context: Context,
    orientation: Int
) : GridLayoutManager(context, orientation) {
    internal var canScroll = true

    override fun canScrollVertically(): Boolean {
        return canScroll
    }
}



fun RecyclerView.setCanScroll(canScroll: Boolean) {
    val lm = layoutManager
    if (lm !is GridLayoutManagerWithDisabler) return
    lm.canScroll = canScroll
}