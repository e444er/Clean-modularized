package com.e444er.dataapi.room

import androidx.room.*
import com.e444er.dataapi.room.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_db")
    fun getNotes(): Flow<MovieEntity>

    @Query("SELECT * FROM movie_db WHERE id = :id")
    suspend fun getNoteById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(movie: MovieEntity)

    @Delete
    suspend fun deleteNote(movie: MovieEntity)
}