package com.karumi.hotswitchlocalizationtest

import android.app.Application

class App: Application() {

  override fun onCreate() {
    LocalizationChanger.initialize(this)
    super.onCreate()
  }
}