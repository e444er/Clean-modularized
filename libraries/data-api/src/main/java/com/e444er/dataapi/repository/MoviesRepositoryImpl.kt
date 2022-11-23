package com.e444er.dataapi.repository

import com.e444er.dataapi.mapper.toDomain
import com.e444er.dataapi.mapper.toEntity
import com.e444er.dataapi.mapper.toModel
import com.e444er.dataapi.remote.MovieService
import com.e444er.dataapi.room.MovieDao
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepositoryImpl() : MoviesRepository, KoinComponent {

    private val apiService: MovieService by inject()
    private val dao: MovieDao by inject()

    override suspend fun getMovieList(): List<MovieListDomainModel> {
        return try {
            apiService.fetchMovieList(1).results.map { it.toDomain() }

        } catch (e: Exception) {
            error("API SERVICE")
        }
    }

    override suspend fun getSearch(name: String): List<MovieListDomainModel> {
        return apiService.searchMovie(name).results.map { it.toDomain() }
    }

    override fun getNotes(): Flow<MovieListDomainModel> {
        return dao.getNotes().map { it.toModel() }
    }

    override suspend fun getNoteById(id: Int): MovieListDomainModel? {
        return dao.getNoteById(id)?.toModel()
    }

    override suspend fun insertNote(movie: MovieListDomainModel) {
        dao.insertNote(movie.toEntity())
    }

    override suspend fun deleteNote(movie: MovieListDomainModel) {
        dao.deleteNote(movie.toEntity())
    }


}

