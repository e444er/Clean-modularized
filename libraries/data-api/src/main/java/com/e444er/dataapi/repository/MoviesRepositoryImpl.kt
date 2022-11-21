package com.e444er.dataapi.repository

import com.e444er.dataapi.mapper.toResponse
import com.e444er.dataapi.remote.MovieService
import com.e444er.domain.model.TmdbApiResponseDomain
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepositoryImpl() : MoviesRepository, KoinComponent {

    private val apiService: MovieService by inject()


    override suspend fun getMovieList(list: String, page: Int?): TmdbApiResponseDomain {
        return apiService.fetchMovieList(list, page).toResponse()

    }

    override suspend fun getMovieDetail(): Flow<TmdbApiResponseDomain> {
        TODO("Not yet implemented")
    }

}

