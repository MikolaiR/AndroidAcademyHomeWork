package com.example.androidacademyhomework.repository

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
        return try {
            val movieResponse = movieApi.getSearchMoviesWithPaging(
                query = query,
                page = position
            )
            val moviesList = movieResponse.results
            val nextKey = if (movieResponse.page >=  movieResponse.totalPages) {
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