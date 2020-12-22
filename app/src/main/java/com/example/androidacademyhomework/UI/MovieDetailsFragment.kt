package com.example.androidacademyhomework.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.adapters.ActorsAdapter
import com.example.androidacademyhomework.domain.MoviesDataSource
import com.example.androidacademyhomework.models.Movie

class MovieDetailsFragment : Fragment() {

    private lateinit var textViewDetailFragmentAgeLimit: TextView
    private lateinit var textViewDetailFragmentNameMove: TextView
    private lateinit var textViewDetailFragmentMovieGenre: TextView
    private lateinit var ratingBarDetailsFragment: RatingBar
    private lateinit var textViewDetailFragmentReviews: TextView
    private lateinit var textViewDetailFragmentStory: TextView
    private lateinit var recyclerForActors: RecyclerView

    private val args: MovieDetailsFragmentArgs by navArgs()
    private var movieId = 0
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewDetailFragmentAgeLimit = view.findViewById(R.id.textViewDetailFragmentAgeLimit)
        textViewDetailFragmentNameMove = view.findViewById(R.id.textViewDetailFragmentNameMove)
        textViewDetailFragmentMovieGenre = view.findViewById(R.id.textViewDetailFragmentMovieGenre)
        ratingBarDetailsFragment = view.findViewById(R.id.ratingBarDetailsFragment)
        textViewDetailFragmentReviews = view.findViewById(R.id.textViewDetailFragmentReviews)
        textViewDetailFragmentStory = view.findViewById(R.id.textViewDetailFragmentStory)
        recyclerForActors = view.findViewById(R.id.recyclerForActors)
        initRecycler()
        view.findViewById<Button>(R.id.buttonViewBack).setOnClickListener {
            returnToMovieListFragment()
        }
        movieId = args.movieId
    }

    private fun initRecycler() {
        recyclerForActors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = ActorsAdapter()
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    override fun onStart() {
        super.onStart()
        getMovieFromId(movieId)
        updateDataMovieForTextView()
        updateData()
    }

    private fun getMovieFromId(movieId:Int){
       MoviesDataSource().getMovies().forEach { if (it.id == movieId) movie = it }
    }

    private fun updateData() {
        (recyclerForActors.adapter as? ActorsAdapter)?.apply {
            bindActors(movie.actors)
        }
    }

    private fun returnToMovieListFragment() {
        findNavController().navigate(R.id.action_movieDetailsFragment_to_movieListFragment)
    }

    private fun updateDataMovieForTextView(){
        textViewDetailFragmentAgeLimit.text = getString(R.string.age_limit, movie.ageLimit)
        textViewDetailFragmentMovieGenre.text = movie.genre
        ratingBarDetailsFragment.rating = movie.rating.toFloat()
        textViewDetailFragmentReviews.text = getString(R.string.reviews, movie.reviews)
        textViewDetailFragmentNameMove.text = movie.movieName
        textViewDetailFragmentStory.text = movie.story
    }
}
