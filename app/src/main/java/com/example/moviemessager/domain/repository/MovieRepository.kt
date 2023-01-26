package com.example.moviemessager.domain.repository

import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.response.MovieListResponse

interface MovieRepository {
    suspend fun getListMovie(): ActionResult<MovieListResponse>
}