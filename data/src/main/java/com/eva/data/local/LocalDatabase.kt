package com.eva.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eva.data.model.Favorite

@Database(
    entities = [Favorite::class], version = 1
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun batchDao(): FavoriteImagesBatchDao
}