package com.eva.presentation.navigation


object nav_routes {
    val home = "home"
    val details = "details"
}


object nav_args {
    val image_data = "image_data"
}

sealed class NavRoutes(
    val Route: String
) {
    object Home : NavRoutes("home") {

    }
    object Details : NavRoutes("details") {
        const val ImageDataArgument = "image_data"
    }
}