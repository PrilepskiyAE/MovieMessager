package com.prilepskiy.moviemessager.data.dbservise

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.prilepskiy.moviemessager.domain.model.MovieUImodel


@Entity(tableName = "movie_table")
@TypeConverters(MovieEntityConverter::class)
data class MovieEntity(
        @PrimaryKey
        val id: Long,
        val adult:Boolean = false,
        val backdrop_path: String,
        val genre_ids: List<String>,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity:Double = 0.0,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video:Boolean = false,
        val vote_average:Double = 0.0,
        val vote_count:Int = 0,
        val user: String
    ){
        companion object{
                fun from(data: MovieUImodel.MovieModel,user: String): MovieEntity =with(data){
                        MovieEntity(
                                id = id,
                                adult = adult,
                                backdrop_path = backdrop_path ?: "",
                                genre_ids = genre_ids,
                                original_title = original_title ?: "",
                                original_language = original_language ?: "",
                                overview = overview ?: "",
                                popularity = popularity,
                                poster_path = poster_path ?: "",
                                release_date = release_date ?: "",
                                title = title ?: "",
                                video = video,
                                vote_average = vote_average ?: 0.0,
                                vote_count = vote_count,
                                user = user
                        )
                }
        }
}
