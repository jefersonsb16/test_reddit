package com.jeferson.android.test_reddit.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jeferson.android.test_reddit.RedditApp

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline factory: () -> T): T {

    val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProvider(this.viewModelStore, viewModelFactory)[T::class.java]
}

fun ImageView.bindImageUrl(
    url: String?, @DrawableRes placeholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    if (url.isNullOrBlank()) {
        setImageResource(placeholder)
        return
    }

    Glide.with(context)
        .load(url)
        .error(errorPlaceholder)
        .placeholder(placeholder)
        .into(this)
}

val Context.app: RedditApp
    get() = applicationContext as RedditApp