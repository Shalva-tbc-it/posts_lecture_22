package com.example.posts.presentation.extension

import android.view.View
import android.view.ViewGroup

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(left, top, right, bottom)
    layoutParams = params
}

fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.setVisibilityAndMargins(visible: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    setVisibility(visible)
    setMargins(left, top, right, bottom)
}