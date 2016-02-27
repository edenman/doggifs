package org.coffeetrain.doggifs

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import flow.*

class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
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
    if (!Flow.get(this).goBack()) {
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
      val annotation: Layout = toScreen.javaClass.getAnnotation(Layout::class.java);
      checkNotNull(annotation.resId, { "Screen didn't have a layout annotation" });
      val context: Context? = incomingContexts.get(incoming.getKey())
      val inflated = LayoutInflater.from(context).inflate(annotation.resId, null)
      setContentView(inflated);
    }
  }
}



