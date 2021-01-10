package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MoviesApiResponse(
	@Json(name = "page")
	val page: Int,
	@Json(name = "total_pages")
	val totalPages: Int,
	@Json(name = "results")
	val results: List<MovieResult>,
	@Json(name = "total_results")
	val totalResults: Int
)

data class MovieResult(
	@field:Json(name="id")
	val id: Int,
	@field:Json(name="overview")
	val overview: String,
	@Json(name="original_language")
	val originalLanguage: String,
	@Json(name="original_title")
	val originalTitle: String,
	@Json(name="video")
	val video: Boolean,
	@field:Json(name="title")
	val title: String,
	@field:Json(name="genre_ids")
	val genreIds: List<Int>,
	@field:Json(name="poster_path")
	val posterPath: String,
	@field:Json(name="backdrop_path")
	val backdropPath: String,
	@Json(name="release_date")
	val releaseDate: String,
	@Json(name="popularity")
	val popularity: Double,
	@field:Json(name="vote_average")
	val voteAverage: Double,
	@field:Json(name="adult")
	val adult: Boolean,
	@field:Json(name="vote_count")
	val voteCount: Int
)