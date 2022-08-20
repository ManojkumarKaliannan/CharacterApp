package com.owow.characterapp.utills

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["customImagePath", "placeholder"], requireAll = false)
fun imageUrls(view: ImageView, customImagePath: String?, placeholder: Drawable) {
    Glide.with(view.context)
        .load(customImagePath)
        .placeholder(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().error(placeholder))
        .into(view)
}


