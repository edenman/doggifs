package org.coffeetrain.doggifs

import android.os.Parcelable
import flow.KeyParceler
import nz.bradcampbell.paperparcel.PaperParcels

class PaperParceler : KeyParceler {
  override fun toParcelable(key: Any?): Parcelable? {
    return PaperParcels.wrap(key);
  }

  override fun toKey(parcelable: Parcelable?): Any? {
    return PaperParcels.unwrap(parcelable);
  }
}
