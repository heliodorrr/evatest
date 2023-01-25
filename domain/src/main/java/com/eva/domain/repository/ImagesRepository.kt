package com.eva.domain.repository

import com.eva.domain.model.ImageData

interface ImagesRepository {
    suspend fun loadImages(count: Int, query:String): List<ImageData>
}