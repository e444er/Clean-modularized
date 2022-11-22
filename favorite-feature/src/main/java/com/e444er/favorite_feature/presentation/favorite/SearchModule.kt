package com.e444er.favorite_feature.presentation.favorite

import com.e444er.favorite_feature.presentation.favorite.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module { viewModel { SearchViewModel(getSearchMovieUseCase = get()) } }