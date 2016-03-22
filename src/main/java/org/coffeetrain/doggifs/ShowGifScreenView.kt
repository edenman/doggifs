package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.show_gif_screen.view.action_bar
import kotlinx.android.synthetic.main.show_gif_screen.view.gif_view

class ShowGifScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  override fun onFinishInflate() {
    super.onFinishInflate()
    action_bar.showButton(R.drawable.ic_zoom_out_map_white_36dp, { onMaximizeClicked() })
    action_bar.showButton(R.drawable.ic_settings_white_36dp, { onSettingsClicked() })
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
    gif_view.setBytes(bytes)
    gif_view.startAnimation()
    gif_view.setOnTouchListener(object : SwipeOrClickTouchListener(context) {
      override fun onClick() {
        action_bar.visibility = VISIBLE;
        gif_view.setOnClickListener(null);
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
    flow.set(SettingsScreen)
  }

  private fun onMaximizeClicked() {
    action_bar.visibility = GONE;
    Toast.makeText(context, R.string.after_maximize, LENGTH_SHORT).show();
  }
}
