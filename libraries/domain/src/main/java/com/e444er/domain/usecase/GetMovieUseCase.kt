package com.e444er.domain.usecase

import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GetMovieUseCase : KoinComponent {
    val moviesRepository: MoviesRepository

    suspend operator fun invoke(
        list: String,
        page: Int? = null
    ): Flow<Resource<List<MovieListDomainModel>>>
}

class GetMovieUseCaseImpl : GetMovieUseCase {
    override val moviesRepository: MoviesRepository by inject();

    override suspend fun invoke(
        list: String,
        page: Int?
    ): Flow<Resource<List<MovieListDomainModel>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val movie = moviesRepository.getMovieList(list, page).results.orEmpty()
                emit(Resource.Success(movie))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "ERROR"))
            }

        }
    }
}