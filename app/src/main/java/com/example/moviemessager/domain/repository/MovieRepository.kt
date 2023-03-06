package com.example.moviemessager.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.response.MovieListResponse
import com.example.moviemessager.data.response.MovieResponse
import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getListMovie(genres:Int): Flow<PagingData<MovieUImodel>>
}