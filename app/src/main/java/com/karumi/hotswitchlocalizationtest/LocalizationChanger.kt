package com.karumi.hotswitchlocalizationtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import java.util.*

object LocalizationChanger {

  private const val languageKey: String = "SavedLocaleLanguage"
  private const val countryKey: String = "SavedLocaleCountry"

  private lateinit var prefs: SharedPreferences
  private var listeners: MutableList<Listener> = LinkedList()

  fun initialize(app: Application) {
    prefs = app.getSharedPreferences("LocalizationChanger", Context.MODE_PRIVATE)
    val savedLocale = getSavedLocale()
    setLocale(app, savedLocale)
  }

  fun register(listener: Listener) = listeners.add(listener)

  fun unregister(listener: Listener) = listeners.remove(listener)

  fun setLocale(context: Context, newLocale: Locale) {
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val config = resources.configuration
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // >= API 17
      config.setLocale(newLocale)
      resources.updateConfiguration(config, displayMetrics)
      saveLocale(newLocale)
      listeners.forEach { listener ->
        listener.onChange()
      }
    }
  }

  fun getLocale(context: Context): Locale =
      context.resources.configuration.locale

  private fun saveLocale(locale: Locale): Boolean =
    prefs.edit()
        .putString(languageKey, locale.language)
        .putString(countryKey, locale.country)
        .commit()



  private fun getSavedLocale(): Locale {
    val lang = prefs.getString(languageKey, "en") ?: "en"
    val country = prefs.getString(countryKey, "") ?: ""
    return Locale(lang, country)
  }


  interface Listener {
    fun onChange()
  }
}

