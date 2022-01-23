package com.example.baeminclone

import android.app.Application
import com.example.baeminclone.di.appModule
import org.koin.core.context.startKoin

class BaeMinClone : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }

    }

}