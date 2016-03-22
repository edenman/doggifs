package org.coffeetrain.doggifs

import android.content.Context
import com.jakewharton.rxrelay.BehaviorRelay
import rx.Observable
import java.io.InputStream
import java.util.Arrays
import java.util.LinkedHashMap

class DogGifRepository(val context: Context) {
  private val assetGifs = Arrays.asList("pounce", "weiners")
  private val availableMap = LinkedHashMap<String, Boolean>()
  private val availableMapRelay = BehaviorRelay.create<Map<String, Boolean>>()

  init {
    for (handle in assetGifs) {
      availableMap.put(handle, getPrefs().getBoolean("avail-" + handle, true))
    }
    availableMapRelay.call(availableMap)
  }

  private fun getPrefs() = context.getSharedPreferences("doggif_availability", 0)

  fun loadGif(handle: String): ByteArray {
    val gifStream: InputStream = context.assets.open(handle + ".gif")
    return gifStream.readBytes(5000000);
  }

  fun nextGifHandle(handle: String): String {
    val gifs = getAvailableGifs()
    val currentIndex = gifs.indexOf(handle)
    return gifs[(currentIndex + 1) % gifs.size]
  }

  fun previousGifHandle(handle: String): String {
    val gifs = getAvailableGifs()
    val currentIndex = gifs.indexOf(handle)
    val previousIndex = if (currentIndex <= 0) gifs.size - 1 else currentIndex - 1;
    return gifs[previousIndex]
  }

  private fun getAvailableGifs(): List<String> {
    return assetGifs.filter { availableMap[it] == true }
  }

  fun setAvailable(handle: String, available: Boolean) {
    getPrefs().edit().putBoolean("avail-" + handle, available)
    availableMap.put(handle, available)
    availableMapRelay.call(availableMap)
  }

  fun isAvailable(handle: String): Boolean {
    return availableMap.get(handle)!!
  }

  fun toggleAvailability(handle: String) {
    setAvailable(handle, !isAvailable(handle))
  }

  fun observeAvailability(): Observable<Map<String, Boolean>> {
    return availableMapRelay
  }

  fun hasAnyAvailableGifs(): Boolean {
    return availableMap.any { it.value }
  }
}
