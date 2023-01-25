package com.eva.presentation.details

import android.os.Bundle
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeImageTransform
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.eva.domain.utils.fastlog
import com.eva.presentation.R
import com.eva.presentation.base.InjectableFragment
import com.eva.presentation.databinding.FragmentDetailsBinding
import com.eva.presentation.navigation.NavRoutes
import com.eva.presentation.utils.addSimpleListener
import kotlin.reflect.KClass

class DetailsFragment() : InjectableFragment<FragmentDetailsBinding>() {

    companion object {
        const val SharedImage = "details_fragment_image"
    }

    override val bindingClass: KClass<FragmentDetailsBinding> = FragmentDetailsBinding::class

    private val detailsViewModel: DetailsViewModel by daggerNavGraphViewModels(NavRoutes.Home.Route)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupImage()

    }

    private fun setupImage() {
        val imageData = detailsViewModel.imageData
            ?: throw IllegalStateException("ImageData object must be present")

        Glide.with(this)
            .asDrawable()
            .load(imageData.url)
            .addSimpleListener {
                startPostponedEnterTransition()
                return@addSimpleListener false
            }
            .into(binding.detailedImage)
    }

    private fun setupNavigation() {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {

                fastlog(names.toString())
                fastlog(sharedElements.toString())
                super.onMapSharedElements(names, sharedElements)
            }
        })
        requireActivity().window?.sharedElementsUseOverlay = false
        sharedElementEnterTransition = TransitionInflater
            .from(requireContext()).inflateTransition(R.transition.shared_image)
        enterTransition = TransitionInflater
            .from(requireContext()).inflateTransition(android.R.transition.fade)


        postponeEnterTransition()

        binding.detailedImage.setOnClickListener { findNavController().popBackStack() }
        ViewCompat.setTransitionName(binding.detailedImage, SharedImage)
    }

}

