package com.prilepskiy.moviemessager.domain.interactor

interface InitListCommentUseCase {
    suspend operator fun invoke( movieId: String,
                                 success: (String) -> Unit,
                                 error: (error: String) -> Unit,
                                 noUser: () -> Unit)
}