package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.coffeetrain.doggifs.databinding.SettingsScreenBinding

class SettingsScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private lateinit var binding: SettingsScreenBinding
  override fun onFinishInflate() {
    super.onFinishInflate()
    binding = SettingsScreenBinding.bind(this)
    binding.actionBar.titleText = R.string.settings
    binding.setAvailableGifs.setOnClickListener { flow.set(SetAvailableGifsScreen()) }
  }
}

