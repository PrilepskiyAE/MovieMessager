package com.example.moviemessager.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(val uid:String,val username:String,val email:String ): BaseAdapterTypes(), Parcelable

@Parcelize
data class UserModelFirebase(val uid:String,val username:String,val email:String ): Parcelable
