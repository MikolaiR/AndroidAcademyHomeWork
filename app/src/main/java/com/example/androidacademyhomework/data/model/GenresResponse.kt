package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class GenresResponse(
    @Json(name="genres")
    val genres: List<GenresItem>
)

data class GenresItem(
    @field:Json(name="name")
    val name: String,
    @field:Json(name="id")
    val id: Int
)