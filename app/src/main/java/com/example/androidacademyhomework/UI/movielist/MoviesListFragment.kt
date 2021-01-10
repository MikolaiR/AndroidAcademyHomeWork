package com.example.androidacademyhomework.UI.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.adapters.MovieAdapter
import com.example.androidacademyhomework.adapters.OnRecyclerItemClicked
import com.example.androidacademyhomework.data.model.Movie

class MoviesListFragment : Fragment(R.layout.fragment_movie_list) {

    private var recyclerMovieList: RecyclerView? = null
    private val viewModel by viewModels<MoviesListFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        initRecycler()
        /*GlobalScope.launch(Dispatchers.IO) {
            RetrofitInstance().getService().getActors(743904,BuildConfig.MOVIE_DATABASE_API_KEY)
        }*/
        viewModel.getMovies()
        viewModel.moviesLiveData.observe(this.viewLifecycleOwner,{
            updateDate(it)
        })
    }

    private fun initRecycler() {
        recyclerMovieList?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(clickListener)
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    private fun updateDate(movies: List<Movie>) {
            (recyclerMovieList?.adapter as? MovieAdapter)?.apply {
                bindMovies(movies)
            }
    }

    override fun onDetach() {
        recyclerMovieList = null

        super.onDetach()
    }

    private fun goToMovieDetailFragment(movieId: Int) {
        findNavController().navigate(
            MoviesListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
                movieId
            )
        )
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            goToMovieDetailFragment(movie.id)
        }
    }
}
