package com.eva.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eva.domain.RequestState
import com.eva.domain.model.ImageData
import com.eva.domain.usecase.LoadImagesDataUC
import com.eva.domain.utils.debugInfo
import com.eva.domain.utils.fastlog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadImagesDataUC: LoadImagesDataUC
) : ViewModel() {

    companion object {
        private const val QuerySize = 30
    }

    private val queryStateFlow = MutableStateFlow("")


    @OptIn(ExperimentalCoroutinesApi::class)
    private val imageUrlsLD =
        queryStateFlow
            .transformLatest <String, RequestState<List<ImageData>>> { value ->
                val imagesDataFlow = loadImagesDataUC.invoke(QuerySize, value)

                coroutineScope {
                    while (this@coroutineScope.isActive) {
                        imagesDataFlow.collect { request ->
                            emit(request)
                            when(request) {
                                is RequestState.Success -> {
                                    this@coroutineScope.cancel()
                                }
                                is RequestState.Error -> {
                                    fastlog(request.exception.debugInfo(true))
                                }
                                else -> {}
                            }
                        }
                    }
                }

            }
            .flowOn(Dispatchers.IO)
            .asLiveData(timeoutInMs = Long.MAX_VALUE)

    internal fun emitQuery(query: String) {
        queryStateFlow.value = query
    }

    fun subscribeToRequestEvents(
        lo: LifecycleOwner, observer: (RequestState<List<ImageData>>)->Unit
    ) {
        imageUrlsLD.observe(lo, observer)
    }

    fun subscribeToImageUrls(lo: LifecycleOwner, observer: (List<HomeItem>)->Unit) {
        imageUrlsLD.observe(lo) { request->
            if (request is RequestState.Success) {
                observer(request.result.map { HomeItem.ActualItem(it) })
            } else {
                observer(HomeItem.DefaultPlaceholderList)
            }

        }
    }

}