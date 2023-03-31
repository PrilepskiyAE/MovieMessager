package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.MovieUImodel

interface LikeMovieUseCase {
    suspend operator fun invoke(movie: MovieUImodel.MovieModel)
}