package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.RegisterBasicAuthUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
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