package com.karumi.hotswitchlocalizationtest

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate

class App: Application() {
  private val localizationDelegate = LocalizationApplicationDelegate(this)

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(localizationDelegate.attachBaseContext(base))
  }

  override fun onConfigurationChanged(newConfig: Configuration?) {
    super.onConfigurationChanged(newConfig)
    localizationDelegate.onConfigurationChanged(this)
  }

  override fun getApplicationContext(): Context {
    return localizationDelegate.getApplicationContext(super.getApplicationContext())
  }
}