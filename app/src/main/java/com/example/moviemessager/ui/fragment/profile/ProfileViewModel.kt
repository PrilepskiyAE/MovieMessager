package com.example.moviemessager.ui.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviemessager.R
import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.interactor.LogoutUseCase
import com.example.moviemessager.ui.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel class ProfileViewModel@Inject constructor(private val logoutUseCase: LogoutUseCase,private val isLogin: CheckIsLoginUseCase) : BaseViewModel() {

    fun logout(action: () -> Unit){viewModelScope.launch {
        logoutUseCase()
        isAuth(action)
    }
    }
     fun isAuth(action: () -> Unit) {
        viewModelScope.launch{
            if (isLogin()){
                action()
            }
        }

    }

}