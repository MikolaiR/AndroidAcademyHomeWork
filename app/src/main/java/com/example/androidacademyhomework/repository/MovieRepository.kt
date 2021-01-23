package com.example.androidacademyhomework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidacademyhomework.data.model.*
import com.example.androidacademyhomework.data.network.MovieApi
import kotlinx.coroutines.flow.Flow

private const val NETWORK_PAGE_SIZE = 20

class MovieRepository(private val movieApi: MovieApi) {

    fun loadPopularMoviesWithPage(): Flow<PagingData<MovieResult>> =
           Pager(
               config = PagingConfig(
                   pageSize = NETWORK_PAGE_SIZE,
                   enablePlaceholders = false
               ),
               pagingSourceFactory = { MoviesPagingSource(movieApi) }
           ).flow

    fun loadSearchMoviesListWithPage(query: String): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviesPagingSource(movieApi, query) }
        ).flow

    suspend fun loadMovieDetails(movieId:Int): DetailsMovie{
        return movieApi.getMovie(movieId = movieId)
    }

    suspend fun loadActors(movieId:Int): ActorsResponse{
        return movieApi.getActors(movie_id = movieId)
    }
}