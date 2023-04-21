package com.example.moviemessager.domain.repository

import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.UserModel

interface MessageRepository {
    suspend fun sendMessage(message:MessageUser)
    suspend fun getMessagesListCurrentUser(userEmail:String,
                                           error: (error: String) -> Unit):List<MessageUser>

}