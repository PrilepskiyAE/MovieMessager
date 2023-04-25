package com.prilepskiy.moviemessager.domain.repository

import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun likeMovie(movie: MovieUImodel.MovieModel)
    suspend fun dislikeMovie(movie: MovieUImodel.MovieModel)
    suspend fun getListFavoriteMovie(): Flow<List<MovieUImodel.MovieModel>>
    suspend fun searchFavoriteMovie(value: String): Flow<MovieUImodel.MovieModel?>
}