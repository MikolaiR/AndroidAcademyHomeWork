package com.example.androidacademyhomework.UI.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidacademyhomework.data.Movie

class MovieDetailsFragmentViewModel(
    val movie: Movie
) : ViewModel() {

    val movieLiveData: LiveData<Movie> = liveData {
        emit(movie)
    }
}