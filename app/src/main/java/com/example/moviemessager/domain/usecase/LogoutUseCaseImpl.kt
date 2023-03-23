package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.LogoutUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
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