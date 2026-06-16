package com.focos_dengue

import android.app.Application
import com.focos_dengue.di.AppContainer

class FocosDengueApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}