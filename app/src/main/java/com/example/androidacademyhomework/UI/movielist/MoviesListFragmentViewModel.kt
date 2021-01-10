package com.example.androidacademyhomework.UI.movielist

import androidx.lifecycle.*
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesListFragmentViewModel : ViewModel() {

    private val repository = MovieRepository()
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = _moviesLiveData

    fun getMovies(){
        viewModelScope.launch {
               _moviesLiveData.value = repository.loadMovies()
        }
    }
}