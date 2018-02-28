package org.coffeetrain.doggifs

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.action_bar.view.button0
import kotlinx.android.synthetic.main.action_bar.view.button1
import kotlinx.android.synthetic.main.action_bar.view.title

class ActionBarView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  init {
    orientation = LinearLayout.HORIZONTAL
    LayoutInflater.from(context).inflate(R.layout.action_bar, this)
  }

  fun showButton(@DrawableRes iconResId: Int, onClickListener: (View) -> Unit) {
    if (button0.visibility == VISIBLE) {
      check(button1.visibility != VISIBLE, { "Only two buttons supported" })
      showButton(button1, iconResId, onClickListener)
    } else {
      showButton(button0, iconResId, onClickListener)
    }
  }

  private fun showButton(button: ImageView, iconResId: Int, onClickListener: (View) -> Unit) {
    button.setImageResource(iconResId)
    button.setOnClickListener(onClickListener)
    button.visibility = VISIBLE
  }

  var titleText: Int? = null
    set(@StringRes value) {
      title.setText(value!!)
    }
}
