package com.prilepskiy.moviemessager.ui.fragment.home


import androidx.lifecycle.viewModelScope
import com.prilepskiy.moviemessager.domain.interactor.*

import com.prilepskiy.moviemessager.domain.model.MovieUImodel

import com.prilepskiy.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

}