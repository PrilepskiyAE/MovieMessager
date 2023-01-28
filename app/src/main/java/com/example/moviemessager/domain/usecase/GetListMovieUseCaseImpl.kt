package com.example.moviemessager.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviemessager.data.paging.PAGE_SIZE
import com.example.moviemessager.domain.interactor.GetListMovieUseCase
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMovieUseCaseImpl@Inject constructor(private val moveRepository: MovieRepository): GetListMovieUseCase {
    override suspend operator fun invoke(queryOptions: List<Pair<String, Any>>): Flow<PagingData<Pair<MovieUImodel, Int>>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { moveRepository.getListMovie(queryOptions) }
        ).flow
}