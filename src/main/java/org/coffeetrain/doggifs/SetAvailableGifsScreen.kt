package org.coffeetrain.doggifs

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SetAvailableGifsScreen : Screen {
  @IgnoredOnParcel
  override val layoutResId: Int = R.layout.set_available_gifs
  override fun equals(other: Any?): Boolean {
    return other is SetAvailableGifsScreen
  }

  override fun hashCode() = 0
}
