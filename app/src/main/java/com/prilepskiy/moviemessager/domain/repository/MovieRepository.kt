package com.prilepskiy.moviemessager.domain.repository

import androidx.paging.PagingData
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getListMovie(genres:Int): Flow<PagingData<MovieUImodel>>
}