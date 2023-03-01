package com.example.moviemessager.domain.usecase


import androidx.paging.PagingData
import com.example.moviemessager.domain.interactor.GetListMovieUseCase
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMovieUseCaseImpl@Inject constructor(private val moveRepository: MovieRepository): GetListMovieUseCase {
    override operator fun invoke(): Flow<PagingData<MovieUImodel>> =
         moveRepository.getListMovie()
}