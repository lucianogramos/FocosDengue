package com.focos_dengue

import android.app.Application
import com.focos_dengue.di.AppContainer

class FocosDengueApplication : Application() {
    val appContainer = AppContainer()
}