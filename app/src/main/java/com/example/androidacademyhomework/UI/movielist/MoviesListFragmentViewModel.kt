package com.example.androidacademyhomework.UI.movielist

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidacademyhomework.createMovie
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.repository.MovieRepository
import kotlinx.coroutines.flow.*

class MoviesListFragmentViewModel(private val repository: MovieRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null
    private var currentResult: Flow<PagingData<Movie>>? = null

   private val _isSearch = MutableStateFlow<Boolean>(false)
     val isSearch: StateFlow<Boolean>
     get() = _isSearch

    fun popularMovies(): Flow<PagingData<Movie>> {
        _isSearch.value = true
        val lastResult = currentResult
        if (lastResult != null) {
            return lastResult
        }
        return repository.loadPopularMoviesWithPage()
                .map { pagingData -> pagingData.map { createMovie(repository.loadMovieDetails(it.id),null) }}
                .cachedIn(viewModelScope)
    }

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> {
        _isSearch.value = true
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


