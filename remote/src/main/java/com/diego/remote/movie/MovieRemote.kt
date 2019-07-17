package com.diego.remote.movie

import com.diego.remote.Service
import javax.inject.Inject

class MovieRemote @Inject constructor(private val service: Service) {

    suspend fun fetchMovieRated(token: String, sortBy: String?, accountId: String) =
        service.fetchMovieRated(token, sortBy, accountId)

}