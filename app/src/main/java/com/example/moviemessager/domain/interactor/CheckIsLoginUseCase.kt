package com.example.moviemessager.domain.interactor

interface CheckIsLoginUseCase {
    suspend operator fun invoke():Boolean
}