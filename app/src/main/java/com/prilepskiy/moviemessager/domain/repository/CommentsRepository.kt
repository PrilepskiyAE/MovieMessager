package com.prilepskiy.moviemessager.domain.repository

interface CommentsRepository {
    suspend fun initListComment( movieId: String,
                                 success: (String) -> Unit,
                                 error: (error: String) -> Unit,
                                 noUser: () -> Unit)
    suspend fun sendComment(movieId: String,message:String)
}