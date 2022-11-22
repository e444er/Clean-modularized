package com.e444er.favorite_feature.presentation.favorite.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e444er.domain.Resource
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.domain.usecase.SearchMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _searchMovie = MutableStateFlow(SearchState())
    val searchMovie: StateFlow<SearchState> = _searchMovie.asStateFlow()

    suspend fun searchName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSearchMovieUseCase(name).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _searchMovie.value = SearchState(data = result.data)
                    }
                    is Resource.Error -> {
                        _searchMovie.value = SearchState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _searchMovie.value = SearchState(isLoading = true)
                    }
                }
            }
        }
    }
}

class SearchState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<MovieListDomainModel>? = emptyList()
)