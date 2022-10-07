package org.sopt.sample

import android.app.Application
import timber.log.Timber

class SoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}