package com.prilepskiy.moviemessager.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prilepskiy.moviemessager.core.ActionResult
import com.prilepskiy.moviemessager.core.data
import com.prilepskiy.moviemessager.core.succeeded
import com.prilepskiy.moviemessager.data.apiservise.MovieApiService
import com.prilepskiy.moviemessager.data.response.GenresResponse
import com.prilepskiy.moviemessager.data.utils.analyzeResponse
import com.prilepskiy.moviemessager.data.utils.makeApiCall
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviePagingSource(private val api: MovieApiService, private val genres: Int) :
    PagingSource<Int, MovieUImodel>() {


    suspend fun getDataFromSource(page: Int): ActionResult<List<MovieUImodel>> {


        val apiData = makeApiCall {

            analyzeResponse(
                if (genres == 0) {
                    api.getMovieList(page = page, genres = "")
                } else {
                    api.getMovieList(page = page, genres = genres.toString())
                }
            )
        }

        val movieList = mutableListOf<MovieUImodel>(
            //MovieUImodel.Title("Жанры"),MovieUImodel.Genre(GenresResponse.list_genres), MovieUImodel.Title("Фильмы")
        )
        if (page == 1) {
            movieList.add(MovieUImodel.Title("Жанры"))
            movieList.add(MovieUImodel.Genre(GenresResponse.list_genres))
            movieList.add(MovieUImodel.Title("Фильмы"))
        }

        return when (apiData) {
            is ActionResult.Success -> {

                apiData.data.results.onEach {

                    movieList.add(MovieUImodel.MovieModel.from(it))
                }
                ActionResult.Success(movieList)
            }
            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieUImodel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUImodel> {
        return try {
            withContext(Dispatchers.IO) {
                val page = params.key ?: 1
                val result = getDataFromSource(page)
                if (result.succeeded) {

                    LoadResult.Page(
                        data = result.data!!,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (result.data.isNullOrEmpty()) null else page.plus(1)
                    )
                } else {
                    LoadResult.Error((result as ActionResult.Error).errors)
                }


            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}