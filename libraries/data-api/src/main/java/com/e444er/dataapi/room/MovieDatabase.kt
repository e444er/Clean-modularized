package com.e444er.dataapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e444er.dataapi.room.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
   abstract val dao: MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}