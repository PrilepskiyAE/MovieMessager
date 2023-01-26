package com.example.moviemessager.data.repository

import androidx.paging.PagingSource
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.response.MovieListResponse
import com.example.moviemessager.data.response.MovieResponse
import com.example.moviemessager.data.utils.analyzeResponse
import com.example.moviemessager.data.utils.makeApiCall
import com.example.moviemessager.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl@Inject constructor(private val api: MovieApiService): MovieRepository {
    override suspend fun getListMovie(): PagingSource<Int, Pair<MovieResponse, Int>> = makeApiCall {
        analyzeResponse(
            api.getMovieList()
        )
    }
}