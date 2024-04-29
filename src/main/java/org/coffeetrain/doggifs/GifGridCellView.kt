package org.coffeetrain.doggifs

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import org.coffeetrain.doggifs.databinding.GifGridCellBinding

class GifGridCellView(context: Context, attributeSet: AttributeSet) :
  FrameLayout(context, attributeSet) {
  private lateinit var binding: GifGridCellBinding

  override fun onFinishInflate() {
    super.onFinishInflate()
    binding = GifGridCellBinding.bind(this)
  }

  var handle: String? = null
    set(value) {
      field = value
      val gifBytes = context.dogGifRepository.loadGif(value!!)
      val bitmap = BitmapFactory.decodeByteArray(gifBytes, 0, gifBytes.size)
      binding.image.setImageBitmap(bitmap)
      val available = context.dogGifRepository.isAvailable(value)
      foreground = if (available) null else
        ResourcesCompat.getDrawable(resources, R.drawable.disabled_overlay, null)
    }

  init {
    setOnClickListener {
      context.dogGifRepository.toggleAvailability(handle!!)
    }
  }
}
