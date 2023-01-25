package com.eva.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.eva.domain.RequestState
import com.eva.domain.model.ImageData

import com.eva.presentation.base.InjectableFragment
import com.eva.presentation.databinding.FragmentHomeBinding
import com.eva.presentation.details.DetailsFragment
import com.eva.presentation.details.DetailsViewModel
import com.eva.presentation.home.adapter.attachFeedRecycler
import com.eva.presentation.navigation.NavRoutes
import com.eva.presentation.utils.Shimmers
import kotlin.reflect.KClass

class HomeFragment : InjectableFragment<FragmentHomeBinding>() {

    companion object {
        const val SharedImage = "home_fragment_shared_image"
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

            ViewCompat.setTransitionName(view, SharedImage)

            findNavController().navigate(
                NavRoutes.Details.Route,
                navOptions {
                    launchSingleTop = false
                    //this.restoreState
                },
                FragmentNavigatorExtras(view to DetailsFragment.SharedImage)
            )
        }

        attachFeedRecycler(binding.homeRecycler, navigationLambda) {
            homeViewModel.subscribeToImageUrls(this, it)
        }

        binding.recyclerShimmer.run {
            setShimmer(Shimmers.DefaultShimmer)

            homeViewModel.subscribeToRequestEvents(this@HomeFragment) {
                when(it) {
                    is RequestState.Success -> hideShimmer()
                    else -> showShimmer(true)
                }
            }
        }

    }

    private fun setupQuery() {
        binding.feedQueryTf.doOnTextChanged { text, start, before, count ->
            homeViewModel.emitQuery(text.toString())
        }
    }

}














