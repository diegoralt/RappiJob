package com.diego.remote.movie

import com.diego.remote.Service
import javax.inject.Inject

class MovieRemote @Inject constructor(private val service: Service) {

    suspend fun fetchMoviesPopular(key: String, language: String, page: Int = 1) =
        service.fetchMoviesPopular(key, language, page)

    suspend fun fetchMoviesTopRated(key: String, language: String, page: Int = 1) =
        service.fetchMoviesTopRated(key, language, page)

    suspend fun fetchMoviesUpcoming(key: String, language: String, page: Int = 1) =
        service.fetchMoviesUpcoming(key, language, page)

    suspend fun searchMovie(key: String, language: String, query: String, page: Int = 1) =
        service.searchMovie(key, language, query, page)

}