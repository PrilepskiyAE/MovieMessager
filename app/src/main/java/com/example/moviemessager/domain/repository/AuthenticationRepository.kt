package com.example.moviemessager.domain.repository

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.domain.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface AuthenticationRepository {

    suspend fun logout()
    suspend fun signInWithGoogle(launcher: ActivityResultLauncher<Intent>, signInClient: GoogleSignInClient)
    suspend fun firebaseAuthWithGoogle(idToken:String,success: () -> Unit,error: () -> Unit)
    suspend fun registerBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit)
    suspend fun loginBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit)
    suspend fun initListUsersFirebase(success: (users:List<UserModel>) -> Unit, error: (error:String) -> Unit, noUser:()->Unit)
    suspend fun isLogin():Boolean
    suspend fun getEmail():String
    suspend fun getUser():UserModel
}