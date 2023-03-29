package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.InitListCommentUseCase
import com.example.moviemessager.domain.repository.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InitListCommentUseCaseImpl @Inject constructor(private val commentRepository: CommentsRepository) :
    InitListCommentUseCase {
    override suspend fun invoke(
        movieId: String,
        success: (String) -> Unit,
        error: (error: String) -> Unit,
        noUser: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            commentRepository.initListComment(movieId, success, error, noUser)
        }
    }
}