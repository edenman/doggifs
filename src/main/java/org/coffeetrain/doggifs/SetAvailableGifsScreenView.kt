package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.coffeetrain.doggifs.databinding.SetAvailableGifsBinding
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class SetAvailableGifsScreenView(context: Context, attrs: AttributeSet)
  : LinearLayout(context, attrs) {
  private val subscriptions = CompositeSubscription()
  private val adapter = GifGridAdapter(context)
  private lateinit var binding: SetAvailableGifsBinding

  override fun onFinishInflate() {
    super.onFinishInflate()
    binding = SetAvailableGifsBinding.bind(this)
    binding.actionBar.titleText = R.string.set_available_gifs
    binding.grid.adapter = adapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    subscriptions.add(context.dogGifRepository.observeAvailability()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe{
          adapter.setHandles(it.keys)
        })
  }

  override fun onDetachedFromWindow() {
    subscriptions.clear()
    super.onDetachedFromWindow()
  }
}
