package com.example.androidacademyhomework.UI.movielist

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.example.androidacademyhomework.createMovie
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository

class MoviesListFragmentViewModel(private val repository: MovieRepository) : ViewModel() {

    fun loadMovies(): LiveData<PagingData<Movie>> {
        val movieResult = repository.loadMoviesListWithPage().liveData.cachedIn(viewModelScope)
        return movieResult.map { pagingData ->
            pagingData.map {
                createMovie(
                    repository.loadMovieDetails(it.id), null
                )
            }
        }
    }
}

