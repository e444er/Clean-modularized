package com.e444er.dataapi.model

import com.google.gson.annotations.SerializedName

data class TmdbApiResponse(
    @SerializedName("results")
    val results: List<MovieListResponse>,
    @SerializedName("total_results")
    val totalResults: Int
)

