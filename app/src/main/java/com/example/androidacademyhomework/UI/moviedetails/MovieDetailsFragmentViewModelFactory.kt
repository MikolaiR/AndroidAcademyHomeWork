package com.example.androidacademyhomework.UI.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidacademyhomework.data.Movie

class MovieDetailsFragmentViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsFragmentViewModel::class.java -> MovieDetailsFragmentViewModel(movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}