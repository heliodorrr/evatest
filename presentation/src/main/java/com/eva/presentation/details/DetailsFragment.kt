package com.eva.presentation.details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.eva.presentation.base.InjectableFragment
import com.eva.presentation.databinding.FragmentDetailsBinding
import com.eva.presentation.navigation.NavRoutes
import com.eva.presentation.utils.addSimpleListener
import kotlin.reflect.KClass

class DetailsFragment : InjectableFragment<FragmentDetailsBinding>() {

    override val bindingClass: KClass<FragmentDetailsBinding> = FragmentDetailsBinding::class

    private val detailsViewModel: DetailsViewModel by daggerNavGraphViewModels(NavRoutes.Home.Route)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupAlphaAnimation()
        setupData()

    }

    private fun setupAlphaAnimation() {
        val animatorHandler =
            AlphaAnimatorHandler(
                binding.detailsTopContainer,
                binding.detailsBottomContainer
            )

        binding.detailedImage.setOnClickListener {
            animatorHandler.switchVisibility()
        }
    }

    private fun setupData() {

        val favoriteButtonLogicHandler = FavoriteButtonLogicHandler(binding.detailsFavoriteIcon)
            .apply {
                addOnClickListener { detailsViewModel.switchIsFavorite() }
            }

        detailsViewModel.observeIsFavorite(this) { favoriteButtonLogicHandler.setFavorite(it) }


        detailsViewModel.observeImageDetails(this) { imageData ->
            if (imageData == null) return@observeImageDetails

            binding.run {

                Glide.with(this@DetailsFragment)
                    .asDrawable()
                    .load(imageData.url)
                    .addSimpleListener {
                        startPostponedEnterTransition()
                        return@addSimpleListener false
                    }
                    .into(binding.detailedImage)

                detailsDateText.text = imageData.date
                detailsLikesText.text = imageData.likes.toString()
                detailsUsernameText.text = imageData.username

            }

        }

    }

    private fun setupNavigation() {
        postponeEnterTransition()
    }

}

