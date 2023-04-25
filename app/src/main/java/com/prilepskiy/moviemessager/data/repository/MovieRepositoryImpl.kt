package com.prilepskiy.moviemessager.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prilepskiy.moviemessager.data.apiservise.MovieApiService
import com.prilepskiy.moviemessager.data.paging.MoviePagingSource
import com.prilepskiy.moviemessager.domain.model.MovieUImodel

import com.prilepskiy.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl@Inject constructor(private val api: MovieApiService): MovieRepository {
    override fun getListMovie(genres:Int): Flow<PagingData<MovieUImodel>> {
        return Pager(config = PagingConfig(
            pageSize = 32,
        ), pagingSourceFactory ={ MoviePagingSource(api,genres)} ).flow
    }



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