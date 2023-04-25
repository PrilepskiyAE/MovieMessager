package com.prilepskiy.moviemessager.domain.interactor

import com.prilepskiy.moviemessager.domain.model.UserModel

interface InitListUsersFirebaseUseCase {
    suspend operator fun invoke(success: (users:List<UserModel>) -> Unit, error: (error:String) -> Unit, noUser:()->Unit)
}