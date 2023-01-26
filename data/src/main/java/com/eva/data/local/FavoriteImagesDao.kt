package com.eva.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteImagesDao {

    @Query("REPLACE INTO favorite(image_id, is_favorite) VALUES (:imageId, :isFavorite)")
    suspend fun insertOrReplace(imageId: String, isFavorite: Boolean)

    @Query("SELECT is_favorite FROM favorite WHERE image_id = :imageId")
    fun findIsFavoriteByImageId(imageId: String): Flow<Boolean?>

}