package com.example.moviemessager.data.dbservise

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviemessager.data.dbservise.FavoriteDataBase.Companion.VERSION_SCHEMA

@Database(
    entities = [MovieEntity::class] ,
    version = VERSION_SCHEMA,
    exportSchema = true
)
abstract class FavoriteDataBase: RoomDatabase() {
    abstract val movieDao: MovieDao
    companion object{
        const val VERSION_SCHEMA=1
    }
}