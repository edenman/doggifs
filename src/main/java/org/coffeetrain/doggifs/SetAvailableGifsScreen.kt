package org.coffeetrain.doggifs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Screen(R.layout.set_available_gifs)
class SetAvailableGifsScreen : Parcelable {
  override fun equals(other: Any?): Boolean {
    return other is SetAvailableGifsScreen
  }

  override fun hashCode() = 0
}
