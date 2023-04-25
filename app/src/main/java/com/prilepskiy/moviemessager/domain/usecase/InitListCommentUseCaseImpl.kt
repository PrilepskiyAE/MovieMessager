package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.InitListCommentUseCase
import com.prilepskiy.moviemessager.domain.repository.CommentsRepository
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
        withContext(Dispatchers.Main) {
            commentRepository.initListComment(movieId, success, error, noUser)
        }
    }
}