package com.e444er.domain.repository

import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.model.TmdbApiResponseDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovieList(): List<MovieListDomainModel>

    suspend fun getSearch(name: String): List<MovieListDomainModel>

    fun getNotes(): Flow<MovieListDomainModel>

    suspend fun getNoteById(id: Int): MovieListDomainModel?

    suspend fun insertNote(movie: MovieListDomainModel)

    suspend fun deleteNote(movie: MovieListDomainModel)

}