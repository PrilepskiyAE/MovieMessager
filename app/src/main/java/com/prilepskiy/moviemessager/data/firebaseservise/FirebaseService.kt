package com.prilepskiy.moviemessager.data.firebaseservise

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseService {
    private val database: FirebaseDatabase = Firebase.database
    private val auth: FirebaseAuth=Firebase.auth

    fun getReference(path:String): DatabaseReference{
        return database.getReference(path)
    }
    fun isAuth():Boolean = auth.currentUser == null

    fun getFirebaseAuth():FirebaseAuth= auth
}