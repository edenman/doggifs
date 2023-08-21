package org.coffeetrain.doggifs

import android.os.Parcelable
import android.support.annotation.LayoutRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

interface Screen : Parcelable {
  @get:LayoutRes
  val layoutResId: Int
}
