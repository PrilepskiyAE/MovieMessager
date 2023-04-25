package com.prilepskiy.moviemessager.data.response

data class MovieListResponse (
    var page:Int = 0,
    var results: List<MovieResponse>,
    var total_pages:Int = 0,
    var total_results:Int = 0
)