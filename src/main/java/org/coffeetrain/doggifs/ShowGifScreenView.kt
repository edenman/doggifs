package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import flow.Flow
import kotlinx.android.synthetic.main.show_gif_screen.view.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class ShowGifScreenView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
  override fun onFinishInflate() {
    super.onFinishInflate()
    var key: ShowGifScreen = checkNotNull(Flow.getKey<ShowGifScreen>(this), { "key is null!" });

    var inputStream: InputStream = context.getAssets().open(key.path);
    var bytes: ByteArray = read(inputStream);
    gif_view.setBytes(bytes)
    gif_view.startAnimation()
  }

  @Throws(IOException::class)
  fun read(inStream: InputStream): ByteArray {
    val out = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    while (true) {
      val r = inStream.read(buffer)
      if (r == -1) break
      out.write(buffer, 0, r)
    }

    return out.toByteArray()
  }
}
