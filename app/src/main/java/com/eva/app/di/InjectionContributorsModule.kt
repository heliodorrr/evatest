package com.eva.app.di

import com.eva.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
interface InjectionContributorsModule {

    @Singleton
    @ContributesAndroidInjector
    fun injectHomeFragment(): HomeFragment
}