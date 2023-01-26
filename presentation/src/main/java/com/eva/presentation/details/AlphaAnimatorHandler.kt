package com.eva.presentation.details

import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi

internal class AlphaAnimatorHandler(
    private vararg val views: View
) {

    private var isForward = true

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 250
        interpolator = LinearInterpolator()

        addUpdateListener {
            val alpha = it.animatedValue as Float

            views.forEach { view ->
                view.alpha = alpha
            }
        }
    }

    fun switchVisibility() {
        if (isForward) {
            animator.reverse()
            isForward = false
        } else {
            animator.start()
            isForward = true
        }
    }


}