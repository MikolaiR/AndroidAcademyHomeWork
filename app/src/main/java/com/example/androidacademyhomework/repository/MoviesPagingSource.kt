package com.example.androidacademyhomework.repository

import com.example.androidacademyhomework.data.model.MovieResult
import com.example.androidacademyhomework.data.network.MovieApi

internal const val THE_MOVIE_DB_STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val movieApi: MovieApi
) : BasePagingSource<Int, MovieResult>() {
    override suspend fun getPagingData(): List<MovieResult> {
        val moviesApiResponse = movieApi.getPopularMoviesWithPaging(
            page = position ?: THE_MOVIE_DB_STARTING_PAGE_INDEX
        )
        totalPages = moviesApiResponse.totalPages
        startingPage = THE_MOVIE_DB_STARTING_PAGE_INDEX
        return moviesApiResponse.results
    }
}