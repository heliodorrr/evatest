package com.eva.presentation.di

import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Subcomponent

@Subcomponent

interface MainComponent {

    @Scope
    annotation class Scope

    interface Provider {
        val mainComponent: MainComponent
    }

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainComponent
    }

    fun multibindingFactory(): MultibindingFactory

}



