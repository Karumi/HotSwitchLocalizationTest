package com.karumi.hotswitchlocalizationtest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), LocalizationChanger.Listener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    LocalizationChanger.register(this)
    setContentView(R.layout.activity_main)
    setTitle(R.string.app_name)
    button.setOnClickListener {
      switchLocale()
    }
    navigateButton.setOnClickListener {
      openNextActivity()
    }
  }

  override fun onDestroy() {
    LocalizationChanger.unregister(this)
    super.onDestroy()
  }

  override fun onChange() {
    this.recreate()
  }

  private fun switchLocale() {
    val currentLocale = LocalizationChanger.getLocale(this)
    val nextLocale = when (currentLocale) {
      Locale.SIMPLIFIED_CHINESE -> Locale.ENGLISH
      else -> Locale.SIMPLIFIED_CHINESE
    }
    LocalizationChanger.setLocale(this, nextLocale)
  }

  private fun openNextActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
  }

}
