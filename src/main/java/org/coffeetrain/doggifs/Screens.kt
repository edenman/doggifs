package org.coffeetrain.doggifs

import coffeetrain.org.doggifs.R
import nz.bradcampbell.paperparcel.PaperParcel

@PaperParcel
@Layout(R.layout.show_gif_screen)
data class ShowGifScreen(val path: String)
