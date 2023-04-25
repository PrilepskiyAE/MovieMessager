package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.LogoutUseCase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    LogoutUseCase {
    override suspend fun invoke() {
        withContext(Dispatchers.IO) {
            authenticationRepository.logout()
        }
    }
}