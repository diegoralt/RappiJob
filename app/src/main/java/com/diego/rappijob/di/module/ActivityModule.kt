package com.diego.rappijob.di.module

import com.diego.rappijob.fragments.MoviesFragment
import com.diego.rappijob.fragments.SeriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeSeriesFragment(): SeriesFragment

}