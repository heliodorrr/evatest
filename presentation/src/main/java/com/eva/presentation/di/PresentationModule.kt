package com.eva.presentation.di

import androidx.lifecycle.ViewModel
import com.eva.presentation.details.DetailsViewModel
import com.eva.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainComponent::class])
//@Module()
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

}