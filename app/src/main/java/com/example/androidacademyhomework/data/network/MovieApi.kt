package com.example.androidacademyhomework.data.network

import com.example.androidacademyhomework.data.model.ActorsResponse
import com.example.androidacademyhomework.data.model.DetailsMovie
import com.example.androidacademyhomework.data.model.GenresResponse
import com.example.androidacademyhomework.data.model.MoviesApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MoviesApiResponse

    @GET("genre/movie/list")
    suspend fun getGenreMovie(@Query("api_key") apiKey: String): GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): ActorsResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): DetailsMovie
}