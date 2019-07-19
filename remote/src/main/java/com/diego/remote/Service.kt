package com.diego.remote

import com.diego.model.Movie
import com.diego.model.Series
import com.diego.remote.model.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/movie/popular")
    suspend fun fetchMoviesPopular(
        @Query("api_key") key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiResult<Movie>>

    @GET("/movie/top_rated")
    suspend fun fetchMoviesTopRated(
        @Query("api_key") key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiResult<Movie>>

    @GET("/movie/upcoming")
    suspend fun fetchMoviesUpcoming(
        @Query("api_key") key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiResult<Movie>>

    @GET("/tv/popular")
    suspend fun fetchSeriesPopular(
        @Query("api_key") key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiResult<Series>>

    @GET("/tv/top_rated")
    suspend fun fetchSeriesTopRated(
        @Query("api_key") key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiResult<Series>>

}