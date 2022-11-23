package com.e444er.dataapi.di

import androidx.room.Room
import com.e444er.dataapi.room.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            "movie-db"
        ).build()
    }

    single {
        get<MovieDatabase>().dao
    }
}