package com.prilepskiy.moviemessager.data.repository

import com.prilepskiy.moviemessager.data.firebaseservise.FirebaseService
import com.prilepskiy.moviemessager.domain.repository.CommentsRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class CommentsRepositoryImpl@Inject constructor(): CommentsRepository {
    override suspend fun initListComment( movieId: String,
                                          success: (String) -> Unit,
                                          error: (error: String) -> Unit,
                                          noUser: () -> Unit) {
        val message:MutableList<String> = mutableListOf()

        if (FirebaseService.getFirebaseAuth().currentUser==null){
            noUser()
        }else{
            FirebaseService.getReference("movie_$movieId").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    snapshot.children.forEach {
                        message.add(it.value.toString())
                        success(it.value.toString())
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    error(error.message)
                }

            })
        }

    }


    override suspend fun sendComment(movieId: String,message:String) {

        FirebaseService.getReference("movie_$movieId").child( FirebaseService.getReference("movie_$movieId").push().key ?: "omnonom").setValue(FirebaseService.getFirebaseAuth().currentUser?.email + " : " + message)
    }
}