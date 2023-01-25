package com.eva.data.di

import android.content.Context
import androidx.room.Room
import com.eva.data.ImageDataDeserializer
import com.eva.data.ImageDataDto
import com.eva.data.local.FavoriteImagesBatchDao
import com.eva.data.local.LocalDatabase
import com.eva.data.remote.UnsplashApi
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataLayerImplModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): UnsplashApi {

        val gson = GsonBuilder()
            .registerTypeAdapter(
                object : TypeToken<List<ImageDataDto>>() {}.type,
                ImageDataDeserializer()
            )
            .registerTypeAdapter(List::class.java, ImageDataDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/photos/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UnsplashApi::class.java)

    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): LocalDatabase {
        return Room
            .databaseBuilder(context, LocalDatabase::class.java, "local_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteImagesBatchDao(db: LocalDatabase): FavoriteImagesBatchDao {
        return db.batchDao()
    }

    @Provides
    @ClientId
    fun provideClientId() = "ZhsST8zH0pMuCDszNqGhFF-SXupn-SSffz95stK9qdQ"



}