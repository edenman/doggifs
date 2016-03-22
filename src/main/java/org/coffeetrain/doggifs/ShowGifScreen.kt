package org.coffeetrain.doggifs

import nz.bradcampbell.paperparcel.PaperParcel

@PaperParcel
@Screen(R.layout.show_gif_screen)
data class ShowGifScreen(val handle: String)
