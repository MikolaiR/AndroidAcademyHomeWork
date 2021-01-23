package com.example.androidacademyhomework.ui.movielist

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.context
import com.example.androidacademyhomework.data.model.Movie
import com.example.androidacademyhomework.fullUrl

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageViewTitleBackground =
        itemView.findViewById<ImageView>(R.id.imageViewTitleBackground)
    private val imageViewLike = itemView.findViewById<ImageView>(R.id.imageViewLike)
    private val textViewAgeLimit = itemView.findViewById<TextView>(R.id.textViewAgeLimit)
    private val textViewMovieGenre = itemView.findViewById<TextView>(R.id.textViewMovieGenre)
    private val ratingBarListFragment =
        itemView.findViewById<RatingBar>(R.id.ratingBarListFragment)
    private val textViewReviews = itemView.findViewById<TextView>(R.id.textViewReviews)
    private val textViewNameMove = itemView.findViewById<TextView>(R.id.textViewNameMove)
    private val textViewFilmDuration =
        itemView.findViewById<TextView>(R.id.textViewFilmDuration)

    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.poster?.fullUrl())
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.error_image)
            .into(imageViewTitleBackground)
        imageViewLike.apply {
            setImageResource(R.drawable.ic_baseline_favorite_white)
            setOnClickListener {
                imageViewLike.setColorFilter(R.color.pink)
            }
        }
        textViewAgeLimit.text = context.getString(R.string.age_limit, movie.minimumAge)
        textViewMovieGenre.text = movie.genres.joinToString { it.name }
        ratingBarListFragment.rating = if (movie.ratings != null) {
            movie.ratings.div(2)
        } else 0F
        textViewReviews.text = context.getString(R.string.reviews, movie.numberOfRatings)
        textViewNameMove.text = movie.title
        textViewFilmDuration.text = context.getString(R.string.duration_min, movie.runtime)
    }
}
