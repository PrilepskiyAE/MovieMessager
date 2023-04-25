package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.SendCommentUseCase
import com.prilepskiy.moviemessager.domain.repository.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendCommentUseCaseImpl @Inject constructor(private val commentRepository: CommentsRepository) :
    SendCommentUseCase {
    override suspend fun invoke(message: String,movieId: String) {
        withContext(Dispatchers.IO) {
            commentRepository.sendComment(movieId, message)
        }
    }
}