package com.eva.presentation.animation


import androidx.navigation.AnimBuilder

fun AnimBuilder.defaultAnimations() {
    enter = android.R.anim.fade_in
    exit = android.R.anim.fade_out
    popEnter = android.R.anim.fade_in
    popExit = android.R.anim.fade_out
}