package org.coffeetrain.doggifs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.coffeetrain.doggifs.databinding.GifGridCellBinding

class GifGridAdapter(context: Context) : BaseAdapter() {
  private val inflater = LayoutInflater.from(context)!!
  private val allGifHandles = mutableListOf<String>()

  fun setHandles(handles: Collection<String>) {
    allGifHandles.clear()
    allGifHandles.addAll(handles)
    notifyDataSetChanged()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
    val view = convertView ?: inflater.inflate(R.layout.gif_grid_cell, parent, false)
    val cellView = view as GifGridCellView
    cellView.handle = allGifHandles[position]
    return view
  }

  override fun getItem(position: Int): Any {
    return allGifHandles[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return allGifHandles.size
  }
}
