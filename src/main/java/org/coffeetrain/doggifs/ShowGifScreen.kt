package org.coffeetrain.doggifs

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowGifScreen(val handle: String) : Screen {
  @IgnoredOnParcel
  override val layoutResId: Int = R.layout.show_gif_screen
}
