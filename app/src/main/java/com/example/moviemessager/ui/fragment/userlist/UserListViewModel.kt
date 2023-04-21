package com.example.moviemessager.ui.fragment.userlist

import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.CheckIsLoginUseCase
import com.example.moviemessager.domain.interactor.InitListUsersFirebaseUseCase
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel@Inject constructor(private val initListUsersFirebaseUseCase: InitListUsersFirebaseUseCase, private val isLogin: CheckIsLoginUseCase,) : BaseViewModel() {
    fun initListUsers(success: (users:List<UserModel>) -> Unit, error: (error:String) -> Unit, noUser:()->Unit){
        viewModelScope.launch{
            initListUsersFirebaseUseCase(success, error, noUser)
        }
    }
}