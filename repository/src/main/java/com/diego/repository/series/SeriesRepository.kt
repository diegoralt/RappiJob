package com.diego.repository.series

import com.diego.local.dao.series.SeriesDao
import com.diego.model.Series
import com.diego.remote.model.ApiResult
import com.diego.remote.series.SeriesRemote
import com.diego.repository.util.DataDelivery
import retrofit2.Response
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val seriesRemote: SeriesRemote, private val seriesDao: SeriesDao) {

    suspend fun fetchSeriesPopular(key: String, language: String): List<Series> =
        object : DataDelivery<Response<ApiResult<Series>>, List<Series>>() {
            override suspend fun processResponse(response: Response<ApiResult<Series>>): List<Series>? {
                return if (response.isSuccessful && response.body() != null) {
                    response.body()?.results
                } else {
                    null
                }
            }

            override suspend fun saveCallResults(items: List<Series>) {
                seriesDao.saveSeries(items)
            }

            override suspend fun loadFromLocal(): List<Series> =
                seriesDao.getSeriesPopular()

            override suspend fun createCallAsync(): Response<ApiResult<Series>> =
                seriesRemote.fetchSeriesPopular(key, language)
        }.start()

    suspend fun fetchSeriesTopRated(key: String, language: String): List<Series> =
        object : DataDelivery<Response<ApiResult<Series>>, List<Series>>() {
            override suspend fun processResponse(response: Response<ApiResult<Series>>): List<Series>? {
                return if (response.isSuccessful && response.body() != null) {
                    response.body()?.results
                } else {
                    null
                }
            }

            override suspend fun saveCallResults(items: List<Series>) {
                seriesDao.saveSeries(items)
            }

            override suspend fun loadFromLocal(): List<Series> =
                seriesDao.getSeriesTopRated()

            override suspend fun createCallAsync(): Response<ApiResult<Series>> =
                seriesRemote.fetchSeriesTopRated(key, language)
        }.start()

    suspend fun searchSeries(key: String, language: String, query: String): List<Series> =
        object : DataDelivery<Response<ApiResult<Series>>, List<Series>>() {
            override suspend fun processResponse(response: Response<ApiResult<Series>>): List<Series>? {
                return if (response.isSuccessful && response.body() != null) {
                    response.body()?.results
                } else {
                    null
                }
            }

            override suspend fun saveCallResults(items: List<Series>) {
                seriesDao.saveSeries(items)
            }

            override suspend fun loadFromLocal(): List<Series> =
                seriesDao.searchSeries("%$query%")

            override suspend fun createCallAsync(): Response<ApiResult<Series>> =
                seriesRemote.searchSeries(key, language, query)
        }.start()
}