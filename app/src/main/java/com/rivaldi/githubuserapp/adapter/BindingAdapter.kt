package com.rivaldi.githubuserapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.rivaldi.githubuserapp.util.ext.loadImageCircle

@BindingAdapter("loadImageCircle")
fun loadImage(image: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        image.loadImageCircle(url)
    }
}

