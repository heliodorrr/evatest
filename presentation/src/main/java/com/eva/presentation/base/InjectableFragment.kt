package com.eva.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.navGraphViewModels
import androidx.viewbinding.ViewBinding
import com.eva.presentation.di.MainComponent
import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions


abstract class InjectableFragment<T : ViewBinding> : Fragment() {

    private lateinit var _binding: T
    protected val binding: T get() = _binding

    protected val mainComponent: MainComponent
        get() = (requireContext().applicationContext as MainComponent.Provider).mainComponent

    abstract val bindingClass: KClass<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupBinding(inflater, container)
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        val inflateFunction = bindingClass.staticFunctions.find {
            return@find it.name == "inflate" && it.parameters.size == 3
        }
        _binding = inflateFunction!!.call(inflater, container, false) as T

    }

    protected inline fun <reified VM : ViewModel>daggerViewModels(
        noinline ownerProducer: () -> ViewModelStoreOwner = { this },
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = {
            mainComponent.multibindingFactory()
        }
    ): Lazy<VM> {
        return viewModels<VM>(
            ownerProducer = ownerProducer,
            extrasProducer = extrasProducer,
            factoryProducer = factoryProducer
        )
    }

    protected inline fun <reified VM : ViewModel>daggerNavGraphViewModels(
        route: String,
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = {
            mainComponent.multibindingFactory()
        }
    ): Lazy<VM> {
        return navGraphViewModels<VM>(
            route,
            extrasProducer = extrasProducer,
            factoryProducer = factoryProducer
        )
    }
}

