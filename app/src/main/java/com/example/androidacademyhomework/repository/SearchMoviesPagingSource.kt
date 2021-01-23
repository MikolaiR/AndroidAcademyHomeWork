package com.example.androidacademyhomework.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.androidacademyhomework.data.model.MovieResult
import com.example.androidacademyhomework.data.network.MovieApi
import retrofit2.HttpException
import java.io.IOException

class SearchMoviesPagingSource(
    private val movieApi: MovieApi,
    private val query: String
) :
    PagingSource<Long, MovieResult>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MovieResult> {
        val position = params.key ?: THE_MOVIE_DB_STARTING_PAGE_INDEX
        Log.d("PagingSource", "load: $query ")
        return try {
            val moviesList = movieApi.getSearchMoviesWithPaging(
                query = query,
                page = position
            ).results
            val nextKey = if (moviesList.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = moviesList,
                prevKey = if (position == THE_MOVIE_DB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}