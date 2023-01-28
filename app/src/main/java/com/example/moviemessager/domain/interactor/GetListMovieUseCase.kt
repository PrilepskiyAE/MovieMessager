package com.example.moviemessager.domain.interactor

import androidx.paging.PagingData
import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetListMovieUseCase {
    suspend operator fun invoke(queryOptions: List<Pair<String, Any>> = emptyList()): Flow<PagingData<Pair<MovieUImodel, Int>>>
}