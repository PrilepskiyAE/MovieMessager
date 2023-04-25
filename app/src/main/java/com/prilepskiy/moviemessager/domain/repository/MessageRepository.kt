package com.prilepskiy.moviemessager.domain.repository

import com.prilepskiy.moviemessager.domain.model.MessageUser

interface MessageRepository {
    suspend fun sendMessage(message:MessageUser)
    suspend fun getMessagesListCurrentUser(uid: String,
                                           success:(list:List<MessageUser>)->Unit,
                                           error: (error: String) -> Unit)

}