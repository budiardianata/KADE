package com.pdk.dicoding.kade.ui.adapter.itemLayout

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.pdk.dicoding.kade.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout


/**
 * Created by Budi Ardianata on 13/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class ItemLeagueLayout : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        constraintLayout {
            lparams(width = matchParent, height = wrapContent)
            val card = cardView {
                id = R.id.card
                background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.WHITE, BlendModeCompat.SRC_ATOP)
                radius = dip(5).toFloat()
                elevation = dip(5).toFloat()
                verticalLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    imageView {
                        id = R.id.image
                    }.lparams {
                        width = dip(100)
                        height = dip(100)
                    }
                    textView {
                        id = R.id.name
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        bottomMargin = dip(4)
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(8)
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                padding = dip(10)
            }
            applyConstraintSet {
                card {
                    connect(
                        ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.TOP of ConstraintLayout.LayoutParams.PARENT_ID,
                        ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintLayout.LayoutParams.PARENT_ID,
                        ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintLayout.LayoutParams.PARENT_ID
                    )
                }
            }
        }
    }
}