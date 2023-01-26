package com.eva.domain.usecase

import com.eva.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ManageImageIsFavoriteUC @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    suspend fun getIsFavoriteFlowForId(imageId: String): Flow<Boolean> {
        return imagesRepository.imageIsFavoriteFlow(imageId)
            .map{ it ?: false }
    }

    suspend fun setIsFavorite(imageId: String, isFavorite: Boolean) {
        imagesRepository.setImageIsFavorite(imageId, isFavorite)
    }

}