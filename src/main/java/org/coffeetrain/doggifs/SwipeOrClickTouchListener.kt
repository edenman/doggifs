package org.coffeetrain.doggifs

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

abstract class SwipeOrClickTouchListener(context: Context) : View.OnTouchListener {
  companion object {
    private const val SWIPE_DISTANCE_THRESHOLD = 100
    private const val SWIPE_VELOCITY_THRESHOLD = 100
  }

  private val clickGestureDetector: GestureDetector = GestureDetector(context,
      object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
          return true
        }
      })
  private val swipeGestureDetector: GestureDetector

  init {
    swipeGestureDetector = GestureDetector(context, GestureListener())
  }

  protected abstract fun onClick()

  protected abstract fun onSwipeRightToLeft()

  protected abstract fun onSwipeLeftToRight()

  override fun onTouch(v: View, event: MotionEvent): Boolean {
    if (clickGestureDetector.onTouchEvent(event)) {
      onClick()
      return true
    }
    return swipeGestureDetector.onTouchEvent(event)
  }

  private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

    override fun onDown(e: MotionEvent): Boolean {
      return true
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float,
        velocityY: Float): Boolean {
      val distanceX = e2.x - e1.x
      val distanceY = e2.y - e1.y
      if (Math.abs(distanceX) > Math.abs(distanceY)
          && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
          && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
        if (distanceX > 0) {
          onSwipeLeftToRight()
        } else {
          onSwipeRightToLeft()
        }
        return true
      }
      return false
    }
  }
}
