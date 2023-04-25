package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.InitListMessageUseCase
import com.prilepskiy.moviemessager.domain.model.MessageUser

import com.prilepskiy.moviemessager.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InitListMessageUseCaseImpl @Inject constructor(private val messageRepository: MessageRepository): InitListMessageUseCase {
    override suspend fun invoke(
        uid: String,
        success:(list:List<MessageUser>)->Unit,
        error: (error: String) -> Unit
    ) {
         withContext(Dispatchers.IO) {messageRepository.getMessagesListCurrentUser(uid,success, error)}
    }
}