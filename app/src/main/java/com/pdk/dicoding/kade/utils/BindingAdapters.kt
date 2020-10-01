package com.pdk.dicoding.kade.utils

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pdk.dicoding.kade.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Budi Ardianata on 14/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */

@BindingAdapter("date", "time")
fun bindDate(textView: TextView, date: String, time: String) {
    textView.apply {
        if (date.isNotEmpty()) {
            var parseFormat: String
            var dateFormat: Date?
            try {
                dateFormat = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).parse("$date $time")
                parseFormat = "EE, d MMM yyyy HH:mm z"
            } catch (e: Exception) {
                dateFormat =
                    SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse("$date $time")
                parseFormat = "EE, d MMM yyyy"
            }
            if (dateFormat != null) {
                text = SimpleDateFormat(parseFormat, Locale.getDefault()).format(dateFormat)
            } else textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }

    }
}

@BindingAdapter("text_array")
fun bindTextArray(textView: TextView, text: String) {
    val arrayList = text.substringBeforeLast(";").split(";")
    for (i in arrayList.indices) {
        textView.append(arrayList[i].trim())
        if (i < arrayList.size - 1) {
            textView.append("\n")
        }
    }
}

@BindingAdapter(value = ["imageUrl", "error", "overrideSize"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, error: Drawable, isOrigin: Boolean) {
    val circularProgressDrawable = CircularProgressDrawable(imageView.context)

    circularProgressDrawable.apply {
        setColorSchemeColors(R.color.colorPrimary)
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }

    val requestOptions = if (isOrigin)
        RequestOptions
            .centerCropTransform()
            .override(100, 100)
    else RequestOptions.centerCropTransform()
    Glide.with(imageView)
        .asBitmap()
        .load("$url/preview")
        .placeholder(circularProgressDrawable)
        .apply(
            requestOptions
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(error)
        .into(imageView)
}

@BindingAdapter("goToUrl")
fun bindImageClick(imageButton: AppCompatImageButton, url: String?) {
    imageButton.apply {
        setOnClickListener {
            if (!url.isNullOrBlank())
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        if (url.startsWith("http")) Uri.parse(url) else Uri.parse("http://$url")
                    )
                )
            else
                Toast.makeText(
                    context,
                    resources.getString(R.string.not_found),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
}
