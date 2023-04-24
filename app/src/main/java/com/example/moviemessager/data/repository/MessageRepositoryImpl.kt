package com.example.moviemessager.data.repository

import android.util.Log
import android.widget.Toast
import com.example.moviemessager.R
import com.example.moviemessager.data.firebaseservise.FirebaseService
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.MessageUserFirebase
import com.example.moviemessager.domain.model.UserModelFirebase
import com.example.moviemessager.domain.repository.MessageRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MessageRepositoryImpl @Inject constructor() : MessageRepository {
    override suspend fun sendMessage(message: MessageUser) {
        val auchUser = FirebaseService.getFirebaseAuth().currentUser
        val currentUserRef = FirebaseService.getReference("${auchUser?.uid}_${message.to?.uid}")
        currentUserRef.child(currentUserRef.push().key ?: "omnonom").setValue(
            MessageUserFirebase(
                UserModelFirebase(message.to?.uid?:"Empty",message.to?.username ?: "Empty", message.to?.email ?: "Empty"),
                UserModelFirebase(auchUser?.uid?:"Empty",auchUser?.displayName ?: "Empty", auchUser?.email ?: "Empty"),
                message.message,
                message.time
            )
        )

    }

    override suspend fun getMessagesListCurrentUser(uid: String,
                                                    error: (error: String) -> Unit): List<MessageUser> {
        val messageList = mutableListOf<MessageUser>()
        val auchUser = FirebaseService.getFirebaseAuth().currentUser
        val currentUserRef = FirebaseService.getReference("${auchUser?.uid}_${uid}")
        val toUserRef = FirebaseService.getReference("$uid}_${auchUser?.uid}")

       val messageListUser1:List<MessageUser> = runBlocking { suspendCoroutine {
       //TODO
           it.resume(mutableListOf<MessageUser>())
       }}
        currentUserRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange1: $snapshot")
        //TODO messageListUser1
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "onCancelled1: $error ")
                error(error.message)
            }
        })

        val messageListUser2:List<MessageUser> = runBlocking { suspendCoroutine { it.resume(mutableListOf<MessageUser>()) }}
        toUserRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange2: $snapshot")
                //TODO messageListUser2
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled2: $error")
                error(error.message)
            }
        })

        messageList.addAll(messageListUser1)
        messageList.addAll(messageListUser2)

        return messageList.sortedBy { it.time } //TODO empty list
    }


    companion object{
        const val TAG = "MessageRepository"
    }

}
