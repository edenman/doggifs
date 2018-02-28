package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.settings_screen.view.action_bar
import kotlinx.android.synthetic.main.settings_screen.view.set_available_gifs

class SettingsScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  override fun onFinishInflate() {
    super.onFinishInflate()
    action_bar.titleText = R.string.settings
    set_available_gifs.setOnClickListener { flow.set(SetAvailableGifsScreen()) }
  }
}

