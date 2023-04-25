package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.DislikeMovieUseCase
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import com.prilepskiy.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DislikeMovieUseCaseImpl@Inject constructor(private val favoriteRepository: FavoriteRepository): DislikeMovieUseCase {
    override suspend fun invoke(movie: MovieUImodel.MovieModel) {
        withContext(Dispatchers.IO) {favoriteRepository.dislikeMovie(movie)}
    }
}