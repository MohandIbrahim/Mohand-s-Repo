package com.test.bostatask.common.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.bostatask.R

@BindingAdapter(value = ["loadImageFromUrl"])
fun ImageView.loadImageFromUrl(url: String?) {
    this.run {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
                .placeholder(R.drawable.default_photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail()
                .dontAnimate()
                .dontTransform()
                .priority(Priority.HIGH)
                .into(this)
        } else {
            this.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.default_photo
                )
            )
        }
    }
}