package com.example.moviemessager.data.dbservise

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao:BaseDao<MovieEntity>() {

    @Query("SELECT * FROM movie_table")
    abstract fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE title=:data")
    abstract fun getMovieByTitle(data:String): Flow<MovieEntity>

    @Query("DELETE FROM movie_table WHERE title=:data")
    abstract suspend fun deleteMovie(data:String)
}