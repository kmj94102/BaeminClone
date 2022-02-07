package com.example.baeminclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaeMinClone : Application() {
    companion object {
        private lateinit var application : BaeMinClone
        fun getInstance() : BaeMinClone = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}