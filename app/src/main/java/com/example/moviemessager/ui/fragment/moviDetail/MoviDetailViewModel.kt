package com.example.moviemessager.ui.fragment.moviDetail

import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.InitListCommentUseCase
import com.example.moviemessager.domain.interactor.SendCommentUseCase
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviDetailViewModel@Inject constructor(private val initListCommentUseCase: InitListCommentUseCase,private val sendCommentUseCase: SendCommentUseCase) : BaseViewModel() {
    private val _comments: MutableStateFlow<String> = MutableStateFlow("")

    val comments = _comments.asStateFlow()

    fun initListComment(movieId: String,

                        error: (error: String) -> Unit,
                        noUser: () -> Unit){
        viewModelScope.launch{
            initListCommentUseCase(movieId, {
            _comments.value=it
            }, error, noUser)
        }
    }
    fun sendComment(movieId: String,message:String){
        viewModelScope.launch{
            sendCommentUseCase(message, movieId )
        }
    }
}