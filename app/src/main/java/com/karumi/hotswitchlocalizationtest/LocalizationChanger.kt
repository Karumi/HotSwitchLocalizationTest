package com.karumi.hotswitchlocalizationtest

import android.content.Context
import android.os.Build
import java.util.*

object LocalizationChanger {

  var listeners: MutableList<Listener> = LinkedList()

  fun register(listener: Listener) = listeners.add(listener)

  fun unregister(listener: Listener) = listeners.remove(listener)

  fun setLocale(context: Context, newLocale: Locale) {
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val config = resources.configuration
    config.setLocale(newLocale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      resources.updateConfiguration(config, displayMetrics)
    } else {
      context.createConfigurationContext(config)
    }
    listeners.forEach { listener ->
      listener.onChange()
    }
  }

  fun getLocale(context: Context): Locale =
      context.resources.configuration.locale

  interface Listener {
    fun onChange()
  }
}

