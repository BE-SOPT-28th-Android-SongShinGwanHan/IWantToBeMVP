package com.hk.iwanttobesupermvp.application

import android.app.Application
import com.hk.iwanttobesupermvp.util.PixelRatio
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeSingleton()
    }

    private fun initializeSingleton() {
        pixelRatio = PixelRatio(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}