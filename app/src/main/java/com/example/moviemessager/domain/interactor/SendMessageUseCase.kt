package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.MessageUser

interface SendMessageUseCase {
    suspend operator fun invoke(message: MessageUser)
}