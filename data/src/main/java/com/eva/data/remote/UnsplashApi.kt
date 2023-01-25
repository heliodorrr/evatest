package com.eva.data.remote

import com.eva.data.ImageDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("random")
    suspend fun loadImagesData(
        @Query("client_id") clientId: String,
        @Query("count") count: Int,
        @Query("query") query: String
    ): List<ImageDataDto>

}