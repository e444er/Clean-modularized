package com.e444er.dataapi.remote

import com.e444er.dataapi.model.MovieListResponse
import com.e444er.dataapi.model.TmdbApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val PATH_MOVIE_DETAIL = "movie/{$PARAM_MOVIE_ID}"
    }

    @GET("movie/popular?api_key=271236134afbbdcd24c3caaaab027824&language=ru-RU")
    suspend fun fetchMovieList(
        @Query("page") page: Int? = null
    ): TmdbApiResponse
}