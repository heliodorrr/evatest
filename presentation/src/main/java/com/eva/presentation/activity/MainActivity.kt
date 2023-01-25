package com.eva.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.eva.domain.utils.TAG
import com.eva.presentation.R
import com.eva.presentation.navigation.mainGraph
import com.eva.presentation.navigation.nav_routes


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val controller = findViewById<FragmentContainerView>(R.id.fragmentContainerView)
            .getFragment<NavHostFragment>()
            .navController


        val graph = controller.mainGraph()
        controller.graph = graph

        //controller.navigate(nav_routes.home)
        Log.d(TAG, "setupNavigation: ")

    }


}