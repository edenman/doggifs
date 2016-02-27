package org.coffeetrain.doggifs

import coffeetrain.org.doggifs.R
import nz.bradcampbell.paperparcel.PaperParcel

class Screens {
  @PaperParcel
  @Layout(R.layout.show_gif)
  data class ShowGifScreen(val path: String)
}
