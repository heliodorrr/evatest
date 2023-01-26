package com.eva.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.AnimBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.eva.domain.RequestState
import com.eva.domain.model.ImageData
import com.eva.presentation.animation.defaultAnimations
import com.eva.presentation.base.InjectableFragment
import com.eva.presentation.databinding.FragmentHomeBinding
import com.eva.presentation.details.DetailsViewModel
import com.eva.presentation.home.adapter.attachFeedRecycler
import com.eva.presentation.navigation.NavRoutes
import com.eva.presentation.utils.Shimmers
import com.eva.presentation.utils.setCanScroll

import kotlin.reflect.KClass

class HomeFragment : InjectableFragment<FragmentHomeBinding>() {

    companion object {
    }

    override val bindingClass: KClass<FragmentHomeBinding> get() = FragmentHomeBinding::class
    private val homeViewModel: HomeViewModel by daggerViewModels()
    private val detailsViewModel: DetailsViewModel by daggerNavGraphViewModels(NavRoutes.Home.Route)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycler()

        setupQuery()
    }

    private fun setupRecycler() {

        val navigationLambda = { imageData: ImageData, view: View ->
            detailsViewModel.setImageData(imageData)

            findNavController().navigate(
                NavRoutes.Details.Route,
                navOptions { anim(AnimBuilder::defaultAnimations) },
            )
        }

        attachFeedRecycler(binding.homeRecycler, navigationLambda) {
            homeViewModel.subscribeToImageUrls(this, it)
        }

        binding.recyclerShimmer.run {
            setShimmer(Shimmers.DefaultShimmer)

            homeViewModel.subscribeToRequestEvents(this@HomeFragment) {
                when(it) {
                    is RequestState.Success -> {
                        hideShimmer()
                        binding.homeRecycler.setCanScroll(true)
                    }
                    else -> {
                        binding.homeRecycler.scrollToPosition(0)
                        binding.homeRecycler.setCanScroll(false)
                        showShimmer(true)
                    }
                }
            }
        }

    }

    private fun setupQuery() {
        binding.feedQueryTf.doOnTextChanged { text, _, _, _ ->
            homeViewModel.emitQuery(text.toString())
        }
    }

}














