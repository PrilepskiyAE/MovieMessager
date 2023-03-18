package com.example.moviemessager.data.repository

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.example.moviemessager.R
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.data.firebaseservise.FirebaseService
import com.example.moviemessager.domain.model.UserModelFirebase
import com.example.moviemessager.domain.repository.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {
    override suspend fun logout() {
        FirebaseService.getFirebaseAuth().signOut()
    }
    override suspend fun signInWithGoogle(launcher: ActivityResultLauncher<Intent>, signInClient: GoogleSignInClient) {
        launcher.launch(signInClient.signInIntent)
    }
    override suspend fun firebaseAuthWithGoogle(idToken: String, success: () -> Unit,error: () -> Unit ) {
        val credation = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseService.getFirebaseAuth().signInWithCredential(credation).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("TAG", "firebaseAuthWithGoogle: ok1")
                FirebaseService.getReference("users")
                    .child(FirebaseService.getFirebaseAuth().currentUser?.uid ?: "omnomnom")
                    .setValue(
                        UserModelFirebase(
                            FirebaseService.getFirebaseAuth().currentUser?.displayName ?: "noname",
                            FirebaseService.getFirebaseAuth().currentUser?.email ?: "noEmail"
                        )
                    )
                success()
            } else {
                error()
            }
        }
    }
    override suspend fun registerBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit) {
        val auth: FirebaseAuth= FirebaseService.getFirebaseAuth()
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    FirebaseService.getReference("users").child(auth.currentUser?.uid?:"omnomnom").setValue(UserModelFirebase(auth.currentUser?.displayName?:"noname",auth.currentUser?.email?:"noEmail")?:"ololololo")
                    success()
                }
            }
            .addOnFailureListener{
                error()
            }
    }
    override suspend fun loginBasicAuth(email:String,password:String,success: () -> Unit,error: () -> Unit) {
        val auth: FirebaseAuth= FirebaseService.getFirebaseAuth()
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    FirebaseService.getReference("users").child(auth.currentUser?.uid?:"omnomnom").setValue(UserModelFirebase(auth.currentUser?.displayName?:"noname",auth.currentUser?.email?:"noEmail")?:"ololololo")
                    success()
                }
            }
            .addOnFailureListener{
                error()
            }
    }
    override suspend fun isLogin():Boolean {
        return FirebaseService.isAuth()
    }
}