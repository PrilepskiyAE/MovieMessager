package com.example.moviemessager.domain.repository

import androidx.paging.PagingSource
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.response.MovieListResponse
import com.example.moviemessager.data.response.MovieResponse
import com.example.moviemessager.domain.model.MovieUImodel

interface MovieRepository {
    fun getListMovie( queryOptions: List<Pair<String, Any>>): PagingSource<Int, Pair<MovieUImodel, Int>>
}