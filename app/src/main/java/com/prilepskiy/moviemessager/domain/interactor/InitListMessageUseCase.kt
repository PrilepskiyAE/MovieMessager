package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.MessageUser

interface InitListMessageUseCase {
    suspend operator fun invoke(uid: String,
                                success:(list:List<MessageUser>)->Unit,
                                error: (error: String) -> Unit)
}