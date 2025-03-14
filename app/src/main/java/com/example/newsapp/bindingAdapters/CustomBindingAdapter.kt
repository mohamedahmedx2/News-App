package com.example.newsapp.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.github.marlonlom.utilities.timeago.TimeAgo

@BindingAdapter("app:imageUrl")
fun bindImage(imageView: ImageView, url: String) {
    Glide.with(imageView)
        .load(url)
        .error(R.drawable.image_failed)
        .into(imageView)
}

@BindingAdapter("app:timeAgo")
fun extractTimeAgo(textView: TextView, timInMillis: Long?) {
    textView.text = TimeAgo.using(timInMillis ?: 0)

}