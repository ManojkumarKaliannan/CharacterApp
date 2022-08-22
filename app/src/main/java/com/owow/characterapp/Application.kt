package com.owow.characterapp

import android.app.Application
import com.owow.characterapp.ui.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        //Setting up koin
        val moduleList = listOf(
            AppModule().viewModel,
            AppModule().networkModule,
            AppModule().repository,
            AppModule().useCaseModule,
            AppModule().preference
        )
        startKoin {
            androidContext(this@Application)
            modules(moduleList)
        }
    }
}