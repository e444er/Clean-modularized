package com.e444er.dataapi.mapper

import com.e444er.dataapi.model.MovieListResponse
import com.e444er.dataapi.model.TmdbApiResponse
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.model.TmdbApiResponseDomain

fun MovieListResponse.toDomain(): MovieListDomainModel {
    return MovieListDomainModel(
        character, id, job, overview, posterPath, releaseDate, title, voteAverage
    )
}


fun TmdbApiResponse.toResponse(): TmdbApiResponseDomain {
    return TmdbApiResponseDomain(
        results.map { it.toDomain() }, totalResults
    )
}
