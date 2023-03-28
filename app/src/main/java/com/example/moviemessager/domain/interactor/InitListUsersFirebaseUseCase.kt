package com.example.moviemessager.domain.interactor

import com.example.moviemessager.domain.model.UserModel

interface InitListUsersFirebaseUseCase {
    suspend operator fun invoke(success: (users:List<UserModel>) -> Unit, error: (error:String) -> Unit, noUser:()->Unit)
}