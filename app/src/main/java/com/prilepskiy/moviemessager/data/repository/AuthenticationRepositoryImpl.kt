package com.prilepskiy.moviemessager.data.repository

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.prilepskiy.moviemessager.data.firebaseservise.FirebaseService
import com.prilepskiy.moviemessager.domain.model.UserModel
import com.prilepskiy.moviemessager.domain.model.UserModelFirebase
import com.prilepskiy.moviemessager.domain.repository.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
                            FirebaseService.getFirebaseAuth().uid?:"noUid",
                            FirebaseService.getFirebaseAuth().currentUser?.displayName ?: "noName",
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
                    FirebaseService.getReference("users").child(auth.currentUser?.uid?:"omnomnom").setValue(UserModelFirebase(auth.currentUser?.uid?:"noUid",auth.currentUser?.displayName?:"noName",auth.currentUser?.email?:"noEmail")?:"ololololo")
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
                    FirebaseService.getReference("users").child(auth.currentUser?.uid?:"omnomnom").setValue(UserModelFirebase(auth.currentUser?.uid?:"noUid",auth.currentUser?.displayName?:"noName",auth.currentUser?.email?:"noEmail")?:"ololololo")
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

    override suspend fun getEmail(): String {
       return FirebaseService.getFirebaseAuth().currentUser?.email.toString()
    }

    override suspend fun getUser(): UserModel {
        return UserModel(FirebaseService.getFirebaseAuth().currentUser?.uid?:"empty",FirebaseService.getFirebaseAuth().currentUser?.displayName?:"empty",FirebaseService.getFirebaseAuth().currentUser?.email.toString())
    }

    override suspend fun initListUsersFirebase(success: (users:List<UserModel>) -> Unit, error: (error:String) -> Unit, noUser: () -> Unit) {
        val users:MutableList<UserModel> = mutableListOf()

        if (FirebaseService.getFirebaseAuth().currentUser==null){
            noUser()
        }else{
            FirebaseService.getReference("users").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        users.add(UserModel( it.child("uid").value.toString(),it.child("username").value.toString(),it.child("email").value.toString()))
                    }
                    success(users)
                }

                override fun onCancelled(error: DatabaseError) {
                    error(error.message)
                }

            })
        }

    }
}