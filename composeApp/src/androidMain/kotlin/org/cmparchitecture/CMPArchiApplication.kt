package org.cmparchitecture

import android.app.Application
import org.di.initKoin
import org.koin.android.ext.koin.androidContext

class CMPArchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initContext(this)
        initKoin {
            androidContext(this@CMPArchApplication)
        }
    }
}