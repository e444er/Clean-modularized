package com.e444er.domain.usecase

import android.util.Log
import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SearchMovieUseCase : KoinComponent {

    val repo: MoviesRepository

    operator fun invoke(query: String): Flow<Resource<List<MovieListDomainModel>>>
}

class SearchMovieUseCaseImpl : SearchMovieUseCase {
    override val repo: MoviesRepository by inject()

    override fun invoke(name: String): Flow<Resource<List<MovieListDomainModel>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data  = repo.getSearch(name = name)
                Log.d("Tagg", "$data")
                emit(Resource.Success(data ))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "ERROR"))
            }
        }
    }

}