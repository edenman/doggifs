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
    // TODO fix
    window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
  }

  override fun attachBaseContext(baseContext: Context) {
    val flowContext = Flow.configure(baseContext, this)
      .keyParceler(KotlinParceler())
      .defaultKey(ShowGifScreen("pounce"))
      .dispatcher(KeyDispatcher.configure(this, Changer()).build())
      .install()
    super.attachBaseContext(flowContext)
  }

  override fun getSystemService(name: String): Any? {
    return super.getSystemService(name) ?: application.getSystemService(name)
  }

  // TODO fix
  @Suppress("OVERRIDE_DEPRECATION")
  override fun onBackPressed() {
    if (!flow.goBack()) {
      super.onBackPressed()
    }
  }

  inner class Changer : KeyChanger {
    override fun changeKey(
      outgoing: State?,
      incoming: State,
      direction: Direction,
      incomingContexts: MutableMap<Any, Context>,
      callback: TraversalCallback
    ) {
      val toScreen = incoming.getKey<Screen>()
      val layoutResId = toScreen.layoutResId

      outgoing?.save(findViewById(android.R.id.content))

      val context = incomingContexts[toScreen]
      val inflated = LayoutInflater.from(context).inflate(layoutResId, null)
      incoming.restore(inflated)
      setContentView(inflated)
      callback.onTraversalCompleted()
    }
  }
}



