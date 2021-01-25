package com.example.androidacademyhomework.repository

import androidx.paging.*
import com.example.androidacademyhomework.data.model.*
import com.example.androidacademyhomework.data.network.MovieApi
import com.example.androidacademyhomework.convertToMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val NETWORK_PAGE_SIZE = 20

class MovieRepository(private val movieApi: MovieApi) {

    private fun loadPopularMoviesWithPage(): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(movieApi) }
        ).flow

    private fun loadSearchMoviesListWithPage(query: String): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviesPagingSource(movieApi, query) }
        ).flow

    private suspend fun loadMovieDetails(movieId: Int): DetailsMovie {
        return movieApi.getMovie(movieId = movieId)
    }

    private suspend fun loadActors(movieId: Int): ActorsResponse {
        return movieApi.getActors(movie_id = movieId)
    }

   suspend fun loadPopularMovies(): Flow<PagingData<Movie>> {
        val resultFlowPagingData = loadPopularMoviesWithPage()
        return resultFlowPagingData.map { pagingData ->
            pagingData.map { movieResult ->
                convertToMovie(
                    loadMovieDetails(movieResult.id),
                    null
                )
            }
        }
    }

   suspend fun loadSearchMovies(queryString: String): Flow<PagingData<Movie>> {
        val resultFlowPagingData = loadSearchMoviesListWithPage(queryString)
        return resultFlowPagingData.map { pagingData ->
            pagingData.map { movieResult ->
                convertToMovie(
                    loadMovieDetails(movieResult.id),
                    null
                )
            }
        }
    }

    suspend fun loadDetailsMovie(movieId: Int): Movie {
        return convertToMovie(
            loadMovieDetails(movieId),
            loadActors(movieId)
        )
    }
}