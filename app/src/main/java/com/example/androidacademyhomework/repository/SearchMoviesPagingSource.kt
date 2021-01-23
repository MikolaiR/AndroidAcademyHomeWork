package com.example.androidacademyhomework.repository

import com.example.androidacademyhomework.data.model.MovieResult
import com.example.androidacademyhomework.data.network.MovieApi

class SearchMoviesPagingSource(
    private val movieApi: MovieApi,
    private val query: String
) : BasePagingSource<Int, MovieResult>() {
    override suspend fun getPagingData(): List<MovieResult> {
        val moviesApiResponse = movieApi.getSearchMoviesWithPaging(
            page = position ?: THE_MOVIE_DB_STARTING_PAGE_INDEX,
            query = query
        )
        totalPages = moviesApiResponse.totalPages
        startingPage = THE_MOVIE_DB_STARTING_PAGE_INDEX
        return moviesApiResponse.results
    }
}