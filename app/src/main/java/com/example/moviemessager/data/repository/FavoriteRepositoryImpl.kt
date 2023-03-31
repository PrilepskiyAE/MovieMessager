package com.example.moviemessager.data.repository

import com.example.moviemessager.data.dbservise.FavoriteDataBase
import com.example.moviemessager.data.dbservise.MovieEntity
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl@Inject constructor(private val db: FavoriteDataBase): FavoriteRepository {
    override suspend fun likeMovie(movie: MovieUImodel.MovieModel) {
        db.movieDao.insert(MovieEntity.from(movie))
    }

    override suspend fun dislikeMovie(movie: MovieUImodel.MovieModel) {
        db.movieDao.deleteMovie(data = movie.title)
    }

    override suspend fun getListFavoriteMovie(): Flow<List<MovieUImodel.MovieModel>> = db.movieDao.getAllMovie().map {
        MovieUImodel.MovieModel.from(it)
    }



    override suspend fun searchFavoriteMovie(value: String): Flow<MovieUImodel.MovieModel> = db.movieDao.getMovieByTitle(value).map {
        MovieUImodel.MovieModel.from(it)
    }
}