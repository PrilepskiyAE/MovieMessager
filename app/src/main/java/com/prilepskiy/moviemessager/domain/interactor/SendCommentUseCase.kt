package com.prilepskiy.moviemessager.domain.interactor

interface SendCommentUseCase {
    suspend operator fun invoke(message:String,movieId: String)
}