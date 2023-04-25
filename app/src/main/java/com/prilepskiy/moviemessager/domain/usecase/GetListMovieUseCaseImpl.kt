package com.prilepskiy.moviemessager.domain.usecase


import androidx.paging.PagingData
import com.prilepskiy.moviemessager.domain.interactor.GetListMovieUseCase
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import com.prilepskiy.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMovieUseCaseImpl@Inject constructor(private val moveRepository: MovieRepository): GetListMovieUseCase {
    override operator fun invoke(genres:Int): Flow<PagingData<MovieUImodel>> =
         moveRepository.getListMovie(genres)
}