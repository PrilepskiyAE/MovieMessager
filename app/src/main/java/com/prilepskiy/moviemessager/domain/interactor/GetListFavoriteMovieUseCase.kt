package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetListFavoriteMovieUseCase {
    suspend operator fun invoke(): Flow<List<MovieUImodel.MovieModel>>
}