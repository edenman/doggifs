package org.coffeetrain.doggifs;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class SwipeOrClickTouchListener implements View.OnTouchListener {
  private final GestureDetector clickGestureDetector;
  private final GestureDetector swipeGestureDetector;

  public SwipeOrClickTouchListener(Context context) {
    clickGestureDetector =
        new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
          @Override public boolean onSingleTapUp(MotionEvent e) {
            return true;
          }
        });
    swipeGestureDetector = new GestureDetector(context, new GestureListener());
  }

  protected abstract void onClick();

  protected abstract void onSwipeRightToLeft();

  protected abstract void onSwipeLeftToRight();

  public boolean onTouch(View v, MotionEvent event) {
    if (clickGestureDetector.onTouchEvent(event)) {
      onClick();
      return true;
    }
    return swipeGestureDetector.onTouchEvent(event);
  }

  private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override public boolean onDown(MotionEvent e) {
      return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      float distanceX = e2.getX() - e1.getX();
      float distanceY = e2.getY() - e1.getY();
      if (Math.abs(distanceX) > Math.abs(distanceY)
          && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
          && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
        if (distanceX > 0) {
          onSwipeLeftToRight();
        } else {
          onSwipeRightToLeft();
        }
        return true;
      }
      return false;
    }
  }
}
