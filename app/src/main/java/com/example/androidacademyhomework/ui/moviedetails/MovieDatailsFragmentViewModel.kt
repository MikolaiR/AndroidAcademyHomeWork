package com.example.androidacademyhomework.ui.moviedetails

import androidx.lifecycle.*
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.formatToMovie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsFragmentViewModel(private val repository: MovieRepository) : ViewModel() {

    fun loadDetailsMovie(movieId: Int): Flow<Movie> {
        return flow {
            emit(
                formatToMovie(
                    repository.loadMovieDetails(movieId),
                    repository.loadActors(movieId)
                )
            )
        }
    }
}