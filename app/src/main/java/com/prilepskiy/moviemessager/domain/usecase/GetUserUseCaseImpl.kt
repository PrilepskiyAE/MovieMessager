package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.GetUserUseCase
import com.prilepskiy.moviemessager.domain.model.UserModel
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCaseImpl@Inject constructor(private val authenticationRepository: AuthenticationRepository): GetUserUseCase {
    override suspend fun invoke(): UserModel {
       return withContext(Dispatchers.IO) {authenticationRepository.getUser()}
    }
}