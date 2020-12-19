package com.example.androidacademyhomework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidacademyhomework.R
import com.example.androidacademyhomework.context
import com.example.androidacademyhomework.data.Movie

class MovieAdapter(private val clickListener: OnRecyclerItemClicked) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
                .load(movie.poster)
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
            ratingBarListFragment.rating = movie.ratings / 2
            textViewReviews.text = context.getString(R.string.reviews, movie.numberOfRatings)
            textViewNameMove.text = movie.title
            textViewFilmDuration.text = context.getString(R.string.duration_min, movie.runtime)
        }
    }
}

interface OnRecyclerItemClicked {
    fun onClick(movie: Movie)
}