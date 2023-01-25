package com.eva.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.MapKey
import dagger.multibindings.ClassKey
import javax.inject.Provider
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val key: KClass<out ViewModel>)
