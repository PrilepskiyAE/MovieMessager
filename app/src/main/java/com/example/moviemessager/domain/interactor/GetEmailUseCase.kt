package com.example.moviemessager.domain.interactor

import androidx.paging.PagingData
import com.example.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.flow.Flow

interface GetEmailUseCase {
    suspend operator fun invoke(): String
}