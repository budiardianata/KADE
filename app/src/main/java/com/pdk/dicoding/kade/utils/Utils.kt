package com.pdk.dicoding.kade.utils

import android.app.Activity
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
}