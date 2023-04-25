package com.prilepskiy.moviemessager.ui.dashboard

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prilepskiy.moviemessager.domain.interactor.GetListMovieUseCase

import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import com.prilepskiy.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel@Inject constructor(private val getListMovieUseCase: GetListMovieUseCase) : BaseViewModel() {


    private val _movieList:  MutableStateFlow<PagingData<MovieUImodel>?> by lazy {
        MutableStateFlow(
            null
        )
    }

    var movieList = _movieList.asStateFlow()


    fun loadMovie(genres:Int) {
        viewModelScope.launch(Dispatchers.IO) {

            getListMovieUseCase(genres = genres)

                .cachedIn(this).onEach {  }

                .collectLatest {

                   _movieList.value=it

                }
        }
    }






    companion object{
        const val TAG ="DashboardViewModel"
    }
}