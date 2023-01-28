package com.example.moviemessager.data.paging

import android.util.Log
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.core.data
import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.response.GenresResponse
import com.example.moviemessager.data.utils.analyzeResponse
import com.example.moviemessager.data.utils.makeApiCall
import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MoviePagingSource(apiQueries: List<Pair<String, Any>>, private val api: MovieApiService) :
    BaseNetworkPagingSource<MovieUImodel>(apiQueries) {
    val movieList = mutableListOf<MovieUImodel>(
        MovieUImodel.Title("Жанры"),MovieUImodel.Genre(GenresResponse.list_genres), MovieUImodel.Title("Фильмы")
    )
    override suspend fun getDataFromSource(queryMap: Map<String, Any>): ActionResult<Pair<List<MovieUImodel>, Int>> {

        val apiData = makeApiCall {

            analyzeResponse(

                api.getMovieList(queryMap = queryMap)
            )
        }
        return when (apiData) {
            is ActionResult.Success -> {

                apiData.data.results.onEach {

                    movieList.add(MovieUImodel.MovieModel.from(it))
                }
                ActionResult.Success(Pair(movieList, apiData.data.page))
            }
            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }
        }
    }
}