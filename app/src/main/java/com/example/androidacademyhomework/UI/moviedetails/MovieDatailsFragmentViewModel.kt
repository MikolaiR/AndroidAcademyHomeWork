package com.example.androidacademyhomework.UI.moviedetails

import androidx.lifecycle.*
import com.example.androidacademyhomework.createMovie
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsFragmentViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieDetailsLiveData = MutableLiveData<Movie>()
    val movieDetailsLiveData: LiveData<Movie>
    get() = _movieDetailsLiveData

    fun loadDetailsMovie(movieId:Int) {
        viewModelScope.launch {
            val movieDetails = repository.loadMovieDetails(movieId)
            val actorsResponse = repository.loadActors(movieId)
            _movieDetailsLiveData.value = createMovie(movieDetails,actorsResponse)
        }
    }
}