package com.e444er.domain.di

import com.e444er.domain.usecase.GetMovieUseCase
import com.e444er.domain.usecase.GetMovieUseCaseImpl
import com.e444er.domain.usecase.SearchMovieUseCase
import com.e444er.domain.usecase.SearchMovieUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetMovieUseCase> { GetMovieUseCaseImpl() }
    factory<SearchMovieUseCase> { SearchMovieUseCaseImpl() }
}