package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.LoginBasicAuthUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginBasicAuthUseCaseImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    LoginBasicAuthUseCase {
    override suspend fun invoke(
        email: String,
        password: String,
        success: () -> Unit,
        error: () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            authenticationRepository.loginBasicAuth(email, password, success, error)
        }
    }
}