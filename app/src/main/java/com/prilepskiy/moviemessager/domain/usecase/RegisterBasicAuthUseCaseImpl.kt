package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.RegisterBasicAuthUseCase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterBasicAuthUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    RegisterBasicAuthUseCase {
    override suspend fun invoke(
        email: String,
        password: String,
        success: () -> Unit,
        error: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            authenticationRepository.registerBasicAuth(email, password, success, error)
        }
    }
}