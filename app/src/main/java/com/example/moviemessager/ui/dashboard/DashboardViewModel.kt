package com.example.moviemessager.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _movieList: MutableStateFlow<List<MovieModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    var movieList = _movieList.asStateFlow()
    data class ListsViewState(
        val movieList: PagingData<MovieModel>? = null,
        val isLoading: Boolean = true
    )
    private val _state = MutableStateFlow(ListsViewState())
    val state: StateFlow<ListsViewState> = _state.asStateFlow()
    private fun trigerLoading(value: ListsViewState) {
        _state.value = value
    }
    fun getMovie() {
        val queryOptions = mutableListOf<Pair<String, Any>>()
        trigerLoading(
            _state.value.copy(
                isLoading = true
            )
        )
        val movieListLocal = mutableListOf<MovieModel>()
        CoroutineScope(Dispatchers.IO).launch{
            _movieList.emit(null)
            _movieTotal.emit(0)
            getListMovieUseCase.invoke(queryOptions).mapLatest {
                it.map {
                    movieListLocal.add(it.first)
                    _movieList.emit(movieListLocal)
                    _movieTotal.emit(it.second)
                    it.first
                }
            }.cachedIn(this).collectLatest {
                _state.value.copy(
                    isLoading = false,
                    movieList = it
                )
            }
        }
    }
    companion object{
        const val TAG ="HomeViewModel"
    }
}