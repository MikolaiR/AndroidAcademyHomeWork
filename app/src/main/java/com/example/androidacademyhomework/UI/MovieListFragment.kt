package com.example.androidacademyhomework.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.adapters.MovieAdapter
import com.example.androidacademyhomework.adapters.OnRecyclerItemClicked
import com.example.androidacademyhomework.domain.MoviesDataSource
import com.example.androidacademyhomework.models.Movie

class MovieListFragment : Fragment() {

    private var recyclerMovieList:RecyclerView? = null

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
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun initRecycler() {
        recyclerMovieList?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(clickListener)
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie:Movie) {
            goToMovieDetailFragment(movie.id)
        }
    }

    private fun updateData() {
        (recyclerMovieList?.adapter as? MovieAdapter)?.apply {
            bindActors(MoviesDataSource().getMovies())
        }
    }

    override fun onDetach() {
        recyclerMovieList = null
        super.onDetach()
    }

    private fun goToMovieDetailFragment(movieId:Int){
        val direction = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(direction)
    }
}
