package com.e444er.dataapi.repository

import com.e444er.dataapi.mapper.toDomain
import com.e444er.dataapi.remote.MovieService
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.model.TmdbApiResponseDomain
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepositoryImpl() : MoviesRepository, KoinComponent {

    private val apiService: MovieService by inject()

    override suspend fun getMovieList(): List<MovieListDomainModel> {
        return try {
            apiService.fetchMovieList(1).results.map { it.toDomain() }

        } catch (e: Exception) {
            error("API SERVICE")
        }
    }

    override suspend fun getSearch(name: String):  List<MovieListDomainModel> {
        return apiService.searchMovie(name).results.map { it.toDomain() }
    }


}

