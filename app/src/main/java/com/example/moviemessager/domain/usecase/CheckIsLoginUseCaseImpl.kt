package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
import com.example.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckIsLoginUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository): CheckIsLoginUseCase {
    override suspend fun invoke(): Boolean =
        withContext(Dispatchers.IO){
            authenticationRepository.isLogin()
        }

}