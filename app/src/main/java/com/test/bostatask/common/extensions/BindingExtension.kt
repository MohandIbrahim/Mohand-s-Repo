package com.test.bostatask.common.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.test.bostatask.R

@BindingAdapter(value = ["loadImageFromUrl"])
fun ImageView.loadImageFromUrl(url: String?) {
    this.run {
        if (!url.isNullOrEmpty()) {
            this.load(url) {
                placeholder(R.drawable.default_photo)
            }
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