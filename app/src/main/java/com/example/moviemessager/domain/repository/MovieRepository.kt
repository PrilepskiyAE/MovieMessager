package com.example.moviemessager.domain.repository

import androidx.paging.PagingSource
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.response.MovieListResponse
import com.example.moviemessager.data.response.MovieResponse

interface MovieRepository {
    suspend fun getListMovie(): PagingSource<Int, Pair<MovieResponse, Int>>
}