package com.example.moviemessager.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageUser (val to:UserModel?,val from:UserModel,val message:String): BaseAdapterTypes(), Parcelable