package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.set_available_gifs.view.action_bar
import kotlinx.android.synthetic.main.set_available_gifs.view.grid
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class SetAvailableGifsScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context,
    attrs) {
  val subscriptions = CompositeSubscription()
  val adapter = GifGridAdapter(context)

  override fun onFinishInflate() {
    super.onFinishInflate()
    action_bar.titleText = R.string.set_available_gifs
    grid.adapter = adapter;
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    subscriptions.add(context.dogGifRepository.observeAvailability()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          adapter.setHandles(it.keys)
        }))
  }

  override fun onDetachedFromWindow() {
    subscriptions.clear()
    super.onDetachedFromWindow()
  }
}
