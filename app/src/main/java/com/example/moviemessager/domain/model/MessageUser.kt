package com.example.moviemessager.domain.model

data class MessageUser (val to:UserModel?,val from:UserModel,val message:String)