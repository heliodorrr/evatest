package com.eva.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData(
    val id: String,
    val username: String,
    val url: String,
    val date: String,
    val likes: Int,
    val blurHash: String,
): Parcelable