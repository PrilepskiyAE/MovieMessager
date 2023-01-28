package com.example.moviemessager.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.moviemessager.domain.interactor.GetListMovieUseCase
import com.example.moviemessager.domain.model.MovieModel
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel@Inject constructor(private val getListMovieUseCase: GetListMovieUseCase) : BaseViewModel() {

    private val _movieTotal: MutableStateFlow<Int> = MutableStateFlow(0)
    val movieTotal: StateFlow<Int> = _movieTotal.asStateFlow()

    private val _movieList:  MutableStateFlow<PagingData<MovieModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    var movieList = _movieList.asStateFlow()


    fun loadMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            getListMovieUseCase()
                .cachedIn(this)
                .collectLatest { pagingData ->
                   _movieList.emit(pagingData.map { it.first })
                }
        }
    }




    companion object{
        const val TAG ="HomeViewModel"
    }
}