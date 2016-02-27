package org.coffeetrain.doggifs

import android.support.annotation.LayoutRes
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
annotation class Layout(@LayoutRes val resId: Int)

