package com.eva.presentation.navigation


sealed class NavRoutes(
    val Route: String
) {
    object Home : NavRoutes("home") {

    }
    object Details : NavRoutes("details") {
        const val ImageDataArgument = "image_data"
    }
}