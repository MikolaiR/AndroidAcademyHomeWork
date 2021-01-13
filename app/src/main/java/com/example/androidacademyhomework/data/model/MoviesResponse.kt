package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MoviesApiResponse(
	@Json(name = "page")
	val page: Int,
	val totalPages: Int,
	@Json(name = "results")
	val results: List<MovieResult>,
	val totalResults: Int
)

data class MovieResult(
	@field:Json(name="id")
	val id: Int,
	val overview: String,
	val originalLanguage: String,
	val originalTitle: String,
	val video: Boolean,
	val title: String,
	val genreIds: List<Int>,
	val posterPath: String,
	val backdropPath: String,
	val releaseDate: String,
	val popularity: Double,
	val voteAverage: Double,
	val adult: Boolean,
	val voteCount: Int
)