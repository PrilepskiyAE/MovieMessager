package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MessageUser

interface SendMessageUseCase {
    suspend operator fun invoke(message: MessageUser)
}