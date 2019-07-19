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
            override fun processResponse(response: Response<ApiResult<Series>>): List<Series> =
                response.body()?.results ?: arrayListOf()

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
            override fun processResponse(response: Response<ApiResult<Series>>): List<Series> =
                response.body()?.results ?: arrayListOf()

            override suspend fun saveCallResults(items: List<Series>) {
                seriesDao.saveSeries(items)
            }

            override suspend fun loadFromLocal(): List<Series> =
                seriesDao.getSeriesTopRated()

            override suspend fun createCallAsync(): Response<ApiResult<Series>> =
                seriesRemote.fetchSeriesTopRated(key, language)
        }.start()
}