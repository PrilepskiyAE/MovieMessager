package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.UserModel

interface GetUserUseCase {
    suspend operator fun invoke(): UserModel
}