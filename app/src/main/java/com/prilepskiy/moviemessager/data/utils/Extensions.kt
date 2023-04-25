package com.prilepskiy.moviemessager.data.utils


import com.prilepskiy.moviemessager.core.ActionResult
import com.prilepskiy.moviemessager.core.CallException

import retrofit2.Response

suspend fun <R> makeApiCall(call: suspend () -> ActionResult<R>) = try {
    call()
} catch (e: Exception) {
    ActionResult.Error(e)
}

fun <Model> analyzeResponse(
    response: Response<Model>
): ActionResult<Model> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            responseBody?.let {
                ActionResult.Success(it)
            } ?: ActionResult.Error(CallException(response.code(), response.message()))
        }
        else -> {
            ActionResult.Error(CallException(response.code(), response.message()))
        }
    }
}