package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class ActorsResponse(
	@Json(name="cast")
	val cast: List<MovieActors>,
	val id: Int
)

data class MovieActors(
	val castId: Int,
	val character: String,
	val gender: Int,
	val creditId: String,
	val knownForDepartment: String,
	val originalName: String,
	val popularity: Double,
	@field:Json(name="name")
	val name: String,
	@field:Json(name="profile_path")
	val profilePath: String,
	val id: Int,
	val adult: Boolean,
	val order: Int
)
