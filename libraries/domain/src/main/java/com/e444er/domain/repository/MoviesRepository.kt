package com.e444er.domain.repository

import com.e444er.domain.model.TmdbApiResponseDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovieList(
        list: String,
        page: Int? = null
    ): TmdbApiResponseDomain

    suspend fun getMovieDetail(): Flow<TmdbApiResponseDomain>
}