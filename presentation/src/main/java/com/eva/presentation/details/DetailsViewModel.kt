package com.eva.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eva.domain.model.ImageData
import com.eva.domain.usecase.ManageImageIsFavoriteUC
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val manageImageIsFavoriteUC: ManageImageIsFavoriteUC
): ViewModel() {
    private val switchIsInProcess = AtomicBoolean()

    private val imageDataStateFlow = MutableStateFlow<ImageData?>(null)
    private val isFavoriteLiveData = MutableLiveData(false)

    init {
        viewModelScope.launch {
            imageDataStateFlow
                .collectLatest { imageData->
                    if (imageData == null) return@collectLatest
                    manageImageIsFavoriteUC.getIsFavoriteFlowForId(imageData.id)
                        .collectLatest {
                            isFavoriteLiveData.postValue(it)
                        }
                }
        }
    }


    fun setImageData(imageData: ImageData) {
        imageDataStateFlow.value = imageData
    }

    internal fun switchIsFavorite() {
        val imageData = imageDataStateFlow.value!!
        if (switchIsInProcess.get()) return

        switchIsInProcess.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val switchedValue = !isFavoriteLiveData.value!!
            manageImageIsFavoriteUC.setIsFavorite(imageData.id, switchedValue)
            switchIsInProcess.set(false)
        }
    }

    internal fun observeImageDetails(lo: LifecycleOwner, lambda: (ImageData?)->Unit) {
        imageDataStateFlow
            .asLiveData()
            .observe(lo, lambda)
    }

    internal fun observeIsFavorite(lo: LifecycleOwner, lambda: (Boolean)->Unit) {
        isFavoriteLiveData
            .observe(lo, lambda)
    }

}