package com.diego.domain.series

import com.diego.model.Series
import com.diego.repository.series.SeriesRepository
import javax.inject.Inject

class SeriesUseCases @Inject constructor(private val seriesRepository: SeriesRepository) {

    suspend fun fetchSeriesPopular(key: String, language: String): List<Series> =
        seriesRepository.fetchSeriesPopular(key, language)

    suspend fun fetchSeriesTopRated(key: String, language: String): List<Series> =
        seriesRepository.fetchSeriesTopRated(key, language)

    suspend fun searchSeries(key: String, language: String, query: String) =
        seriesRepository.searchSeries(key, language, query)

}