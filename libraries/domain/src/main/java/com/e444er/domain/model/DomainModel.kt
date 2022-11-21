package com.e444er.domain.model

data class TmdbApiResponseDomain(
    val results: List<MovieListDomainModel>? = null,
    val totalResults: Int? = 0
)

data class MovieListDomainModel(
    val character: String?,
    val id: Int,
    val job: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double
)