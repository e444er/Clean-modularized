package com.e444er.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TmdbApiResponseDomain(
    val results: List<MovieListDomainModel>,
    val totalResults: Int? = 0
)

@Parcelize
data class MovieListDomainModel(
    val character: String?,
    val id: Int,
    val job: String?,
    val overview: String?,
    val poster_path: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double
): Parcelable