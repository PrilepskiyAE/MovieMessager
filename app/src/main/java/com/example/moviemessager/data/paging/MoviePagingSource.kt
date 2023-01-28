package com.example.moviemessager.data.paging

import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.core.data
import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.utils.analyzeResponse
import com.example.moviemessager.data.utils.makeApiCall

import com.example.moviemessager.domain.model.MovieModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MoviePagingSource( apiQueries: List<Pair<String,Any>>,private val api: MovieApiService) : BaseNetworkPagingSource<MovieModel>(apiQueries){
    override suspend fun getDataFromSource(queryMap: Map<String, Any>): ActionResult<Pair<List<MovieModel>, Int>> {
        val movieList = mutableListOf<MovieModel>()
        val apiData = makeApiCall {

                    analyzeResponse(

                       api.getMovieList(queryMap = queryMap)
                    )
        }
        return when(apiData){
            is ActionResult.Success -> {
                apiData.data.results.onEach {
                  movieList.add(MovieModel.from(it))
                }
                 ActionResult.Success(Pair(movieList, apiData.data.total_pages))
             }
            is ActionResult.Error -> {ActionResult.Error(apiData.errors)}
        }
    }
}