package com.example.moviemessager.domain.usecase

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.moviemessager.domain.interactor.SignInWithGoogleUseCase
import com.example.moviemessager.domain.repository.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInWithGoogleUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    SignInWithGoogleUseCase {
    override suspend fun invoke(
        launcher: ActivityResultLauncher<Intent>,
        signInClient: GoogleSignInClient
    ) {
        withContext(Dispatchers.IO) {
            authenticationRepository.signInWithGoogle(launcher, signInClient)
        }
    }
}