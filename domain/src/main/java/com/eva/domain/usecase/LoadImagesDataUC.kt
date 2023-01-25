package com.eva.domain.usecase

import android.util.Log
import com.eva.domain.RequestState
import com.eva.domain.model.ImageData
import com.eva.domain.repository.ImagesRepository
import com.eva.domain.utils.TAG
import com.eva.domain.utils.debugInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadImagesDataUC @Inject constructor(
    private val repository: ImagesRepository
) {
    
    fun invoke(count: Int, query: String): Flow<RequestState<List<ImageData>>> = flow {
        emit(RequestState.Loading)
        try {
            emit(RequestState.Success(repository.loadImages(count, query)))
        } catch (t: Throwable) {
            emit(RequestState.Error(t))
            Log.d(this@LoadImagesDataUC.TAG, t.debugInfo(showStackTrace = true))
        }
    }

}