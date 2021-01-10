package com.example.androidacademyhomework.repository

import com.example.androidacademyhomework.BuildConfig
import com.example.androidacademyhomework.data.model.*
import com.example.androidacademyhomework.data.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {

    private val retrofitInstance = RetrofitInstance().getService()

    private suspend fun getListMovie(): List<MovieResult> =
        retrofitInstance.getPopularMovies(BuildConfig.MOVIE_DATABASE_API_KEY).results

    private suspend fun getActors(movieId: Int): List<MovieActors> =
        retrofitInstance.getActors(movie_id = movieId, BuildConfig.MOVIE_DATABASE_API_KEY).cast

    private suspend fun getDetailsMovie(movieId: Int): DetailsMovie =
        retrofitInstance.getMovie(movieId = movieId, BuildConfig.MOVIE_DATABASE_API_KEY)

    private suspend fun getGenreMovie(): List<GenresItem> =
        retrofitInstance.getGenreMovie(BuildConfig.MOVIE_DATABASE_API_KEY).genres

    suspend fun loadMovieById(movieId: Int): Movie {
        val detailMovies = getDetailsMovie(movieId)
        val genresMap = getGenreMovie().associateBy { it.id }
        return Movie(
            id = detailMovies.id,
            title = detailMovies.title,
            overview = detailMovies.overview,
            poster = detailMovies.posterPath,
            backdrop = detailMovies.backdropPath,
            ratings = detailMovies.voteAverage.toFloat(),
            numberOfRatings = detailMovies.voteCount,
            minimumAge = if (detailMovies.adult) 16 else 13,
            runtime = getDetailsMovie(detailMovies.id).runtime,
            genres = detailMovies.genres.map {
                genresMap[it.id] ?: throw IllegalArgumentException("Genre not found")
            },
            actors = getActors(detailMovies.id)
        )
    }

    suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val movies = getListMovie()
        val genresMap = getGenreMovie().associateBy { it.id }
        movies.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                poster = movie.posterPath,
                backdrop = movie.backdropPath,
                ratings = movie.voteAverage.toFloat(),
                numberOfRatings = movie.voteCount,
                minimumAge = if (movie.adult) 16 else 13,
                runtime = getDetailsMovie(movie.id).runtime,
                genres = movie.genreIds.map {
                    genresMap[it] ?: throw IllegalArgumentException("Genre not found")
                },
                actors = listOf()
            )
        }
    }
}