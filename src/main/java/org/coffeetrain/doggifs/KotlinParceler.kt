package org.coffeetrain.doggifs

import android.os.Parcelable
import flow.KeyParceler

class KotlinParceler : KeyParceler {
  override fun toParcelable(key: Any): Parcelable {
    return key as Parcelable
  }

  override fun toKey(parcelable: Parcelable): Any {
    return parcelable
  }
}
