package com.eva.domain.repository

import com.eva.domain.model.ImageData
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun loadImages(count: Int, query:String): List<ImageData>
    suspend fun imageIsFavoriteFlow(imageId: String): Flow<Boolean?>
    suspend fun setImageIsFavorite(imageId: String, isFavorite: Boolean)
}