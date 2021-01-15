package com.example.androidacademyhomework.data.model

import com.squareup.moshi.Json

data class DetailsMovie(
	val originalLanguage: String,
	val imdbId: String,
	val video: Boolean,
	@field:Json(name="title")
	val title: String?,
	@field:Json(name="backdrop_path")
	val backdropPath: String?,
	val revenue: Long,
	@field:Json(name="genres")
	val genres: List<Genres>,
	val popularity: Double,
	val productionCountries: List<ProductionCountriesItem>,
	@field:Json(name="id")
	val id: Int?,
	@field:Json(name="vote_count")
	val voteCount: Int?,
	val budget: Int,
	@field:Json(name="overview")
	val overview: String?,
	val originalTitle: String,
	@field:Json(name="runtime")
	val runtime: Int?,
	@field:Json(name="poster_path")
	val posterPath: String?,
	val spokenLanguages: List<SpokenLanguagesItem>,
	val productionCompanies: List<ProductionCompaniesItem>,
	val releaseDate: String,
	@field:Json(name="vote_average")
	val voteAverage: Double,
	val belongsToCollection: BelongsToCollection,
	val tagline: String,
	@field:Json(name="adult")
	val adult: Boolean,
	val homepage: String,
	val status: String
)

data class ProductionCountriesItem(
	val iso31661: String,
	val name: String
)

data class BelongsToCollection(
	val backdropPath: String,
	val name: String,
	val id: Int,
	val posterPath: String
)

data class ProductionCompaniesItem(
	val logoPath: String,
	val name: String,
	val id: Int,
	val originCountry: String
)

data class SpokenLanguagesItem(
	val name: String,
	val iso6391: String,
	val englishName: String
)
