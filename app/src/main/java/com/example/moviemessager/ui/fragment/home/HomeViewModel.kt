package com.example.moviemessager.ui.fragment.home


import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.moviemessager.domain.interactor.*

import com.example.moviemessager.domain.model.MovieUImodel

import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val isLogin: CheckIsLoginUseCase,
    private val getEmailUseCase: GetEmailUseCase,
    private val getListMovieUseCase: GetListMovieUseCase,
    private val getListFavoriteMovieUseCase: GetListFavoriteMovieUseCase
) : BaseViewModel() {

    private val _email: MutableStateFlow<String> = MutableStateFlow("")

    val email = _email.asStateFlow()


    private val _listFavoriteMovie: MutableStateFlow<List<MovieUImodel.MovieModel>?> by lazy {
        MutableStateFlow(null)
    }
    val listFavoriteMovie = _listFavoriteMovie.asStateFlow()
    private val _movieList:  MutableStateFlow<PagingData<MovieUImodel>?> by lazy {
        MutableStateFlow(
            null
        )
    }

    var movieList = _movieList.asStateFlow()
    fun getEmail() {
        viewModelScope.launch {
            _email.emit(getEmailUseCase())
        }
    }

    fun logout(action: () -> Unit) {
        viewModelScope.launch {
            logoutUseCase()
            isAuth(action)
        }
    }

    fun isAuth(action: () -> Unit) {
        viewModelScope.launch {
            if (isLogin()) {
                action()
            }
        }

    }

    fun getFavorite() {
        viewModelScope.launch {
            getListFavoriteMovieUseCase().collect {
                _listFavoriteMovie.emit(it)
            }

        }
    }

    fun loadMovie(genres: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getListMovieUseCase(genres = genres)

                .cachedIn(this)

                .collect { pagingData ->
                    val topMovie = pagingData.filter {
                        return@filter when (it) {
                            is MovieUImodel.MovieModel -> {
                                it.popularity > 5000
                            }
                            else -> {
                                false
                            }
                        }
                    }

                    _movieList.value=topMovie
                }
        }
    }
}