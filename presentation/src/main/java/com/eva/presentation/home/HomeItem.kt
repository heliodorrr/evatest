package com.eva.presentation.home

import com.eva.domain.model.ImageData

sealed class HomeItem {
    object Placeholder: HomeItem()
    class ActualItem(val value: ImageData): HomeItem()

    companion object {
        val DefaultPlaceholderList = generatePlaceHolderListForSize(4)
        fun generatePlaceHolderListForSize(size: Int) : List<Placeholder> {
            return buildList { repeat(10) { add(Placeholder) } }
        }
    }
}