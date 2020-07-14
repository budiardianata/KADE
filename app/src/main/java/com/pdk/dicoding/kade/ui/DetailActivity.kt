package com.pdk.dicoding.kade.ui

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.model.League
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

class DetailActivity : AppCompatActivity() {
    lateinit var league: League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        league = intent.getParcelableExtra("data") as League
        DetailActivityUI().setContentView(this)
        supportActionBar?.title = league.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    inner class DetailActivityUI : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            scrollView {
                lparams(width = matchParent, height = matchParent)
                isFillViewport = true
                constraintLayout {
                    lparams(width = matchParent, height = matchParent)
                    imageView {
                        id = R.id.image
                        setImageResource(league.image)
                    }.lparams {
                        width = dip(150)
                        height = dip(150)
                        topMargin = dip(10)
                    }
                    textView(league.name) {
                        id = R.id.name
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        bottomMargin = dip(4)
                    }

                    textView(league.description) {
                        id = R.id.description
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        padding = dip(10)
                        bottomMargin = dip(4)
                    }

                    applyConstraintSet {
                        R.id.image {
                            connect(
                                ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.TOP of ConstraintLayout.LayoutParams.PARENT_ID,
                                ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintLayout.LayoutParams.PARENT_ID,
                                ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintLayout.LayoutParams.PARENT_ID
                            )
                        }
                        R.id.name {
                            connect(
                                ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintLayout.LayoutParams.PARENT_ID margin dip(
                                    8
                                ),
                                ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.BOTTOM of R.id.image margin dip(
                                    10
                                ),
                                ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintLayout.LayoutParams.PARENT_ID margin dip(
                                    8
                                )
                            )
                        }
                        R.id.description {
                            connect(
                                ConstraintSetBuilder.Side.START to ConstraintSetBuilder.Side.START of ConstraintLayout.LayoutParams.PARENT_ID margin dip(
                                    8
                                ),
                                ConstraintSetBuilder.Side.TOP to ConstraintSetBuilder.Side.BOTTOM of R.id.name margin dip(
                                    10
                                ),
                                ConstraintSetBuilder.Side.END to ConstraintSetBuilder.Side.END of ConstraintLayout.LayoutParams.PARENT_ID margin dip(
                                    8
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}