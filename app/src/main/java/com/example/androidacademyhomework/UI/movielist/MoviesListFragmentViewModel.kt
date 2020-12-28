package com.example.androidacademyhomework.UI.movielist

import android.app.Application
import androidx.lifecycle.*
import com.example.androidacademyhomework.data.Movie
import com.example.androidacademyhomework.repository.MovieRepository

class MoviesListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepository(application)
    val listMoviesLiveData: LiveData<List<Movie>> = liveData {
        emit(repository.loadMovies())
    }

}