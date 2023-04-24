package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.GetUserUseCase
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository): GetUserUseCase {
    override suspend fun invoke(): UserModel {
       return withContext(Dispatchers.IO) {authenticationRepository.getUser()}
    }
}