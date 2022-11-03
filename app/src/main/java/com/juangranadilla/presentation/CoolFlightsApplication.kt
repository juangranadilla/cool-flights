package com.juangranadilla.presentation

import android.app.Application
import com.juangranadilla.data.di.dataModule
import com.juangranadilla.domain.di.domainModule
import com.juangranadilla.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoolFlightsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoolFlightsApplication)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}