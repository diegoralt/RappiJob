package com.diego.remote.series

import com.diego.remote.Service
import javax.inject.Inject

class SeriesRemote @Inject constructor(private val service: Service) {

    suspend fun fetchSeriesPopular(key: String, language: String, page: Int = 1) =
        service.fetchSeriesPopular(key, language, page)

    suspend fun fetchSeriesTopRated(key: String, language: String, page: Int = 1) =
        service.fetchSeriesTopRated(key, language, page)

    suspend fun searchSeries(key: String, language: String, query: String, page: Int = 1) =
        service.searchSeries(key, language, query, page)

}