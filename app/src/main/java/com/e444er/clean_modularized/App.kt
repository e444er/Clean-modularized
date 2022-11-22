package com.e444er.clean_modularized

import android.app.Application
import com.e444er.dataapi.di.apiModule
import com.e444er.dataapi.di.networkModule
import com.e444er.domain.di.domainModule
import com.e444er.favorite_feature.presentation.favorite.searchModule
import com.e444er.home_feature.presentation.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                apiModule,
                networkModule,
                homeModule,
                domainModule,
                searchModule
            )
        }
    }
}