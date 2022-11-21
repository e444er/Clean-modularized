package com.e444er.home_feature.presentation.home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { context, exception ->
        Log.e("Context: $context", exception.message.orEmpty())
    }

    private val _moviesListStateFlow = MutableStateFlow(HomeState())
    val moviesListStateFlow: StateFlow<HomeState> = _moviesListStateFlow.asStateFlow()


    fun getMovies(list: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieUseCase(
                list = list,
                page = 1
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _moviesListStateFlow.value = HomeState(data = result.data)
                    }
                    is Resource.Error -> {
                        _moviesListStateFlow.value = HomeState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _moviesListStateFlow.value = HomeState(isLoading = true)
                    }
                }
            }
        }
    }
}


class HomeState(
    val isLoading: Boolean = false,
    val data: List<MovieListDomainModel>? = null,
    val error: String = ""
)

