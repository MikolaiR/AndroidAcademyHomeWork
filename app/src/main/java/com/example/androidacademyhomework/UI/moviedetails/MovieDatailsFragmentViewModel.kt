package com.example.androidacademyhomework.UI.moviedetails

import androidx.lifecycle.*
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsFragmentViewModel: ViewModel() {

    private val repository = MovieRepository()
    private val _movieLiveData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = _movieLiveData

     fun updateDate(movieId:Int){
         viewModelScope.launch {
            _movieLiveData.value = repository.loadMovieById(movieId)
        }
    }
}