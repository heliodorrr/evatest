package com.eva.presentation.details

import androidx.lifecycle.ViewModel
import com.eva.domain.model.ImageData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsViewModel @Inject constructor(): ViewModel() {

    val isFavoriteFlow: Flow<Boolean> = flow {  }

    internal var imageData: ImageData? = null


    fun setImageData(imageData: ImageData) {
        this.imageData = imageData
    }

}