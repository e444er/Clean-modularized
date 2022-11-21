package com.e444er.home_feature.presentation.home.di

import com.e444er.home_feature.presentation.home.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module { viewModel { HomeViewModel(getMovieUseCase = get()) } }