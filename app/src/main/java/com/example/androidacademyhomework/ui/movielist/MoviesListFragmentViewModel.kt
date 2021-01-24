package com.example.androidacademyhomework.ui.movielist

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.formatToMovie
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

        val resultFlowPagingData = repository.loadPopularMoviesWithPage()
        val newResultMovies = resultFlowPagingData.map { pagingData ->
            pagingData.map {movieResult ->
                formatToMovie(
                    repository.loadMovieDetails(movieResult.id),
                    null
                )
            }
        }.cachedIn(viewModelScope)
        currentPopularResult = newResultMovies
        return newResultMovies
    }

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> {
        _isFirstStart.value = false
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val resultFlowPagingData = repository.loadSearchMoviesListWithPage(queryString)
        val newResultMovies = resultFlowPagingData.map { pagingData ->
            pagingData.map {movieResult ->
                formatToMovie(
                    repository.loadMovieDetails(movieResult.id),
                    null
                )
            }
        }.cachedIn(viewModelScope)
        currentSearchResult = newResultMovies
        return newResultMovies
    }
}