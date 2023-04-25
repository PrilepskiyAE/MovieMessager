package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface SearchFavoriteMovieUseCase {
    suspend operator fun invoke(value: String): Flow<MovieUImodel.MovieModel?>
}