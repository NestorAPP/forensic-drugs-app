package com.forensic.drugs.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

enum class AppThemeMode {
    SYSTEM, LIGHT, DARK
}

object ThemePreference {
    private const val PREFS = "app_settings"
    private const val KEY_THEME = "theme_mode"

    var current by mutableStateOf(AppThemeMode.SYSTEM)
        private set

    fun load(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val saved = prefs.getString(KEY_THEME, AppThemeMode.SYSTEM.name)
        current = try {
            AppThemeMode.valueOf(saved ?: AppThemeMode.SYSTEM.name)
        } catch (e: Exception) {
            AppThemeMode.SYSTEM
        }
    }

    fun set(context: Context, mode: AppThemeMode) {
        current = mode
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME, mode.name).apply()
    }
}
