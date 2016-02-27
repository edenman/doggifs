package org.coffeetrain.doggifs

import android.os.Parcelable
import flow.KeyParceler

class PaperParceler : KeyParceler {
  override fun toParcelable(p0: Any?): Parcelable? {
    return null; //PaperParcels.wrap(p0);
  }

  override fun toKey(p0: Parcelable?): Any? {
    return null; //PaperParcels.unwrap(p0);
  }
}
