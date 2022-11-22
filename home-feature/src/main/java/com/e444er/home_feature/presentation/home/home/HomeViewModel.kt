package com.e444er.home_feature.presentation.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.model.TmdbApiResponseDomain
import com.e444er.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { context, exception ->
        Log.e("Context: $context", exception.message.orEmpty())
    }

    private val _moviesListStateFlow = MutableStateFlow(HomeState())
    val moviesListStateFlow: StateFlow<HomeState> = _moviesListStateFlow.asStateFlow()

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _moviesListStateFlow.value = HomeState(data = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _moviesListStateFlow.value = HomeState(
                            error = result.message ?: "An unexpected error"
                        )
                    }
                    is Resource.Loading -> {
                        _moviesListStateFlow.value = HomeState(isLoading = true)
                    }
                }
            }
        }
    }

//    fun getMovies() {
//        getMovieUseCase.invoke().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _moviesListStateFlow.value = HomeState(isLoading = true)
//                }
//                is Resource.Error -> {
//                    _moviesListStateFlow.value =
//                        HomeState(error = it.message ?: "An unexpected error occurred")
//                }
//                is Resource.Success -> {
//                    _moviesListStateFlow.value = HomeState(data = it.data)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}


class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<MovieListDomainModel>? = emptyList()
)

