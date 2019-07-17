package com.diego.remote

import com.diego.remote.model.AccessToken
import com.diego.remote.model.ApiResult
import com.diego.remote.model.Authentication
import com.diego.remote.model.Movie
import com.diego.remote.model.NewToken
import com.diego.remote.model.RequestToken
import com.diego.remote.model.Series
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @POST("/auth/request_token")
    suspend fun requestToken(headers: HashMap<String, String>, @Body params: RequestToken): Response<NewToken>

    @POST("/auth/access_token")
    suspend fun accessToken(headers: HashMap<String, String>, @Body params: AccessToken): Response<Authentication>

    @GET("/account/{account_id}/movie/rated")
    suspend fun fetchMovieRated(
        @Header("Authorization") token: String, @Query("sort_by") sortBy: String?,
        @Path("account_id") accountId: String
    ): Response<ApiResult<Movie>>

    @GET("/account/{account_id}/tv/rated")
    suspend fun fetchSeriesRated(
        @Header("Authorization") token: String, @Query("sort_by") sortBy: String?,
        @Path("account_id") accountId: String
    ): Response<ApiResult<Series>>
}