package com.e444er.dataapi.mapper

import com.e444er.dataapi.model.MovieListResponse
import com.e444er.dataapi.model.TmdbApiResponse
import com.e444er.dataapi.room.model.MovieEntity
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.model.TmdbApiResponseDomain

fun MovieListResponse.toDomain(): MovieListDomainModel {
    return MovieListDomainModel(
        character, id, job, overview, poster_path, releaseDate, title, voteAverage
    )
}

fun MovieListDomainModel.toEntity(): MovieEntity {
    return MovieEntity(
        character, id, job, overview, poster_path, releaseDate, title, voteAverage
    )
}

fun MovieEntity.toModel(): MovieListDomainModel {
    return MovieListDomainModel(
        character, id, job, overview, poster_path, releaseDate, title, voteAverage
    )
}


fun TmdbApiResponse.toResponse(): TmdbApiResponseDomain {
    return TmdbApiResponseDomain(
        results.map { it.toDomain() }, totalResults
    )
}
