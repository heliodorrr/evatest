package com.eva.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
class Favorite(
    @PrimaryKey()
    @ColumnInfo("image_id")
    val imageId: String,

    @ColumnInfo("is_favorite")
    val isFavorite: Boolean
)