package com.example.moviemessager.domain.interactor

import androidx.paging.PagingData
import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetListMovieUseCase {
  operator fun invoke(): Flow<PagingData<MovieUImodel>>
}