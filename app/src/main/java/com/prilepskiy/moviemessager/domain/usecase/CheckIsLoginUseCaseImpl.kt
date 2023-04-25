package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckIsLoginUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository): CheckIsLoginUseCase {
    override suspend fun invoke(): Boolean =
        withContext(Dispatchers.IO){
            authenticationRepository.isLogin()
        }

}