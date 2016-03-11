package org.coffeetrain.doggifs

import android.content.Context
import android.util.AttributeSet
import android.view.ContextMenu
import android.widget.LinearLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.set_available_gifs.view.action_bar
import kotlinx.android.synthetic.main.set_available_gifs.view.grid

class SetAvailableGifsScreenView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  override fun onCreateContextMenu(menu: ContextMenu?) {
    super.onCreateContextMenu(menu)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    action_bar.titleText = R.string.set_available_gifs
    val row = TableRow(context)
    val thumbnailSizePx = context.getResources().getDimensionPixelSize(R.dimen.thumbnail_size);

    // TODO figure out multiple rows.  Should we be using a GridView?  GridLayout?
    for (handle in context.dogGifRepository.getAllGifHandles()) {
      // TODO Make these fixed size (layoutparams?)
      row.addView(GifGridView(context, handle), thumbnailSizePx, thumbnailSizePx)
    }
    grid.addView(row)
  }
}

