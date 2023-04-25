package com.prilepskiy.moviemessager.domain.interactor

interface GetEmailUseCase {
    suspend operator fun invoke(): String
}