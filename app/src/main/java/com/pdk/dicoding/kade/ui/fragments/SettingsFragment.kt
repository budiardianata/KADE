package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.utils.Utils

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
            themePreference -> Utils.setTheme(requireContext(), newValue as String)
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
}