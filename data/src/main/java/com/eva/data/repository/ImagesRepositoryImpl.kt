package com.eva.data.repository

import com.eva.data.di.ClientId
import com.eva.data.local.FavoriteImagesDao
import com.eva.data.remote.UnsplashApi
import com.eva.domain.model.ImageData
import com.eva.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val dao: FavoriteImagesDao,
    @ClientId
    private val clientId: String
): ImagesRepository {

    //val repositoryScope =  CoroutineScope(Job() + Dispatchers.IO)

    override suspend fun loadImages(count: Int, query:String): List<ImageData> {
        val remoteData = api.loadImagesData(clientId,30, query)

        return remoteData.map { it.toImageData() }
    }

    override suspend fun imageIsFavoriteFlow(imageId: String): Flow<Boolean?> {
        return dao.findIsFavoriteByImageId(imageId)
    }

    override suspend fun setImageIsFavorite(imageId: String, isFavorite: Boolean) {
        dao.insertOrReplace(imageId, isFavorite)
    }


}