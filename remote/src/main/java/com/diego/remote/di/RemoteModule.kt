package com.diego.remote.di

import com.diego.remote.Service
import com.diego.remote.ServiceFactory
import com.diego.remote.auth.AuthenticationRemote
import com.diego.remote.movie.MovieRemote
import com.diego.remote.series.SeriesRemote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun providesService(): Service =
        ServiceFactory.getInstance(baseUrl)

    @Singleton
    @Provides
    fun providesAuthenticationRemote(service: Service): AuthenticationRemote =
        AuthenticationRemote(service)

    @Singleton
    @Provides
    fun providesMovieRemote(service: Service): MovieRemote =
        MovieRemote(service)

    @Singleton
    @Provides
    fun providesSeriesRemote(service: Service): SeriesRemote =
        SeriesRemote(service)
}