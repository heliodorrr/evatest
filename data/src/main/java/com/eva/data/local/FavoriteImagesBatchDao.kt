package com.eva.data.local

import androidx.room.Dao
import androidx.room.Query
import com.eva.data.model.Favorite

@Dao()
interface FavoriteImagesBatchDao {

    @Query("REPLACE INTO favorite(image_id, is_favorite) VALUES (:imageId, :isFavorite)")
    suspend fun insertOrReplace(imageId: String, isFavorite: Boolean)

    @Query("INSERT OR IGNORE INTO favorite(image_id, is_favorite) VALUES (:imageId, :isFavorite)")
    suspend fun insertIfAbsent(imageId: String, isFavorite: Boolean)

    @Query("SELECT * FROM favorite WHERE image_id IN (:imageIds)")
    suspend fun selectPresent(imageIds: List<String>): List<Favorite>

    //@Query("SELECT is_favorite FROM favorite WHERE image_id = :imageId")
    //suspend fun findByImageId(imageId: String): Flow<Boolean?>

    //@Query("SELECT is_favorite FROM favorite WHERE image_id = :imageId")
    //suspend fun findByImageId(imageId: String): Flow<Boolean?>

    //@Query("UPDATE favorite SET is_favorite = :isFavorite WHERE image_id = :imageId")
    //suspend fun updateIsFavorite(imageId: String, isFavorite: Boolean): Flow<Boolean?>

}