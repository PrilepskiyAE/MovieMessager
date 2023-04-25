package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.SendMessageUseCase
import com.prilepskiy.moviemessager.domain.model.MessageUser
import com.prilepskiy.moviemessager.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendMessageUseCaseImpl @Inject constructor(private val messageRepository: MessageRepository): SendMessageUseCase {
    override suspend fun invoke(message: MessageUser) {
        withContext(Dispatchers.IO) {messageRepository.sendMessage(message)}
    }
}