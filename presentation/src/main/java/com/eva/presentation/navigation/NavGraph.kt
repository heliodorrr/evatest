package com.eva.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgument
import com.eva.domain.model.ImageData
import com.eva.presentation.details.DetailsFragment
import com.eva.presentation.home.HomeFragment

fun NavController.mainGraph() = createGraph(NavRoutes.Home.Route) {
    fragment<HomeFragment>(NavRoutes.Home.Route) {

    }

    fragment<DetailsFragment>(NavRoutes.Details.Route) {
        navArgument(NavRoutes.Details.ImageDataArgument) {
            this.type = NavType.ParcelableType(ImageData::class.java)
        }
    }
}