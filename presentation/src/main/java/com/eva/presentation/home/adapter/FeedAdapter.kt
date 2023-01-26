package com.eva.presentation.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.text.method.TextKeyListener.clear
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.core.content.contentValuesOf
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.eva.domain.model.ImageData
import com.eva.domain.utils.fastlog
import com.eva.presentation.R

import com.eva.presentation.databinding.ImageVhBinding
import com.eva.presentation.home.HomeItem
import com.eva.presentation.utils.GridLayoutManagerWithDisabler
import com.eva.presentation.utils.Shimmers
import com.eva.presentation.utils.addSimpleListener
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.util.concurrent.atomic.AtomicInteger

fun attachFeedRecycler(
    recycler: RecyclerView,
    navigationLambda: (ImageData, View) -> Unit,
    subscriber: ((List<HomeItem>)->Unit)->Unit,
) {
    attachFeedAdapter(recycler, subscriber, navigationLambda)
    attachLayoutManager(recycler)
    decorateRecycler(recycler)
}

private fun attachFeedAdapter(
    recycler: RecyclerView,
    subscriber: ((List<HomeItem>) -> Unit) -> Unit,
    navigationLambda: (ImageData, View) -> Unit
) {

    val delegate = delegate(recycler, navigationLambda)
    val adapter = ListDelegationAdapter<List<HomeItem>>(delegate)


    subscriber {
        adapter.items = it
        adapter.notifyDataSetChanged()
    }

    recycler.adapter = adapter
}

private fun delegate(
    recycler: RecyclerView,
    navigationLambda: (ImageData, View) -> Unit
): AdapterDelegate<List<HomeItem>> {

    val placeholder = ColorDrawable(Color.BLACK)

    return adapterDelegateViewBinding<HomeItem, HomeItem, ImageVhBinding>(
        viewBinding = { i, vg ->
            ImageVhBinding.inflate(i, vg, false)
        }

    ) {

        binding.feedShimmer.setShimmer(Shimmers.DefaultShimmer)
        binding.feedImageview.setOnClickListener {
            val item = item
            if (item is HomeItem.ActualItem) {
                navigationLambda(item.value, binding.feedImageview)
            }

        }

        bind {
            when(val item = item) {
                is HomeItem.ActualItem -> {
                    val imageData = item.value
                    binding.feedShimmer.showShimmer(true)

                    Glide.with(context)
                        .asDrawable()
                        .placeholder(placeholder)
                        .override(Target.SIZE_ORIGINAL)
                        .load(imageData.url)
                        .addSimpleListener {
                            binding.feedShimmer.hideShimmer()
                            return@addSimpleListener false
                        }
                        .into(binding.feedImageview)
                }
                is HomeItem.Placeholder -> {
                    binding.feedImageview.setImageDrawable(placeholder)
                    binding.feedShimmer.showShimmer(true)
                }
            }



        }
    }
}

private fun attachLayoutManager(recycler: RecyclerView) {
    val lm = GridLayoutManagerWithDisabler(recycler.context, RecyclerView.VERTICAL)
    lm.spanCount = 2

    recycler.layoutManager = lm

}

private fun decorateRecycler(recycler: RecyclerView) {
    val offset = recycler.context.resources.getDimensionPixelSize(R.dimen.query_bar_full_size)
    recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val pos = parent.getChildAdapterPosition(view)
            if (pos == 0 || pos == 1) outRect.top = offset
        }
    })
}











