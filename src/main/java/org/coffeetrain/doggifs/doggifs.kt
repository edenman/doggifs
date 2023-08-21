package org.coffeetrain.doggifs

import android.annotation.SuppressLint
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
  @SuppressLint("WrongConstant") // Sorry, this is how this app does janky service lookup
  get() = this.getSystemService(DogGifsApplication.dogGifRepositoryName) as DogGifRepository
