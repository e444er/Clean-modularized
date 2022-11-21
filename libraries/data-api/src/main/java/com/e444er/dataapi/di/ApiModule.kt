package com.e444er.dataapi.di

import com.e444er.dataapi.repository.MoviesRepositoryImpl
import com.e444er.domain.repository.MoviesRepository
import org.koin.dsl.module

val apiModule = module {

    single<MoviesRepository> { MoviesRepositoryImpl() }

}