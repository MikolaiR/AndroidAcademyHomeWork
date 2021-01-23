package com.example.androidacademyhomework.data.network

import com.example.androidacademyhomework.data.model.ActorsResponse
import com.example.androidacademyhomework.data.model.DetailsMovie
import com.example.androidacademyhomework.data.model.MoviesApiResponse
import com.example.androidacademyhomework.data.model.MoviesSearchResponse
import retrofit2.http.*

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMoviesWithPaging(
        @Query("page") page: Long
    ): MoviesApiResponse

    @GET("search/movie")
    suspend fun getSearchMoviesWithPaging(
        @Query("page") page: Long,
        @Query("query") query: String
    ): MoviesApiResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movie_id: Int
    ): ActorsResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ): DetailsMovie
}