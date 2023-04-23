package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.SendMessageUseCase
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendMessageUseCaseImpl @Inject constructor(private val messageRepository: MessageRepository): SendMessageUseCase {
    override suspend fun invoke(message: MessageUser) {
        withContext(Dispatchers.IO) {messageRepository.sendMessage(message)}
    }
}