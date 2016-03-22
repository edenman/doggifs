package org.coffeetrain.doggifs

import android.content.Context
import android.view.View
import flow.Flow

val Context.flow: Flow
  get() = Flow.get(this)

val View.flow: Flow
  get() = Flow.get(this)

fun <T : Any> View.flowKey(): T = checkNotNull(Flow.getKey<T>(this),
    { "view's Context has no Flow key" })

val Context.dogGifRepository: DogGifRepository
  get() = this.getSystemService(DogGifsApplication.dogGifRepositoryName) as DogGifRepository
