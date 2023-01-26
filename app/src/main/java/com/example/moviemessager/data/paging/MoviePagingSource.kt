package com.example.moviemessager.data.paging

import com.example.moviemessager.data.response.MovieResponse

class MoviePagingSource( apiQueries: List<Pair<String,Any>>,) : BaseNetworkPagingSource<MovieResponse>(apiQueries){
}