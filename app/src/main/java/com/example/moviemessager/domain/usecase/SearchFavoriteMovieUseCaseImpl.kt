package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.SearchFavoriteMovieUseCase
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchFavoriteMovieUseCaseImpl@Inject constructor(private val favoriteRepository: FavoriteRepository): SearchFavoriteMovieUseCase {
    override suspend fun invoke(value: String): Flow<MovieUImodel.MovieModel?> {
     return withContext(Dispatchers.IO) {favoriteRepository.searchFavoriteMovie(value)}
    }
}