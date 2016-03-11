package org.coffeetrain.doggifs

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView

class GifGridView(context: Context, fileName: String) : ImageView(context) {
  init {
    scaleType = ScaleType.CENTER_CROP
    val gifBytes = context.dogGifRepository.loadGif(fileName)
    val bitmap = BitmapFactory.decodeByteArray(gifBytes, 0, gifBytes.size);
    setImageBitmap(bitmap)
  }
}
