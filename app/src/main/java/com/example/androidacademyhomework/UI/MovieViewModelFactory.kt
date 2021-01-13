package com.example.androidacademyhomework.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidacademyhomework.UI.moviedetails.MovieDetailsFragmentViewModel
import com.example.androidacademyhomework.UI.movielist.MoviesListFragmentViewModel
import com.example.androidacademyhomework.data.network.RetrofitInstance
import com.example.androidacademyhomework.repository.MovieRepository

class MovieViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesListFragmentViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return MoviesListFragmentViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MovieDetailsFragmentViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsFragmentViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    object Injection {

        private fun provideMoviesRepository(): MovieRepository {
            return MovieRepository(RetrofitInstance().getService())
        }

        fun provideViewModelFactory(): ViewModelProvider.Factory {
            return MovieViewModelFactory(provideMoviesRepository())
        }
    }
}