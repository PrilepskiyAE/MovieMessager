package com.example.moviemessager.ui.fragment.moviDetail

import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.InitListCommentUseCase
import com.example.moviemessager.domain.interactor.SendCommentUseCase
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviDetailViewModel@Inject constructor(private val initListCommentUseCase: InitListCommentUseCase,private val sendCommentUseCase: SendCommentUseCase) : BaseViewModel() {
    fun initListComment(movieId: String,
                        success: (comment: List<String>) -> Unit,
                        error: (error: String) -> Unit,
                        noUser: () -> Unit){
        viewModelScope.launch{
            initListCommentUseCase(movieId, success, error, noUser)
        }
    }
    fun sendComment(movieId: String,message:String){
        viewModelScope.launch{
            sendCommentUseCase(message, movieId )
        }
    }
}