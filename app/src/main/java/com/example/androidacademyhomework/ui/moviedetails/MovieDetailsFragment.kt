package com.example.androidacademyhomework.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.ui.MovieViewModelFactory
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.fullUrl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var textViewDetailFragmentAgeLimit: TextView? = null
    private var textViewDetailFragmentNameMove: TextView? = null
    private var textViewDetailFragmentMovieGenre: TextView? = null
    private var textViewDetailFragmentReviews: TextView? = null
    private var textViewDetailFragmentStory: TextView? = null
    private var textViewDetailFragmentCast: TextView? = null
    private var imageViewDetailFragmentTitleBackground: ImageView? = null
    private var ratingBarDetailsFragment: RatingBar? = null
    private var recyclerForActors: RecyclerView? = null

    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: MovieDetailsFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)

        viewModel =
            ViewModelProvider(this, MovieViewModelFactory.Injection.provideViewModelFactory())
                .get(MovieDetailsFragmentViewModel::class.java)

        loadAndUpdateMovie()

        view.findViewById<Button>(R.id.buttonViewBack).setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_movieListFragment)
        }
    }

    private fun initRecycler() {
        recyclerForActors?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ActorsAdapter()
            itemAnimator = DefaultItemAnimator()
            hasFixedSize()
        }
    }

    private fun loadAndUpdateMovie() {
        lifecycleScope.launch {
            viewModel.loadDetailsMovie(args.movieId).collect {
                addDetailsMovieToViews(it)
            }
        }
    }

    override fun onDetach() {
        clearViews()

        super.onDetach()
    }

    private fun addDetailsMovieToViews(movie: Movie) {
        textViewDetailFragmentAgeLimit?.text = getString(R.string.age_limit, movie.minimumAge)
        textViewDetailFragmentMovieGenre?.text = movie.genres.joinToString { it.name }
        ratingBarDetailsFragment?.rating = if (movie.ratings != null) {
            movie.ratings.div(2)
        } else 0F
        textViewDetailFragmentReviews?.text =
            getString(R.string.reviews, movie.numberOfRatings)
        textViewDetailFragmentNameMove?.text = movie.title
        textViewDetailFragmentStory?.text = movie.overview
        imageViewDetailFragmentTitleBackground?.let {
            Glide.with(requireContext())
                .load(movie.backdrop?.fullUrl())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.error_image)
                .into(it)
        }
        if (movie.actors != null) {
            if (movie.actors.isNotEmpty()) {
                initRecycler()
                textViewDetailFragmentCast?.isVisible = true
                (recyclerForActors?.adapter as? ActorsAdapter)?.apply {
                    bindActors(movie.actors)
                }
            } else {
                textViewDetailFragmentCast?.isVisible = false
            }
        } else {
            textViewDetailFragmentCast?.isVisible = false
        }
    }

    private fun setupViews(view: View) {
        textViewDetailFragmentAgeLimit = view.findViewById(R.id.textViewDetailFragmentAgeLimit)
        textViewDetailFragmentNameMove = view.findViewById(R.id.textViewDetailFragmentNameMove)
        textViewDetailFragmentMovieGenre = view.findViewById(R.id.textViewDetailFragmentMovieGenre)
        ratingBarDetailsFragment = view.findViewById(R.id.ratingBarDetailsFragment)
        textViewDetailFragmentReviews = view.findViewById(R.id.textViewDetailFragmentReviews)
        textViewDetailFragmentCast = view.findViewById(R.id.textViewDetailFragmentCast)
        textViewDetailFragmentStory = view.findViewById(R.id.textViewDetailFragmentStory)
        imageViewDetailFragmentTitleBackground =
            view.findViewById(R.id.imageViewDetailFragmentTitleBackground)
        recyclerForActors = view.findViewById(R.id.recyclerForActors)
    }

    private fun clearViews() {
        textViewDetailFragmentAgeLimit = null
        textViewDetailFragmentNameMove = null
        textViewDetailFragmentMovieGenre = null
        textViewDetailFragmentReviews = null
        textViewDetailFragmentStory = null
        textViewDetailFragmentCast = null
        imageViewDetailFragmentTitleBackground = null
        ratingBarDetailsFragment = null
        recyclerForActors = null
    }
}
