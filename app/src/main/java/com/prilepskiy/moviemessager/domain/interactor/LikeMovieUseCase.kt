package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MovieUImodel

interface LikeMovieUseCase {
    suspend operator fun invoke(movie: MovieUImodel.MovieModel)
}