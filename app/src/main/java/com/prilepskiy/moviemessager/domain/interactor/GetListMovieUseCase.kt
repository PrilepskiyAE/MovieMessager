package com.prilepskiy.moviemessager.domain.interactor

import androidx.paging.PagingData
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetListMovieUseCase {
  operator fun invoke(genres:Int): Flow<PagingData<MovieUImodel>>
}