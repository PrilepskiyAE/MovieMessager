package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.InitListUsersFirebaseUseCase
import com.prilepskiy.moviemessager.domain.model.UserModel
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
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