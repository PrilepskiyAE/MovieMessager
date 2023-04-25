package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.UserModel

interface GetUserUseCase {
    suspend operator fun invoke(): UserModel
}