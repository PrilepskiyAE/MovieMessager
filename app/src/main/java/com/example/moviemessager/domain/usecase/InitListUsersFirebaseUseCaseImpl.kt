package com.example.moviemessager.domain.usecase

import com.example.moviemessager.domain.interactor.InitListUsersFirebaseUseCase
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.repository.AuthenticationRepository
import com.example.moviemessager.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InitListUsersFirebaseUseCaseImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    InitListUsersFirebaseUseCase {
    override suspend fun invoke(
        success: (users: List<UserModel>) -> Unit,
        error: (error:String) -> Unit,
        noUser: () -> Unit
    ) {
        withContext(Dispatchers.Main) {
            authenticationRepository.initListUsersFirebase(success, error, noUser)
        }
    }
}