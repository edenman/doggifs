package org.coffeetrain.doggifs

import android.app.Application

class DogGifsApplication : Application() {
  private val dogGifRepository: DogGifRepository by lazy {
    DogGifRepository(this)
  }

  override fun getSystemService(name: String?): Any? {
    if (name.equals(dogGifRepositoryName)) {
      return dogGifRepository
    }
    return super.getSystemService(name)
  }

  companion object {
    val dogGifRepositoryName: String = "dogGifRepository"
  }
}
