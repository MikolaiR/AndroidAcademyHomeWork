package com.example.androidacademyhomework.UI.movielist

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidacademyhomework.createMovie
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesListFragmentViewModel(private val repository: MovieRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun popularMovies(): Flow<PagingData<Movie>> {
        return repository.loadPopularMoviesWithPage()
                .map { pagingData -> pagingData.map { createMovie(repository.loadMovieDetails(it.id),null) }}
                .cachedIn(viewModelScope)
    }

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Movie>> =
            repository.loadSearchMoviesListWithPage(queryString)
                .map { pagingData -> pagingData.map { createMovie(repository.loadMovieDetails(it.id),null) }}
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}


