package com.example.moviemessager.data.repository

import android.util.Log
import com.example.moviemessager.data.firebaseservise.FirebaseService
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.MessageUserFirebase
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.model.UserModelFirebase
import com.example.moviemessager.domain.repository.MessageRepository
import com.example.moviemessager.ui.base.safeResume
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MessageRepositoryImpl @Inject constructor() : MessageRepository {
    override suspend fun sendMessage(message: MessageUser) {
        val auchUser = FirebaseService.getFirebaseAuth().currentUser
        val currentUserRef = FirebaseService.getReference("message")
        currentUserRef.child("${auchUser?.uid}_${message.to?.uid}").child(currentUserRef.push().key ?: "omnonom").setValue(
            MessageUserFirebase(
                UserModelFirebase(message.to?.uid?:"Empty",message.to?.username ?: "Empty", message.to?.email ?: "Empty"),
                UserModelFirebase(auchUser?.uid?:"Empty",auchUser?.displayName ?: "Empty", auchUser?.email ?: "Empty"),
                message.message,
                message.time
            )
        )

    }

    override suspend fun getMessagesListCurrentUser(
        uid: String,
        success:(list:List<MessageUser>)->Unit,
        error: (error: String) -> Unit
    ) {
        val auchUser = FirebaseService.getFirebaseAuth().currentUser
        val patch = FirebaseService.getReference("message")
        patch.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val resultListUser:MutableList<MessageUser> = mutableListOf()
                Log.d(TAG, "onDataChange: $snapshot")
                snapshot.child("${auchUser?.uid}_${uid}").children.forEach {
                    resultListUser.add(
                            MessageUser(
                                to=UserModel(
                                    uid=it.child("to").child("uid").value.toString(),
                                    email = it.child("to").child("email").value.toString(),
                                    username = it.child("to").child("username").value.toString()
                                ),
                                from=UserModel(
                                    uid=it.child("from").child("uid").value.toString(),
                                    email = it.child("from").child("email").value.toString(),
                                    username = it.child("from").child("username").value.toString()
                                ),
                                message = it.child("message").value.toString(),
                                time = it.child("time").value.toString().toLong()
                        )
                        )
                }
                snapshot.child("${uid}_${auchUser?.uid}").children.forEach{
                    resultListUser.add(
                        MessageUser(
                            to=UserModel(
                                uid=it.child("to").child("uid").value.toString(),
                                email = it.child("to").child("email").value.toString(),
                                username = it.child("to").child("username").value.toString()
                            ),
                            from=UserModel(
                                uid=it.child("from").child("uid").value.toString(),
                                email = it.child("from").child("email").value.toString(),
                                username = it.child("from").child("username").value.toString()
                            ),
                            message = it.child("message").value.toString(),
                            time = it.child("time").value.toString().toLong()
                        )
                    )
                }
                success(resultListUser.sortedBy { it.time })
            }

            override fun onCancelled(error: DatabaseError) {
                error(error.message)
            }
        })
    }

//    override suspend fun getMessagesListCurrentUser(uid: String, error: (error: String) -> Unit): List<MessageUser> {
//        val messageList = mutableListOf<MessageUser>()
//        val auchUser = FirebaseService.getFirebaseAuth().currentUser
//        val currentUserRef = FirebaseService.getReference("${auchUser?.uid}_${uid}")
//        val toUserRef = FirebaseService.getReference("${uid}_${auchUser?.uid}")
//
//       val messageListUser1:List<MessageUser> = runBlocking { suspendCoroutine { list->
//
//           currentUserRef.addValueEventListener(object :ValueEventListener{
//               override fun onDataChange(snapshot: DataSnapshot) {
//                   val resultListUser:MutableList<MessageUser> = mutableListOf()
//                   snapshot.children.forEach {
//
//                        resultListUser.add(
//                            MessageUser(
//                                to=UserModel(
//                                    uid=it.child("to").child("uid").value.toString(),
//                                    email = it.child("to").child("email").value.toString(),
//                                    username = it.child("to").child("username").value.toString()
//                                ),
//                                from=UserModel(
//                                    uid=it.child("from").child("uid").value.toString(),
//                                    email = it.child("from").child("email").value.toString(),
//                                    username = it.child("from").child("username").value.toString()
//                                ),
//                                message = it.child("message").value.toString(),
//                                time = it.child("time").value.toString().toLong()
//                        )
//                        )
//                    }
//                   try {
//                       list.resume(resultListUser)
//                   }catch (e:java.lang.IllegalStateException)
//                   {
//                       Log.d(TAG, "onDataChange: ")
//
//                   }
//
//
//               }
//
//               override fun onCancelled(error: DatabaseError) {
//                   Log.d("TAG", "onCancelled1: $error ")
//                   error(error.message)
//               }
//           })
//
//
//       }}
//
//
//        val messageListUser2:List<MessageUser> = runBlocking { suspendCoroutine {list->
//            toUserRef.addValueEventListener(object :ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val resultListUser:MutableList<MessageUser> = mutableListOf()
//                    snapshot.children.forEach {
//
//                        resultListUser.add(
//                            MessageUser(
//                                to=UserModel(
//                                    uid=it.child("to").child("uid").value.toString(),
//                                    email = it.child("to").child("email").value.toString(),
//                                    username = it.child("to").child("username").value.toString()
//                                ),
//                                from=UserModel(
//                                    uid=it.child("from").child("uid").value.toString(),
//                                    email = it.child("from").child("email").value.toString(),
//                                    username = it.child("from").child("username").value.toString()
//                                ),
//                                message = it.child("message").value.toString(),
//                                time = it.child("time").value.toString().toLong()
//                            )
//                        )
//                    }
//
//                         try {
//                        list.resume(resultListUser)
//                    }catch (e:java.lang.IllegalStateException)
//                    {
//                        Log.d(TAG, "onDataChange: ")
//
//                    }
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.d(TAG, "onCancelled2: $error")
//                    error(error.message)
//                }
//            })
//
//        }}
//
//
//        messageList.addAll(messageListUser1)
//        messageList.addAll(messageListUser2)
//
//        return messageList.sortedBy { it.time } //TODO empty list
//    }


    companion object{
        const val TAG = "MessageRepository"
    }

}
