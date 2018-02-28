package org.coffeetrain.doggifs

import android.os.Parcelable
import android.support.annotation.LayoutRes
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
annotation class Screen(@LayoutRes val layoutResId: Int)
