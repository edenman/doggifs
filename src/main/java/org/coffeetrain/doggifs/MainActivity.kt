package org.coffeetrain.doggifs

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import flow.Direction
import flow.Flow
import flow.KeyChanger
import flow.KeyDispatcher
import flow.State
import flow.TraversalCallback

class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
  }

  override fun attachBaseContext(baseContext: Context) {
    var flowContext = Flow.configure(baseContext, this)
        .keyParceler(PaperParceler())
        .defaultKey(ShowGifScreen("pounce.gif"))
        .dispatcher(KeyDispatcher.configure(this, Changer()).build())
        .install()
    super.attachBaseContext(flowContext)
  }

  override fun onBackPressed() {
    if (!flow.goBack()) {
      super.onBackPressed()
    }
  }

  inner class Changer : KeyChanger() {
    override fun changeKey(outgoing: State?,
        incoming: State,
        direction: Direction,
        incomingContexts: MutableMap<Any, Context>,
        callback: TraversalCallback) {
      val toScreen = incoming.getKey<Any>();
      val annotation = checkNotNull(toScreen.javaClass.getAnnotation(Layout::class.java),
          { "Screen didn't have a layout annotation" })
      val context = incomingContexts[toScreen]
      val inflated = LayoutInflater.from(context).inflate(annotation.resId, null)
      setContentView(inflated)
    }
  }
}



