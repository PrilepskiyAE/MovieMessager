package com.example.moviemessager.ui.fragment.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.moviemessager.core.data
import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.interactor.GetListMovieUseCase

import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val isLogin: CheckIsLoginUseCase):BaseViewModel()
{
    fun isAuth(action: () -> Unit) {
        viewModelScope.launch{
            if (isLogin()){
                action()
            }
        }

    }
}