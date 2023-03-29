package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.UserModel

interface InitListCommentUseCase {
    suspend operator fun invoke( movieId: String,
                                 success: (String) -> Unit,
                                 error: (error: String) -> Unit,
                                 noUser: () -> Unit)
}