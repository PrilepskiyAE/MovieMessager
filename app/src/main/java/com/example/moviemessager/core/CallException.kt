package com.example.moviemessager.core

import com.google.android.gms.tasks.Continuation
import kotlinx.coroutines.CancellableContinuation

data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
) : Exception()
