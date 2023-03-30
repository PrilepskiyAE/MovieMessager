package com.example.moviemessager.ui.fragment.home


import androidx.lifecycle.viewModelScope

import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.interactor.GetEmailUseCase
import com.example.moviemessager.domain.interactor.LogoutUseCase

import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val isLogin: CheckIsLoginUseCase,
    private val getEmailUseCase: GetEmailUseCase
) : BaseViewModel() {

    private val _email: MutableStateFlow<String> = MutableStateFlow("")

    val email = _email.asStateFlow()

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

}