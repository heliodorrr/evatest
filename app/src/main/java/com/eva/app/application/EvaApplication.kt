package com.eva.app.application

import android.app.Application
import com.eva.app.di.DaggerAppComponent
import com.eva.presentation.di.MainComponent

class EvaApplication : Application(), MainComponent.Provider {

    private lateinit var _mainComponent: MainComponent
    override val mainComponent: MainComponent get() = _mainComponent

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent
            .builder()
            .bindContext(this)
            .build()

        _mainComponent = appComponent
            .mainComponent()
            .build()
    }


}