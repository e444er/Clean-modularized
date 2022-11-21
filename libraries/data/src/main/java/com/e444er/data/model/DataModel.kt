package com.e444er.data.model

data class TmdbApiResponseRepository(
    val page: Int? = 0,
    val totalResults: Int? = 0,
    val totalPages: Int? = 0,
    var results: List<MovieListRepositoryModel>? = null,
)

data class MovieListRepositoryModel(
    val id: String,
    val posterPath: String?,
    val title: String?,
    val releaseDate: String?,
    var genreList: List<GenreRepositoryModel>?,
    val voteAverage: Float?,
    val voteCount: Int? = null,
    val backdropPath: String?,
    val genreIds: List<Int>?
)

data class GenreRepositoryModel(
    val id: Int,
    val name: String?
)