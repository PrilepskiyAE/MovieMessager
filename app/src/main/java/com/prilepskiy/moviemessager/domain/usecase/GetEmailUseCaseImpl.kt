package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.GetEmailUseCase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEmailUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository):
    GetEmailUseCase {
    override suspend fun invoke(): String = withContext(Dispatchers.IO) { authenticationRepository.getEmail()}

}