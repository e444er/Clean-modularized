package com.e444er.dataapi.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("character")
    val character: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)