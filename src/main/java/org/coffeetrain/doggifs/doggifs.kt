package org.coffeetrain.doggifs

import android.content.Context
import android.view.View
import flow.Flow

val Context.flow: Flow
  get() = Flow.get(this)

fun <T : Any> View.flowKey(): T = checkNotNull(Flow.getKey<T>(this),
    { "view's Context has no Flow key" })
