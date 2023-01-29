package com.example.moviemessager.ui.fragment.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MessageViewModel@Inject constructor() : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Message Fragment"
    }
    val text: LiveData<String> = _text
}