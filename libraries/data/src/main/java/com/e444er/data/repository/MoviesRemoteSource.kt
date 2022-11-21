package com.e444er.data.repository

import com.e444er.data.model.GenreRepositoryModel
import com.e444er.data.model.TmdbApiResponseRepository
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteSource {
    suspend fun getGenreList(): Flow<GenreRepositoryModel>

    suspend fun getMovieList(
        list: String,
        page: Int? = null
    ): Flow<TmdbApiResponseRepository>

    suspend fun getMovieDetail(): Flow<TmdbApiResponseRepository>
}