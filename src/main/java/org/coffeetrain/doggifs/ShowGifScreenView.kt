package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import org.coffeetrain.doggifs.databinding.ShowGifScreenBinding

class ShowGifScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private lateinit var binding: ShowGifScreenBinding

  override fun onFinishInflate() {
    super.onFinishInflate()
    binding = ShowGifScreenBinding.bind(this)
    binding.actionBar.showButton(R.drawable.ic_zoom_out_map_white_36dp, { onMaximizeClicked() })
    binding.actionBar.showButton(R.drawable.ic_settings_white_36dp, { onSettingsClicked() })
    val repo: DogGifRepository = context.dogGifRepository
    var handle = flowKey<ShowGifScreen>().handle
    if (!repo.isAvailable(handle)) {
      if (repo.hasAnyAvailableGifs()) {
        handle = repo.nextGifHandle(handle)
      } else {
        Toast.makeText(context, R.string.no_gifs, LENGTH_SHORT).show()
        return
      }
    }
    val bytes = repo.loadGif(handle)
    binding.gifView.setBytes(bytes)
    binding.gifView.startAnimation()
    binding.gifView.setOnTouchListener(object : SwipeOrClickTouchListener(context) {
      override fun onClick() {
        binding.actionBar.visibility = VISIBLE
        binding.gifView.setOnClickListener(null)
      }

      override fun onSwipeLeftToRight() {
        flow.set(ShowGifScreen(repo.nextGifHandle(handle)))
      }

      override fun onSwipeRightToLeft() {
        flow.set(ShowGifScreen(repo.previousGifHandle(handle)))
      }
    })
  }

  private fun onSettingsClicked() {
    flow.set(SettingsScreen())
  }

  private fun onMaximizeClicked() {
    binding.actionBar.visibility = GONE
    Toast.makeText(context, R.string.after_maximize, LENGTH_SHORT).show()
  }
}
