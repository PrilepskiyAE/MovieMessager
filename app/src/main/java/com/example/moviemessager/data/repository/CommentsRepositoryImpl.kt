package com.example.moviemessager.data.repository

import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.repository.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl@Inject constructor(): CommentsRepository {
    override suspend fun initListComment( movieId: String,
                                          success: (comment: List<String>) -> Unit,
                                          error: (error: String) -> Unit,
                                          noUser: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun sendComment(movieId: String,message:String) {
        TODO("Not yet implemented")
    }
}