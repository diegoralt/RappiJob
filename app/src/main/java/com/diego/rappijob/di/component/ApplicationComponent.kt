package com.diego.rappijob.di.component

import android.app.Application
import com.diego.local.di.LocalModule
import com.diego.rappijob.RappiJobApplication
import com.diego.rappijob.di.module.ActivityModule
import com.diego.rappijob.di.module.ViewModelModule
import com.diego.remote.di.RemoteModule
import com.diego.repository.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        LocalModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)

@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun remote(remoteModule: RemoteModule): Builder
        fun local(localModule: LocalModule): Builder
        fun repository(repositoryModule: RepositoryModule): Builder
        fun build(): ApplicationComponent
    }

    fun inject(appController: RappiJobApplication)
}