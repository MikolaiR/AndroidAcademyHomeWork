package com.example.androidacademyhomework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.androidacademyhomework.BuildConfig
import com.example.androidacademyhomework.data.model.*
import com.example.androidacademyhomework.data.network.MovieApi

private const val NETWORK_PAGE_SIZE = 20

class MovieRepository(private val movieApi: MovieApi) {

    fun loadMoviesListWithPage(): Pager<Long,MovieResult> =
           Pager(
               config = PagingConfig(
                   pageSize = NETWORK_PAGE_SIZE,
                   enablePlaceholders = false
               ),
               pagingSourceFactory = { MoviesPagingSource(movieApi) }
           )

    suspend fun loadMovieDetails(movieId:Int): DetailsMovie{
        return movieApi.getMovie(movieId = movieId, apiKey = BuildConfig.MOVIE_DATABASE_API_KEY)
    }

    suspend fun loadActors(movieId:Int): ActorsResponse{
        return movieApi.getActors(movie_id = movieId, apiKey = BuildConfig.MOVIE_DATABASE_API_KEY)
    }
}