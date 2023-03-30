package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.GetEmailUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEmailUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository):
    GetEmailUseCase {
    override suspend fun invoke(): String = withContext(Dispatchers.IO) { authenticationRepository.getEmail()}

}