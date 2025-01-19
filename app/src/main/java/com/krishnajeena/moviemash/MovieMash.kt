package com.krishnajeena.moviemash

import android.app.Application
import com.krishnajeena.moviemash.network.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieMash : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MovieMash)
            modules(appModule)
        }
    }
}