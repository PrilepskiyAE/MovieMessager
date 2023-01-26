package com.example.moviemessager.data.apiservise


import com.example.moviemessager.data.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/3/discover/movie")
    suspend fun getMovieList(@Query("api_key") api: String = "e74f8b81dfb31627c4e1f8eb055e8682"): Response<MovieListResponse>



}
