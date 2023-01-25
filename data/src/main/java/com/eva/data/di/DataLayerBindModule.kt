package com.eva.data.di

import com.eva.data.repository.ImagesRepositoryImpl
import com.eva.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataLayerBindModule {

    @Binds
    @Singleton
    fun bindImagesDataRepository(impl: ImagesRepositoryImpl): ImagesRepository

}