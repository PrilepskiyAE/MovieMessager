package com.example.moviemessager.core

data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
) : Exception()