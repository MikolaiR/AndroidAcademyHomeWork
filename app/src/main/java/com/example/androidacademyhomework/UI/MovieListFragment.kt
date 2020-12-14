package com.example.androidacademyhomework.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.adapters.MovieAdapter
import com.example.androidacademyhomework.adapters.OnRecyclerItemClicked
import com.example.androidacademyhomework.data.Movie
import com.example.androidacademyhomework.data.loadMovies
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private var recyclerMovieList: RecyclerView? = null
    private val scope = lifecycleScope

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        initRecycler()
        updateDate()
    }

    private fun initRecycler() {
        recyclerMovieList?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(clickListener)
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    private fun updateDate() {
        scope.launch {
            var listMovies = listOf<Movie>()
            whenStarted {
                listMovies = loadMovies(requireContext())
            }
            if (listMovies.isNotEmpty()) {
                (recyclerMovieList?.adapter as? MovieAdapter)?.apply {
                    bindMovies(listMovies)
                }
            } else {
                Toast.makeText(requireContext(), "Error load movies", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDetach() {
        recyclerMovieList = null

        super.onDetach()
    }

    private fun goToMovieDetailFragment(movie: Movie) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
                movie
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.cancel()
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            goToMovieDetailFragment(movie)
        }
    }
}
