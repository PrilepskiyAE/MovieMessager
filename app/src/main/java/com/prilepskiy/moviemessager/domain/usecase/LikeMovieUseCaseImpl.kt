package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.LikeMovieUseCase
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import com.prilepskiy.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeMovieUseCaseImpl@Inject constructor(private val favoriteRepository: FavoriteRepository): LikeMovieUseCase {
    override suspend fun invoke(movie: MovieUImodel.MovieModel) {
        withContext(Dispatchers.IO) {favoriteRepository.likeMovie(movie)}
    }
}