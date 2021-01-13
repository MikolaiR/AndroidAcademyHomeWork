package com.example.androidacademyhomework.UI.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.UI.MovieViewModelFactory
import com.example.androidacademyhomework.adapters.MovieAdapter
import com.example.androidacademyhomework.adapters.OnRecyclerItemClicked
import com.example.androidacademyhomework.data.model.Movie
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment(R.layout.fragment_movie_list) {

    private var recyclerMovieList: RecyclerView? = null
    private lateinit var viewModel: MoviesListFragmentViewModel

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            goToMovieDetailFragment(movie.id)
        }
    }
    private val adapterMovies = MovieAdapter(clickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        viewModel =
            ViewModelProvider(this, MovieViewModelFactory.Injection.provideViewModelFactory())
                .get(MoviesListFragmentViewModel::class.java)

        initRecycler()

        viewModel.loadMovies().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapterMovies.submitData(it)
            }
        })
    }

    private fun initRecycler() {
        recyclerMovieList?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterMovies
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    override fun onDetach() {
        recyclerMovieList = null
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
