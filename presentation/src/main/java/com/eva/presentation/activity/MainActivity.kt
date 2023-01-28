package com.eva.presentation.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.eva.presentation.R
import com.eva.presentation.navigation.applyMainNavGraph


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        applyMainNavGraph(navController)
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.popBackStack()
            }
        })
    }

    private val navController get () =
        findViewById<FragmentContainerView>(R.id.fragmentContainerView)
        .getFragment<NavHostFragment>()
        .navController

}