package com.diego.rappijob

import android.app.Application
import androidx.fragment.app.Fragment
import com.diego.local.di.LocalModule
import com.diego.rappijob.di.component.DaggerApplicationComponent
import com.diego.remote.di.RemoteModule
import com.diego.repository.di.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class RappiJobApplication : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .remote(RemoteModule(BuildConfig.BASE_URL))
            .local(LocalModule(applicationContext))
            .repository(RepositoryModule())
            .build()
            .inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        dispatchingAndroidInjector
}