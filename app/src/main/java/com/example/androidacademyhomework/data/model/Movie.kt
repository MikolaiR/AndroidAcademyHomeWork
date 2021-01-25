package com.example.androidacademyhomework.data.model

data class Movie(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val ratings: Float?,
    val numberOfRatings: Int?,
    val minimumAge: Int?,
    val runtime: Int?,
    val genres: List<Genres>,
    val actors: List<MovieActor>?
)
