package com.eva.data.model

import com.eva.domain.model.ImageData

data class ImageDataDto(
    val imageId: String,
    val username: String,
    val url: String,
    val date: String,
    val likes: Int,
    val blurHash: String,
){
    fun toImageData()
    = ImageData(
        id = imageId,
        username = username,
        url = url,
        date = date,
        likes = likes,
        blurHash = blurHash,
    )
}