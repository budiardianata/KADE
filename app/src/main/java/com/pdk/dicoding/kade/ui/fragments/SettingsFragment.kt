package com.pdk.dicoding.kade.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.pdk.dicoding.kade.R

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener,
    Preference.OnPreferenceClickListener {
    private lateinit var aboutPreference: Preference
    private lateinit var themePreference: ListPreference
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
        themePreference =
            findPreference<ListPreference>(resources.getString(R.string.theme_key)) as ListPreference
        aboutPreference =
            findPreference<Preference>(resources.getString(R.string.about)) as Preference
        themePreference.onPreferenceChangeListener = this
        aboutPreference.onPreferenceClickListener = this
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        when (preference) {
            themePreference -> setTheme(newValue as String)
        }
        return true
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference) {
            aboutPreference -> {
                findNavController().navigate(SettingsFragmentDirections.settingsToAbout())
            }
        }
        return true
    }

    private fun setTheme(mode: String) {
        when (mode) {
            resources.getStringArray(R.array.themeEntryArray)[0] -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            resources.getStringArray(R.array.themeEntryArray)[1] -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}