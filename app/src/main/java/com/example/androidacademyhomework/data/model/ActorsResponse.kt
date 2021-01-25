package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class ActorsResponse(
	@Json(name="cast")
	val cast: List<MovieActor>,
)

data class MovieActor(
	@field:Json(name="name")
	val name: String,
	@field:Json(name="profile_path")
	val profilePath: String?,
)
