package com.example.moviemessager.domain.interactor

interface LoginBasicAuthUseCase {
    suspend operator fun invoke(email:String,password:String,success: () -> Unit,error: () -> Unit)
}