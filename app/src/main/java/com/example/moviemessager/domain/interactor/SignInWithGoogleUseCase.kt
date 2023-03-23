package com.example.moviemessager.domain.interactor

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface SignInWithGoogleUseCase {
    suspend operator fun invoke(launcher: ActivityResultLauncher<Intent>, signInClient: GoogleSignInClient)
}