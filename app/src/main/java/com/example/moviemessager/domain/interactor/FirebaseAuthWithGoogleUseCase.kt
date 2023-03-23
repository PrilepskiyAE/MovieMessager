package com.example.moviemessager.domain.interactor

interface FirebaseAuthWithGoogleUseCase {
    suspend operator fun invoke(idToken:String,success: () -> Unit,error: () -> Unit)
}