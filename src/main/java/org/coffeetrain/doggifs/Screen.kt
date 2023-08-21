package org.coffeetrain.doggifs

import android.os.Parcelable
import androidx.annotation.LayoutRes

interface Screen : Parcelable {
  @get:LayoutRes
  val layoutResId: Int
}
