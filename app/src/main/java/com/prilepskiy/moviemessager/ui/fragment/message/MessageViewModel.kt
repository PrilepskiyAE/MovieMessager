package com.prilepskiy.moviemessager.ui.fragment.message

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.prilepskiy.moviemessager.domain.interactor.GetUserUseCase
import com.prilepskiy.moviemessager.domain.interactor.InitListMessageUseCase
import com.prilepskiy.moviemessager.domain.interactor.SendMessageUseCase
import com.prilepskiy.moviemessager.domain.model.MessageUser
import com.prilepskiy.moviemessager.domain.model.UserModel
import com.prilepskiy.moviemessager.ui.base.BaseViewModel
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

    fun GetListMessage( uid:String,success:(list:List<MessageUser>)->Unit, error: (error: String) -> Unit){
        viewModelScope.launch {
            getListMessageUseCase(uid,success, error)
        }
    }
}