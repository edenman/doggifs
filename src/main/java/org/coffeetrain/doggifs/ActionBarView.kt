package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.coffeetrain.doggifs.databinding.ActionBarBinding

class ActionBarView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private val binding: ActionBarBinding

  init {
    orientation = HORIZONTAL
    binding = ActionBarBinding.inflate(LayoutInflater.from(context), this)
  }

  fun showButton(@DrawableRes iconResId: Int, onClickListener: (View) -> Unit) {
    if (binding.button0.visibility == VISIBLE) {
      check(binding.button1.visibility != VISIBLE, { "Only two buttons supported" })
      showButton(binding.button1, iconResId, onClickListener)
    } else {
      showButton(binding.button0, iconResId, onClickListener)
    }
  }

  private fun showButton(button: ImageView, iconResId: Int, onClickListener: (View) -> Unit) {
    button.setImageResource(iconResId)
    button.setOnClickListener(onClickListener)
    button.visibility = VISIBLE
  }

  var titleText: Int? = null
    set(@StringRes value) {
      binding.title.setText(value!!)
    }
}
