package com.example.moviemessager.data.repository

import androidx.paging.PagingSource
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.paging.MoviePagingSource
import com.example.moviemessager.data.response.MovieListResponse
import com.example.moviemessager.data.response.MovieResponse
import com.example.moviemessager.data.utils.analyzeResponse
import com.example.moviemessager.data.utils.makeApiCall
import com.example.moviemessager.domain.model.MovieUImodel

import com.example.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class MovieRepositoryImpl@Inject constructor(private val api: MovieApiService): MovieRepository {
    override  fun getListMovie( queryOptions: List<Pair<String, Any>>): PagingSource<Int, Pair<MovieUImodel, Int>> = MoviePagingSource(
        queryOptions,api
    )

//    override suspend fun getListMoviePages(): List<MovieModel> {
//        val result: MutableList<MovieModel> = mutableListOf()
//        for (i in 1.. 400)
//     {
//            delay(1000)
//         val apiData = makeApiCall {
//             analyzeResponse(
//                 api.getMovieListPsged(page = i.toString())
//                 )
//
//         }
//         when (apiData) {
//             is ActionResult.Success -> {
//                 apiData.data.results.onEach {
//                     result.add(MovieModel.from(it))
//                 }
//             }
//
//             is ActionResult.Error -> ActionResult.Error(apiData.errors)
//         }
//     }
//       return result
//    }
}