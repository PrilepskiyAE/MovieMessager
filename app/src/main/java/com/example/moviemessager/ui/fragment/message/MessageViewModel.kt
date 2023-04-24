package com.example.moviemessager.ui.fragment.message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.interactor.GetUserUseCase
import com.example.moviemessager.domain.interactor.InitListMessageUseCase
import com.example.moviemessager.domain.interactor.SendMessageUseCase
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MessageViewModel@Inject constructor(
    private val isLogin: CheckIsLoginUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getListMessageUseCase: InitListMessageUseCase
    ): BaseViewModel() {


    private val _listMessage: MutableStateFlow<List<MessageUser>?> by lazy {
        MutableStateFlow(null)
    }
    val listMessage = _listMessage.asStateFlow()


    fun isAuth(action: () -> Unit) {
        viewModelScope.launch{
            if (isLogin()){
                action()
            }
        }

    }

    fun sendMessage( to: UserModel?,   message:String){
        viewModelScope.launch {
            val dt:Long=System.currentTimeMillis()
            Log.d("TAG_DATA", "sendMessage: $dt")
            val from=getUserUseCase()

            sendMessageUseCase.invoke(MessageUser(to,from,message,dt))
        }
    }

    fun GetListMessage( uid: String,
                        error: (error: String) -> Unit){
        viewModelScope.launch {
            _listMessage.emit(null)
            _listMessage.emit(getListMessageUseCase(uid, error))

        }
    }
}