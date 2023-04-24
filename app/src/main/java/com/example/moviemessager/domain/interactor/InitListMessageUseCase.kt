package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.MessageUser

interface InitListMessageUseCase {
    suspend operator fun invoke( uid: String,
                                 error: (error: String) -> Unit): List<MessageUser>
}