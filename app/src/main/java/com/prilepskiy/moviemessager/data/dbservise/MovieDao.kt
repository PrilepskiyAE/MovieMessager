package com.prilepskiy.moviemessager.data.dbservise

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao:BaseDao<MovieEntity>() {

    @Query("SELECT * FROM movie_table WHERE user=:user")
    abstract fun getAllMovie(user:String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE id=:data AND user=:user")
    abstract fun getMovieByTitle(data:String,user:String): Flow<MovieEntity?>

    @Query("DELETE FROM movie_table WHERE id=:data AND user=:user")
    abstract suspend fun deleteMovie(data:String,user:String)
}