package com.pdk.dicoding.kade.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import com.pdk.dicoding.kade.R


/**
 * Created by Budi Ardianata on 19/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
object Utils {
    const val DEFAULT_COUNTRY = "All"

    fun materialContainerTransform(activity: Activity) =
        MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host
            interpolator = FastOutSlowInInterpolator()
            containerColor = MaterialColors.getColor(
                activity.findViewById(android.R.id.content),
                R.attr.colorSurface
            )
            fadeMode = MaterialContainerTransform.FADE_MODE_OUT
            duration = 500
        }

    fun setTheme(context: Context, mode: String?) {
        when (mode) {
            context.resources.getStringArray(R.array.themeEntryArray)[0] -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            context.resources.getStringArray(R.array.themeEntryArray)[1] -> {
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

    fun isDarkTheme(context: Context): Boolean {
        return context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

}