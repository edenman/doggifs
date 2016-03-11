package org.coffeetrain.doggifs

import android.content.Context
import java.io.InputStream
import java.util.Arrays

class DogGifRepository(val context: Context) {
  private val assetGifs = Arrays.asList("pounce", "weiners")

  fun loadGif(handle: String): ByteArray {
    val gifStream: InputStream = context.assets.open(handle + ".gif")
    return gifStream.readBytes(5000000);
  }

  fun nextGifHandle(handle: String): String {
    val currentIndex = assetGifs.indexOf(handle)
    check(currentIndex >= 0, { "Unable to find handle " + handle })
    return assetGifs[(currentIndex + 1) % assetGifs.size]
  }

  fun previousGifHandle(handle: String): String {
    val currentIndex = assetGifs.indexOf(handle)
    check(currentIndex >= 0, { "Unable to find handle " + handle })
    val previousIndex = if (currentIndex == 0) assetGifs.size - 1 else currentIndex - 1;
    return assetGifs[previousIndex]
  }

  fun getAllGifHandles(): List<String> {
    return assetGifs;
  }
}
