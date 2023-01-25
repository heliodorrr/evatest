package com.eva.app.di

import android.content.Context
import com.eva.app.application.EvaApplication
import com.eva.data.di.DataLayerBindModule
import com.eva.data.di.DataLayerImplModule
import com.eva.presentation.di.MainComponent
import com.eva.presentation.di.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, PresentationModule::class,
DataLayerImplModule::class, DataLayerBindModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: EvaApplication)

    fun mainComponent(): MainComponent.Builder
}



