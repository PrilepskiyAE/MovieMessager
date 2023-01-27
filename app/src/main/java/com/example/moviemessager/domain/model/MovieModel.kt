package com.example.moviemessager.domain.model

import com.example.moviemessager.data.response.MovieResponse
import com.example.moviemessager.data.utils.getUniqueId

data class MovieModel(
    override val id: Long,
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
    val vote_count:Int = 0
) : BaseAdapterTypes() {
    companion object{
        fun from(data:MovieResponse):MovieModel=with(data){
            MovieModel(
                id = getUniqueId(),
                adult = adult,
                backdrop_path = backdrop_path,
                genre_ids = genre_ids,
                original_title = original_title,
                original_language = original_language,
                overview = overview,
                popularity = popularity,
                poster_path = poster_path,
                release_date = release_date,
                title = title,
                video = video,
                vote_average = vote_average,
                vote_count = vote_count
            )
        }
    }

}