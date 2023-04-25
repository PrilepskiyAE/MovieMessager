package com.prilepskiy.moviemessager.domain.interactor

interface RegisterBasicAuthUseCase {
    suspend operator fun invoke(email:String,password:String,success: () -> Unit,error: () -> Unit)
}