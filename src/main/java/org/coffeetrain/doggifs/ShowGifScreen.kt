package org.coffeetrain.doggifs

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Screen(R.layout.show_gif_screen)
data class ShowGifScreen(val handle: String) : Parcelable
