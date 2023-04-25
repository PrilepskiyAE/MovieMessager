package com.prilepskiy.moviemessager.ui.fragment.login

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.viewModelScope
import com.prilepskiy.moviemessager.domain.interactor.FirebaseAuthWithGoogleUseCase
import com.prilepskiy.moviemessager.domain.interactor.LoginBasicAuthUseCase
import com.prilepskiy.moviemessager.domain.interactor.RegisterBasicAuthUseCase
import com.prilepskiy.moviemessager.domain.interactor.SignInWithGoogleUseCase
import com.prilepskiy.moviemessager.ui.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthWithGoogleUseCase: FirebaseAuthWithGoogleUseCase,
    private val loginBasicAuthUseCase: LoginBasicAuthUseCase,
    private val registerBasicAuthUseCase: RegisterBasicAuthUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
    ): BaseViewModel(){
    fun firebaseAuthWithGoogle(idToken:String,success: () -> Unit,error: () -> Unit) {
     viewModelScope.launch {
         firebaseAuthWithGoogleUseCase(idToken, success, error)
     }
 }
    fun loginBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit){
      viewModelScope.launch {
          loginBasicAuthUseCase(email, password, success, error)
      }
  }
    fun registerBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit){
        viewModelScope.launch {
        registerBasicAuthUseCase(email, password, success, error)
    }

    }
    fun signInWithGoogle(launcher: ActivityResultLauncher<Intent>, signInClient: GoogleSignInClient){
       viewModelScope.launch {
           signInWithGoogleUseCase(launcher, signInClient)
       }
   }
}