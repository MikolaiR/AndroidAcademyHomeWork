package com.example.androidacademyhomework

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhomework.data.model.*

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context

fun createMovie(
    movieDetails: DetailsMovie,
    actors: ActorsResponse?
): Movie{
   return Movie(
            id = movieDetails.id,
            title = movieDetails.title,
            overview = movieDetails.overview,
            poster = movieDetails.posterPath,
            backdrop = movieDetails.backdropPath,
            ratings = movieDetails.voteAverage.toFloat(),
            numberOfRatings = movieDetails.voteCount,
            minimumAge = if (movieDetails.adult) 16 else 13,
            runtime = movieDetails.runtime,
            genres = movieDetails.genres,
            actors = actors?.cast
        )
    }