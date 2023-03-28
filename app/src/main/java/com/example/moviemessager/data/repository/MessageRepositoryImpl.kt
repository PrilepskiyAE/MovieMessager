package com.example.moviemessager.data.repository

import android.util.Log
import android.widget.Toast
import com.example.moviemessager.R
import com.example.moviemessager.data.firebaseservise.FirebaseService
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.repository.MessageRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class MessageRepositoryImpl@Inject constructor(): MessageRepository {


}