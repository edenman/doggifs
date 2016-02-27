package org.coffeetrain.doggifs

import android.os.Parcelable
import flow.KeyParceler

class PaperParceler : KeyParceler {
  override fun toParcelable(key: Any?): Parcelable? {
    // TODO return PaperParcels.wrap(key);
    return null;
  }

  override fun toKey(parcelable: Parcelable?): Any? {
    // TODO return PaperParcels.unwrap(key);
    return null;
  }
}
