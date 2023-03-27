package com.example.moviemessager.ui.fragment.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MessageViewModel@Inject constructor(private val isLogin: CheckIsLoginUseCase): BaseViewModel() {
    fun isAuth(action: () -> Unit) {
        viewModelScope.launch{
            if (isLogin()){
                action()
            }
        }

    }
}