package com.diego.local.di

import android.content.Context
import com.diego.local.RappiJobDatabase
import com.diego.local.dao.movie.MovieDao
import com.diego.local.dao.series.SeriesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesRappiJobDatabase(): RappiJobDatabase =
        RappiJobDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providesMovieDao(rappiJobDatabase: RappiJobDatabase): MovieDao =
        rappiJobDatabase.movieDao()

    @Provides
    @Singleton
    fun providesSeriesDao(rappiJobDatabase: RappiJobDatabase): SeriesDao =
        rappiJobDatabase.seriesDao()
}