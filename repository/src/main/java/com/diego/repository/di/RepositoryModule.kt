package com.diego.repository.di

import com.diego.local.dao.movie.MovieDao
import com.diego.local.dao.series.SeriesDao
import com.diego.remote.movie.MovieRemote
import com.diego.remote.series.SeriesRemote
import com.diego.repository.movie.MovieRepository
import com.diego.repository.series.SeriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepository(movieRemote: MovieRemote, movieDao: MovieDao): MovieRepository =
        MovieRepository(movieRemote, movieDao)

    @Provides
    @Singleton
    fun providesSeriesRepository(seriesRemote: SeriesRemote, seriesDao: SeriesDao): SeriesRepository =
        SeriesRepository(seriesRemote, seriesDao)

}