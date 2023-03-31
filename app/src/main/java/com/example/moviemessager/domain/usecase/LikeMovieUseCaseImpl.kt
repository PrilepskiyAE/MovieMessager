package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.LikeMovieUseCase
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeMovieUseCaseImpl@Inject constructor(private val favoriteRepository: FavoriteRepository): LikeMovieUseCase {
    override suspend fun invoke(movie: MovieUImodel.MovieModel) {
        withContext(Dispatchers.IO) {favoriteRepository.likeMovie(movie)}
    }
}