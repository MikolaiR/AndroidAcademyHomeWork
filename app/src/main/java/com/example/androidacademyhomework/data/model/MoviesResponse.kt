package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class MoviesApiResponse(
	@Json(name = "page")
	val page: Int,
	@Json(name = "results")
	val results: List<MovieResult>,
	@field:Json(name = "total_pages")
	val totalPages: Int
)

data class MovieResult(
	@field:Json(name="id")
	val id: Int
)