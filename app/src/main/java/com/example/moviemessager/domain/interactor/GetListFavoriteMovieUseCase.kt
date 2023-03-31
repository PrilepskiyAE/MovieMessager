package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetListFavoriteMovieUseCase {
    suspend operator fun invoke(): Flow<List<MovieUImodel.MovieModel>>
}