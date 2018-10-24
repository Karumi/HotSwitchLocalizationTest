package com.karumi.hotswitchlocalizationtest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : LocalizationActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setTitle(R.string.app_name)
    button.setOnClickListener {
      switchLocale()
    }
    navigateButton.setOnClickListener {
      openNextActivity()
    }
  }

  private fun switchLocale() {
    val config = resources.configuration
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      val currentLocale = config.locale
      val nextLocale = when (currentLocale) {
        Locale.SIMPLIFIED_CHINESE -> Locale.ENGLISH
        else -> Locale.SIMPLIFIED_CHINESE
      }
      setLanguage(nextLocale)
    }
  }

  private fun openNextActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
  }

}
