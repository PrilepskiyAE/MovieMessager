package com.example.moviemessager.data.repository

import com.example.moviemessager.data.dbservise.FavoriteDataBase
import com.example.moviemessager.data.dbservise.MovieEntity
import com.example.moviemessager.data.firebaseservise.FirebaseService
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl@Inject constructor(private val db: FavoriteDataBase): FavoriteRepository {
    override suspend fun likeMovie(movie: MovieUImodel.MovieModel) {
        db.movieDao.insert(MovieEntity.from(movie, FirebaseService.getFirebaseAuth().currentUser?.email.toString()))
    }

    override suspend fun dislikeMovie(movie: MovieUImodel.MovieModel) {
        db.movieDao.deleteMovie(data = movie.id.toString(),FirebaseService.getFirebaseAuth().currentUser?.email.toString())
    }

    override suspend fun getListFavoriteMovie(): Flow<List<MovieUImodel.MovieModel>> = db.movieDao.getAllMovie(FirebaseService.getFirebaseAuth().currentUser?.email.toString()).map {
        MovieUImodel.MovieModel.from(it)
    }



    override suspend fun searchFavoriteMovie(value: String): Flow<MovieUImodel.MovieModel?> = db.movieDao.getMovieByTitle(value,FirebaseService.getFirebaseAuth().currentUser?.email.toString()).map {
        if (it!=null)
        MovieUImodel.MovieModel.from(it)
        else null
    }
}