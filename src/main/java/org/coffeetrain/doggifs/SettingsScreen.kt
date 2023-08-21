package org.coffeetrain.doggifs

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SettingsScreen : Screen {
  @IgnoredOnParcel
  override val layoutResId: Int = R.layout.settings_screen

  override fun equals(other: Any?): Boolean {
    return other is SettingsScreen
  }

  override fun hashCode() = 0
}
