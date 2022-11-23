package com.e444er.dataapi.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e444er.dataapi.room.MovieDatabase.Companion.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class MovieEntity(
    val character: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val job: String?,
    val overview: String?,
    val poster_path: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double
)
