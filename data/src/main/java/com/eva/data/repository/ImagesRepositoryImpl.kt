package com.eva.data.repository

import com.eva.data.ImageDataDto
import com.eva.data.di.ClientId
import com.eva.data.local.FavoriteImagesBatchDao
import com.eva.data.remote.UnsplashApi
import com.eva.domain.model.ImageData
import com.eva.domain.repository.ImagesRepository
import com.eva.domain.utils.fastlog
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    val dao: FavoriteImagesBatchDao,
    @ClientId
    private val clientId: String
): ImagesRepository {

    //val repositoryScope =  CoroutineScope(Job() + Dispatchers.IO)

    override suspend fun loadImages(count: Int, query:String): List<ImageData> {
        val remoteData = api.loadImagesData(clientId,30, query)
        syncWithDb(remoteData)
        val favoriteStates = obtainFavoriteStates(remoteData)

        return remoteData.map {
            it.toImageData(favoriteStates[it.imageId] ?: false)
        }
    }

    private suspend fun syncWithDb(data: List<ImageDataDto>) {
        data.forEach {
            fastlog("syncWithDb")
            fastlog(it.imageId)
            dao.insertIfAbsent(imageId = it.imageId, isFavorite = false)
        }
    }

    private suspend fun obtainFavoriteStates(data: List<ImageDataDto>): Map<String, Boolean> {
        val range = data.map { it.imageId }
        return buildMap {
            fastlog("SELECT IF PRESENT")
            dao.selectPresent(range).forEach {
                this[it.imageId] = it.isFavorite
            }
        }
    }


}