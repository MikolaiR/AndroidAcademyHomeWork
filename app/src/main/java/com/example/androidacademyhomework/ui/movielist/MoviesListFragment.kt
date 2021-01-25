package com.example.androidacademyhomework.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.ui.MovieViewModelFactory
import com.example.androidacademyhomework.adapters.MovieAdapter
import com.example.androidacademyhomework.adapters.OnRecyclerItemClicked
import com.example.androidacademyhomework.data.model.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment(R.layout.fragment_movie_list) {

    private var recyclerMovieList: RecyclerView? = null
    private var searchEditText: EditText? = null
    private var progressBarLoading: ProgressBar? = null
    private lateinit var viewModel: MoviesListFragmentViewModel
    private var searchJob: Job? = null

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            goToMovieDetailFragment(movie.id)
        }
    }
    private val adapterMovies = MovieAdapter(clickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        searchEditText = view.findViewById(R.id.editTextSearch)
        progressBarLoading = view.findViewById(R.id.progressBarLoading)
        viewModel =
            ViewModelProvider(this, MovieViewModelFactory.Injection.provideViewModelFactory())
                .get(MoviesListFragmentViewModel::class.java)
        initRecycler()
        initEditText()
        if (viewModel.isFirstStart.value) {
            addPopularMoviesToAdapter()
        }
        view.findViewById<Button>(R.id.buttonViewMenu).setOnClickListener {
            addPopularMoviesToAdapter()
            searchEditText?.text?.clear()
        }
    }

    private fun initEditText() {
        searchEditText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText?.text?.trim().toString()
                addSearchMoviesToAdapter(query)
                return@setOnEditorActionListener true
            } else
                return@setOnEditorActionListener false
        }
    }

    private fun addSearchMoviesToAdapter(query: String) {
        searchJob?.cancel()
        recyclerMovieList?.scrollToPosition(0)
        searchJob = lifecycleScope.launch {
            viewModel.searchMovies(query).collectLatest {
                adapterMovies.submitData(it)
            }
        }
    }

    private fun addPopularMoviesToAdapter() {
        searchJob?.cancel()
        recyclerMovieList?.scrollToPosition(0)
        searchJob = lifecycleScope.launch {
            viewModel.popularMovies().collectLatest {
                adapterMovies.submitData(it)
            }
        }
    }

    private fun initRecycler() {
        adapterMovies.addLoadStateListener {
            progressBarLoading?.isVisible = it.refresh is LoadState.Loading
        }
        recyclerMovieList?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterMovies
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    override fun onDetach() {
        recyclerMovieList = null
        searchEditText = null
        progressBarLoading = null
        super.onDetach()
    }

    private fun goToMovieDetailFragment(movieId: Int?) {
        if (movieId != null)
            findNavController().navigate(
                MoviesListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
                    movieId
                )
            )
    }
}
