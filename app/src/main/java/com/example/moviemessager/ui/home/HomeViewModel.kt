package com.example.moviemessager.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.domain.repository.MovieRepository
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val moveRepository: MovieRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun movieTest(){
        viewModelScope.launch {
            when (val res = moveRepository.getListMovie() ) {
                is ActionResult.Success -> {

                    Log.d(TAG, "movieTest:${res.data} ")
                    Log.d(TAG, "movieTest:${res.data.results.size} ")

                }
                is ActionResult.Error -> {
                    Log.d(TAG, "movieTest:${res.errors} ")
                }
            }
        }

    }
    companion object{
        const val TAG ="HomeViewModel"
    }
}