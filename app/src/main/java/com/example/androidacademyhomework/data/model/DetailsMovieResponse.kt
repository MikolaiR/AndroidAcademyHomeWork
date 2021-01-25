package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class DetailsMovie(
	@field:Json(name="title")
	val title: String?,
	@field:Json(name="backdrop_path")
	val backdropPath: String?,
	@field:Json(name="genres")
	val genres: List<Genres>,
	@field:Json(name="id")
	val id: Int?,
	@field:Json(name="vote_count")
	val voteCount: Int?,
	@field:Json(name="overview")
	val overview: String?,
	@field:Json(name="runtime")
	val runtime: Int?,
	@field:Json(name="poster_path")
	val posterPath: String?,
	@field:Json(name="vote_average")
	val voteAverage: Double,
	@field:Json(name="adult")
	val adult: Boolean,
)
