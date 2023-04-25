package com.prilepskiy.moviemessager.domain.usecase

import com.prilepskiy.moviemessager.domain.interactor.FirebaseAuthWithGoogleUseCase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthWithGoogleUseCaseImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    FirebaseAuthWithGoogleUseCase {
    override suspend fun invoke(idToken: String, success: () -> Unit, error: () -> Unit) {
        withContext(Dispatchers.IO) {
            authenticationRepository.firebaseAuthWithGoogle(idToken, success, error)
        }
    }
}