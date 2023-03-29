package com.example.moviemessager.domain.repository

import com.example.moviemessager.domain.model.UserModel

interface CommentsRepository {
    suspend fun initListComment( movieId: String,
                                 success: (String) -> Unit,
                                 error: (error: String) -> Unit,
                                 noUser: () -> Unit)
    suspend fun sendComment(movieId: String,message:String)
}