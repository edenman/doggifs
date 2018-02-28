package org.coffeetrain.doggifs

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.gif_grid_cell.view.image

class GifGridCellView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {
  var handle: String? = null
    set(value) {
      field = value
      val gifBytes = context.dogGifRepository.loadGif(value!!)
      val bitmap = BitmapFactory.decodeByteArray(gifBytes, 0, gifBytes.size)
      image.setImageBitmap(bitmap)
      val available = context.dogGifRepository.isAvailable(value)
      foreground = if (available) null else resources.getDrawable(R.drawable.disabled_overlay)
    }

  init {
    setOnClickListener {
      context.dogGifRepository.toggleAvailability(handle!!)
    }
  }
}
