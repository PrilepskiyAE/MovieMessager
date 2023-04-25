package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MovieUImodel

interface DislikeMovieUseCase {
    suspend operator fun invoke(movie: MovieUImodel.MovieModel)
}