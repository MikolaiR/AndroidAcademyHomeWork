package com.example.androidacademyhomework.ui.movielist

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidacademyhomework.createMovie
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.flow.*

class MoviesListFragmentViewModel(private val repository: MovieRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null
    private var currentPopularResult: Flow<PagingData<Movie>>? = null

   private val _isFirstStart = MutableStateFlow<Boolean>(true)
     val isFirstStart: StateFlow<Boolean>
     get() = _isFirstStart

    fun popularMovies(): Flow<PagingData<Movie>> {
        _isFirstStart.value = false
        val lastResult = currentPopularResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Movie>> =  repository.loadPopularMoviesWithPage()
                .map { pagingData -> pagingData.map { createMovie(repository.loadMovieDetails(it.id),null) }}
                .cachedIn(viewModelScope)
        currentPopularResult = newResult
        return newResult
    }

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> {
        _isFirstStart.value = false
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