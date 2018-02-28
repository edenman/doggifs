package org.coffeetrain.doggifs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Screen(R.layout.settings_screen)
class SettingsScreen : Parcelable {
  override fun equals(other: Any?): Boolean {
    return other is SettingsScreen
  }

  override fun hashCode() = 0
}
