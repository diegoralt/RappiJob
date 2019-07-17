package com.diego.remote.series

import com.diego.remote.Service
import javax.inject.Inject

class SeriesRemote @Inject constructor(private val service: Service) {

    suspend fun fetchSeriesRated(token: String, sortBy: String?, accountId: String) =
        service.fetchSeriesRated(token, sortBy, accountId)

}