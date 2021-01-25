package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class MoviesSearchResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieResult>,
)