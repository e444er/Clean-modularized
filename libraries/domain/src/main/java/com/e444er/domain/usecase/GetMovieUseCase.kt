package com.e444er.domain.usecase

import android.util.Log
import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface GetMovieUseCase : KoinComponent {

    val moviesRepository: MoviesRepository

    operator fun invoke(): Flow<Resource<List<MovieListDomainModel>>>
}

class GetMovieUseCaseImpl : GetMovieUseCase {
    override val moviesRepository: MoviesRepository by inject();

    override fun invoke(): Flow<Resource<List<MovieListDomainModel>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data  = moviesRepository.getMovieList()
                Log.d("Tagg", "$data")
                emit(Resource.Success(data ))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "ERROR"))
            }
        }
    }

}