package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.show_gif_screen.view.gif_view

class ShowGifScreenView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
  override fun onFinishInflate() {
    super.onFinishInflate()
    val gifStream = context.assets.open(flowKey<ShowGifScreen>().path)
    gifStream.use {
      val bytes = it.readBytes()
      gif_view.setBytes(bytes)
      gif_view.startAnimation()
    }
  }
}
