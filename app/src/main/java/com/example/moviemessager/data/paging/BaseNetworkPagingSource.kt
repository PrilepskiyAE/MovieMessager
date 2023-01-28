package com.example.moviemessager.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviemessager.core.ActionResult
import com.example.moviemessager.core.DiffUtilModel
import com.example.moviemessager.core.data
import com.example.moviemessager.core.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val STARTING_PAGE = 1
const val PAGE_SIZE = 20
abstract class BaseNetworkPagingSource<Item : DiffUtilModel<*>> (private val apiQueries: List<Pair<String, Any>>?): PagingSource<Int, Pair<Item, Int>>(){

    private var lastPage = 0

    override fun getRefreshKey(state: PagingState<Int, Pair<Item, Int>>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Item, Int>> {
        val position = params.key ?: STARTING_PAGE
        val queryMap = mutableMapOf<String, Any>()
        val queriesList = mutableListOf<Pair<String, Any>>()
        queriesList.add(Pair("page", position))
        if (apiQueries != null)
            queriesList.addAll(apiQueries)
        return try {
            withContext(Dispatchers.IO) {

                val result = getDataFromSource(
                    queryMap.addQueryPair(
                        queriesList
                    )
                )

                if (result.succeeded) {
                    val nextKey = if (result.data!!.first.isEmpty()) {
                        null
                    } else {
                        position + (params.loadSize / PAGE_SIZE)
                    }

                    val prevKey = if (position == STARTING_PAGE) null else position - 1

                    LoadResult.Page(
                        data = result.data!!.first.map { Pair(it, result.data!!.second) },
                        prevKey = prevKey,
                        nextKey = nextKey
                    )

                } else {
                    LoadResult.Error((result as ActionResult.Error).errors)
                }
            }

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
    abstract suspend fun getDataFromSource(queryMap: Map<String, Any>): ActionResult<Pair<List<Item>, Int>>
    fun MutableMap<String,Any>.addQueryPair(queryParams: List<Pair<String,Any>>): Map<String,Any> {

        queryParams.onEach {
            this[it.first] = it.second
        }

        return this
    }
}