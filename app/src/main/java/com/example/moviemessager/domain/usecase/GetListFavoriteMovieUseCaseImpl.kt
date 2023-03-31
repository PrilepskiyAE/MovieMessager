package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.GetListFavoriteMovieUseCase
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListFavoriteMovieUseCaseImpl@Inject constructor(private val favoriteRepository: FavoriteRepository): GetListFavoriteMovieUseCase {
    override suspend fun invoke(): Flow<List<MovieUImodel.MovieModel>> {
     return   withContext(Dispatchers.IO) {favoriteRepository.getListFavoriteMovie()}
    }
}