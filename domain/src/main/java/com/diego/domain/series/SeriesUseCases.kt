package com.diego.domain.series

import com.diego.repository.series.SeriesRepository
import javax.inject.Inject

class SeriesUseCases @Inject constructor(private val seriesRepository: SeriesRepository) {

    suspend fun fetchSeriesPopular(key: String, language: String) =
        seriesRepository.fetchSeriesPopular(key, language)

    suspend fun fetchSeriesTopRated(key: String, language: String) =
        seriesRepository.fetchSeriesTopRated(key, language)

}