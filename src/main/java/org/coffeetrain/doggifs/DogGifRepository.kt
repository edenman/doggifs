package org.coffeetrain.doggifs

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.rxrelay.BehaviorRelay
import rx.Observable
import java.io.InputStream

class DogGifRepository(val context: Context) {
  private val assetGifs = listOf("pounce", "weiners")
  private val availableMap = linkedMapOf<String, Boolean>()
  private val availableMapRelay = BehaviorRelay.create<Map<String, Boolean>>()

  private val prefs: SharedPreferences by lazy {
    context.getSharedPreferences("doggif_availability", 0)
  }

  init {
    for (handle in assetGifs) {
      availableMap[handle] = prefs.getBoolean("avail-$handle", true)
    }
    availableMapRelay.call(availableMap)
  }

  fun loadGif(handle: String): ByteArray {
    val gifStream: InputStream = context.assets.open("$handle.gif")
    return gifStream.readBytes()
  }

  fun nextGifHandle(handle: String): String {
    val gifs = getAvailableGifs()
    val currentIndex = gifs.indexOf(handle)
    return gifs[(currentIndex + 1) % gifs.size]
  }

  fun previousGifHandle(handle: String): String {
    val gifs = getAvailableGifs()
    val currentIndex = gifs.indexOf(handle)
    val previousIndex = if (currentIndex <= 0) gifs.size - 1 else currentIndex - 1
    return gifs[previousIndex]
  }

  private fun getAvailableGifs(): List<String> {
    return assetGifs.filter { availableMap[it] == true }
  }

  private fun setAvailable(handle: String, available: Boolean) {
    prefs.edit()
        .putBoolean("avail-$handle", available)
        .apply()
    availableMap[handle] = available
    availableMapRelay.call(availableMap)
  }

  fun isAvailable(handle: String): Boolean {
    return availableMap[handle]!!
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
