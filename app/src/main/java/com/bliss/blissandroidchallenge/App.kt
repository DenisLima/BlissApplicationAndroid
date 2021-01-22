package com.bliss.blissandroidchallenge

import android.app.Application
import com.bliss.blissandroidchallenge.data.di.dataModule
import com.bliss.blissandroidchallenge.domain.di.domainModule
import com.bliss.blissandroidchallenge.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}