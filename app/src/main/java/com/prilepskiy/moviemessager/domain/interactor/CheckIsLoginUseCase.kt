package com.prilepskiy.moviemessager.domain.interactor

interface CheckIsLoginUseCase {
    suspend operator fun invoke():Boolean
}